<script setup>
import { computed } from 'vue'

const props = defineProps({
  items: {
    type: Array,
    default: () => []
  },
  subscriberEmail: {
    type: String,
    default: ''
  },
  isLoading: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['create', 'delete'])

const activeSubscriptions = computed(() => {
  return props.items.filter(item => item.active)
})

const inactiveSubscriptions = computed(() => {
  return props.items.filter(item => !item.active)
})

function handleCreateSubscription(subscriptionType) {
  emit('create', {
    email: props.subscriberEmail,
    subscriptionType: subscriptionType
  })
}

function handleDeleteSubscription(subscriptionType) {
  emit('delete', {
    email: props.subscriberEmail,
    subscriptionType: subscriptionType
  })
}
</script>

<template>
  <div class="subscription-list">
    <div v-if="isLoading" class="loading">
      ⏳ Загрузка подписок...
    </div>
    
    <div v-else-if="items.length === 0" class="no-subscriptions">
      📭 Нет доступных подписок
    </div>
    
    <div v-else class="subscriptions-container">
      <div v-if="activeSubscriptions.length > 0" class="subscription-section">
        <h3 class="section-title active">
          <span class="icon">✅</span>
          Активные подписки
        </h3>
        <div class="subscriptions-grid">
          <div 
            v-for="subscription in activeSubscriptions" 
            :key="subscription.code"
            class="subscription-card active"
          >
            <div class="subscription-header">
              <h4 class="subscription-title">{{ subscription.title }}</h4>
              <span class="subscription-status active">Активна</span>
            </div>
            
            <p class="subscription-description">{{ subscription.description }}</p>
            
            <div class="subscription-footer">
              <span class="subscription-code">{{ subscription.name }}</span>
              <button 
                class="action-btn delete"
                @click="handleDeleteSubscription(subscription.name)"
                title="Отписаться"
              >
                🗑️ Отписаться
              </button>
            </div>
          </div>
        </div>
      </div>

      <div v-if="inactiveSubscriptions.length > 0" class="subscription-section">
        <h3 class="section-title inactive">
          <span class="icon">⏸️</span>
          Доступные подписки
        </h3>
        <div class="subscriptions-grid">
          <div 
            v-for="subscription in inactiveSubscriptions" 
            :key="subscription.code"
            class="subscription-card inactive"
          >
            <div class="subscription-header">
              <h4 class="subscription-title">{{ subscription.title }}</h4>
              <span class="subscription-status inactive">Неактивна</span>
            </div>
            
            <p class="subscription-description">{{ subscription.description }}</p>
            
            <div class="subscription-footer">
              <span class="subscription-code">{{ subscription.name }}</span>
              <button 
                class="action-btn create"
                @click="handleCreateSubscription(subscription.name)"
                title="Подписаться"
              >
                ✅ Подписаться
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.subscription-list {
  background: #fff;
  border-radius: 8px;
  padding: 20px;
  border: 1px solid #e0e0e0;
}

.loading, .no-subscriptions {
  text-align: center;
  padding: 40px;
  color: #666;
  font-size: 16px;
}

.subscriptions-container {
  display: flex;
  flex-direction: column;
  gap: 30px;
}

.section-title {
  display: flex;
  align-items: center;
  gap: 10px;
  margin: 0 0 15px 0;
  padding-bottom: 10px;
  border-bottom: 2px solid;
  font-size: 18px;
  color: #333;
}

.section-title.active {
  border-bottom-color: #28a745;
}

.section-title.inactive {
  border-bottom-color: #ffc107;
}

.icon {
  font-size: 20px;
}

.subscriptions-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 15px;
}

.subscription-card {
  background: #f8f9fa;
  border-radius: 6px;
  padding: 15px;
  border: 1px solid #dee2e6;
  transition: all 0.3s ease;
}

.subscription-card.active {
  border-left: 4px solid #28a745;
  background: #f0f9f0;
}

.subscription-card.inactive {
  border-left: 4px solid #ffc107;
}

.subscription-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0,0,0,0.1);
}

.subscription-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.subscription-title {
  margin: 0;
  font-size: 16px;
  font-weight: 600;
  color: #333;
}

.subscription-status {
  font-size: 12px;
  padding: 3px 8px;
  border-radius: 12px;
  font-weight: 500;
}

.subscription-status.active {
  background: #d4edda;
  color: #155724;
}

.subscription-status.inactive {
  background: #fff3cd;
  color: #856404;
}

.subscription-description {
  margin: 10px 0;
  font-size: 14px;
  color: #666;
  line-height: 1.4;
}

.subscription-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 15px;
  padding-top: 10px;
  border-top: 1px solid #e9ecef;
}

.subscription-code {
  font-size: 12px;
  color: #6c757d;
  font-family: monospace;
  background: #e9ecef;
  padding: 2px 6px;
  border-radius: 3px;
}

.action-btn {
  padding: 6px 12px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 13px;
  font-weight: 500;
  transition: all 0.3s ease;
  display: flex;
  align-items: center;
  gap: 5px;
}

.action-btn.create {
  background: #28a745;
  color: white;
}

.action-btn.create:hover:not(:disabled) {
  background: #218838;
  transform: translateY(-1px);
}

.action-btn.delete {
  background: #dc3545;
  color: white;
}

.action-btn.delete:hover:not(:disabled) {
  background: #c82333;
  transform: translateY(-1px);
}

.action-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

@media (max-width: 768px) {
  .subscriptions-grid {
    grid-template-columns: 1fr;
  }
  
  .subscription-footer {
    flex-direction: column;
    gap: 10px;
    align-items: flex-start;
  }
  
  .action-btn {
    width: 100%;
    justify-content: center;
  }
}
</style>