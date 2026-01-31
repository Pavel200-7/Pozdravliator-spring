<script setup>
import { ref, reactive, onMounted, computed, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'
import SubscriptionList from '@/components/subscriptions/SubscriptionList.vue'
import { API_CONFIG } from '@/config/api'

const router = useRouter()
const route = useRoute()

const subscriberId = ref(route.params.id)
const subscriberEmail = ref(route.query.email)

const state = reactive({
  subscriptions: [],
  isLoading: true,
  error: null
})

const activeSubscriptions = computed(() => {
  return state.subscriptions.filter(sub => sub.active)
})

const inactiveSubscriptions = computed(() => {
  return state.subscriptions.filter(sub => !sub.active)
})

async function fetchSubscriptions() {
  state.isLoading = true

  try {
    const response = await axios.get(`${API_CONFIG.BASE_URL}/api/v1/subscription/by_subscriber/${subscriberId.value}`)
    state.subscriptions = response.data.items || []
  } catch (err) {
    state.error = 'Ошибка при загрузке подписок'
    console.error('Ошибка при загрузке подписок:', err)
  } finally {
    state.isLoading = false
  }
}

async function handleCreateSubscription(data) {
  try {
    const response = await axios.post(`${API_CONFIG.BASE_URL}/api/v1/subscription`, data)
    
    if (response.data.succeed) {
      await fetchSubscriptions()
    }
  } catch (err) {
    console.error('Ошибка при создании подписки:', err)
    await fetchSubscriptions()
  }
}

async function handleDeleteSubscription(data) {
  try {
    const response = await axios.delete(`${API_CONFIG.BASE_URL}/api/v1/subscription`, {
      data: data
    })
    
    if (response.data.succeed) {
      await fetchSubscriptions()
    }
  } catch (err) {
    console.error('Ошибка при удалении подписки:', err)
    await fetchSubscriptions()
  }
}

function handleRefresh() {
  fetchSubscriptions()
}

function goBack() {
  router.push('/subscribers')
}

onMounted(() => {
  if (subscriberId.value) {
    fetchSubscriptions()
  }
})

watch(() => route.params.id, (newId) => {
  if (newId) {
    subscriberId.value = newId
    subscriberEmail.value = route.query.email 
    state.isLoading = true
    state.error = null
    fetchSubscriptions()
  }
})

watch(() => route.query.email, (newEmail) => {
  if (newEmail) {
    subscriberEmail.value = newEmail
  }
})
</script>

<template>
  <div class="subscriber-view">
    <div class="page-header">
      <button class="back-btn" @click="goBack">
        ← Назад к списку
      </button>
      <h1 class="page-title">Детали подписчика</h1>
    </div>

    <div v-if="state.isLoading" class="loading-state">
      <div class="loading-spinner">⏳</div>
      <p>Загрузка данных...</p>
    </div>

    <div v-else-if="state.error" class="error-state">
      <div class="error-icon">❌</div>
      <p class="error-message">{{ state.error }}</p>
      <button class="back-to-list" @click="goBack">
        Вернуться к списку
      </button>
    </div>

    <div v-else class="subscriber-content">
      <div class="subscriber-card">
        <div class="subscriber-header">
          <h2 class="subscriber-name">
            <span class="icon">👤</span>
            Подписчик
          </h2>
          <div class="subscriber-actions">
            <button class="action-btn edit" @click="handleRefresh" :disabled="state.isLoading">
              <span v-if="state.isLoading">⏳</span>
              <span v-else>🔄</span>
              Обновить
            </button>
          </div>
        </div>

        <div class="subscriber-info">
          <div class="info-item">
            <span class="info-label">Email:</span>
            <span class="info-value email">{{ subscriberEmail }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">Всего подписок:</span>
            <span class="info-value">{{ state.subscriptions.length }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">Активных подписок:</span>
            <span class="info-value active-count">{{ activeSubscriptions.length }}</span>
          </div>
        </div>
      </div>

      <div class="subscriptions-section">
        <div class="section-header">
          <h3 class="section-title">
            <span class="icon">📋</span>
            Управление подписками
          </h3>
        </div>

        <div class="subscriptions-list">
          <SubscriptionList
            :items="state.subscriptions"
            :subscriber-email="subscriberEmail"
            :is-loading="false"
            @create="handleCreateSubscription"
            @delete="handleDeleteSubscription"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.subscriber-view {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

/* Заголовок страницы */
.page-header {
  display: flex;
  align-items: center;
  gap: 20px;
  margin-bottom: 30px;
  padding-bottom: 15px;
  border-bottom: 2px solid #e0e0e0;
}

.back-btn {
  padding: 8px 16px;
  background: #6c757d;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  transition: all 0.3s ease;
}

.back-btn:hover {
  background: #545b62;
  transform: translateY(-1px);
}

.page-title {
  margin: 0;
  color: #333;
  font-size: 28px;
}

/* Состояния загрузки и ошибки */
.loading-state,
.error-state {
  text-align: center;
  padding: 60px 20px;
  background: #f8f9fa;
  border-radius: 10px;
  border: 1px solid #e9ecef;
}

.loading-spinner,
.error-icon {
  font-size: 48px;
  margin-bottom: 20px;
}

.error-message {
  color: #dc3545;
  font-size: 18px;
  margin-bottom: 20px;
}

/* Карточка подписчика */
.subscriber-card {
  background: white;
  border-radius: 10px;
  padding: 25px;
  margin-bottom: 30px;
  border: 1px solid #e0e0e0;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}

.subscriber-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
  padding-bottom: 15px;
  border-bottom: 1px solid #e9ecef;
}

.subscriber-name {
  display: flex;
  align-items: center;
  gap: 10px;
  margin: 0;
  color: #333;
  font-size: 22px;
}

.subscriber-actions {
  display: flex;
  gap: 10px;
}

.action-btn {
  display: flex;
  align-items: center;
  gap: 6px;
  padding: 8px 16px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 14px;
  font-weight: 500;
  transition: all 0.3s ease;
}

.action-btn.edit {
  background: #17a2b8;
  color: white;
}

.action-btn.edit:hover:not(:disabled) {
  background: #138496;
  transform: translateY(-1px);
}

.action-btn.edit:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

/* Информация о подписчике */
.subscriber-info {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 15px;
}

.info-item {
  display: flex;
  flex-direction: column;
  gap: 5px;
  padding: 12px;
  background: #f8f9fa;
  border-radius: 6px;
}

.info-label {
  font-size: 12px;
  color: #6c757d;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.info-value {
  font-size: 16px;
  font-weight: 500;
  color: #333;
}

.info-value.email {
  color: #007bff;
  font-family: monospace;
}

.info-value.active-count {
  color: #28a745;
}

/* Секция подписок */
.subscriptions-section {
  background: white;
  border-radius: 10px;
  padding: 25px;
  border: 1px solid #e0e0e0;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}

.section-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 25px;
  padding-bottom: 20px;
  border-bottom: 2px solid #f0f0f0;
}

.section-title {
  display: flex;
  align-items: center;
  gap: 10px;
  margin: 0;
  color: #333;
  font-size: 20px;
}

.section-actions {
  display: flex;
  gap: 15px;
}

/* Кнопка возврата в список */
.back-to-list {
  padding: 10px 20px;
  background: #007bff;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 16px;
  margin-top: 20px;
  transition: all 0.3s ease;
}

.back-to-list:hover {
  background: #0056b3;
  transform: translateY(-1px);
}

/* Адаптивность */
@media (max-width: 768px) {
  .page-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 10px;
  }
  
  .subscriber-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 15px;
  }
  
  .section-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 15px;
  }
  
  .section-actions {
    flex-direction: column;
    width: 100%;
  }
  
  .subscriber-info {
    grid-template-columns: 1fr;
  }
  
  .subscriber-view {
    padding: 15px;
  }
}

@media (max-width: 480px) {
  .page-title {
    font-size: 22px;
  }
  
  .subscriber-name {
    font-size: 18px;
  }
  
  .section-title {
    font-size: 18px;
  }
}
</style>