<script setup>
import { ref, reactive } from 'vue'
import axios from 'axios'
import { API_CONFIG } from '@/config/api'


const props = defineProps({
  onSuccess: {
    type: Function,
    default: () => {}
  }
})

const emit = defineEmits(['success'])

const state = reactive({
  email: '',
  isLoading: false,
  error: null,
  success: false
})

const showForm = ref(false)

async function handleSubmit() {
  if (!state.email.trim()) {
    state.error = 'Введите email'
    return
  }

  state.isLoading = true
  state.error = null
  state.success = false

  try {
    const response = await axios.post(`${API_CONFIG.BASE_URL}/api/v1/subscriber`, {
      email: state.email
    })

    state.success = true
    state.email = ''
    showForm.value = false
    
    emit('success')
    props.onSuccess()
    
    setTimeout(() => {
      state.success = false
    }, 500)
    
  } catch (err) {
    if (err.response?.data?.validationErrors?.email) {
      state.error = err.response.data.validationErrors.email
    } else if (err.response?.data?.message) {
      state.error = err.response.data.message
    } else {
      state.error = 'Ошибка при создании подписчика'
    }
  } finally {
    state.isLoading = false
  }
}

function toggleForm() {
  showForm.value = !showForm.value
}
</script>

<template>
  <div class="create-subscriber">
    <div class="action-header">
      <button 
        class="action-toggle-btn"
        :class="{ active: showForm }"
        @click="toggleForm"
      >
        <span class="icon">➕</span>
        <span class="label">Добавить подписчика</span>
      </button>
    </div>
    
    <div v-if="showForm" class="action-form">
      <div class="form-group">
        <input
          v-model="state.email"
          type="email"
          placeholder="Введите email для добавления"
          class="email-input"
          :disabled="state.isLoading"
          @keyup.enter="handleSubmit"
        />
        <button
          class="submit-btn"
          :disabled="state.isLoading || !state.email.trim()"
          @click="handleSubmit"
        >
          <span v-if="state.isLoading">⏳</span>
          <span v-else>✅</span>
        </button>
      </div>
      
      <div v-if="state.error" class="error-message">
        ⚠️ {{ state.error }}
      </div>
      
      <div v-if="state.success" class="success-message">
        ✅ Подписчик успешно добавлен
      </div>
      
      <div class="form-actions">
        <button 
          class="cancel-btn"
          @click="toggleForm"
          :disabled="state.isLoading"
        >
          Отмена
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.create-subscriber {
  flex: 1;
}

.action-header {
  margin-bottom: 10px;
}

.action-toggle-btn {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 10px 15px;
  background: #007bff;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 14px;
  transition: all 0.3s ease;
  width: 100%;
  justify-content: center;
}

.action-toggle-btn:hover {
  background: #0056b3;
  transform: translateY(-1px);
}

.action-toggle-btn.active {
  background: #0056b3;
  box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.2);
}

.icon {
  font-size: 16px;
}

.label {
  font-weight: 500;
}

.action-form {
  padding: 15px;
  background: white;
  border-radius: 6px;
  border: 1px solid #dee2e6;
}

.form-group {
  display: flex;
  gap: 10px;
  margin-bottom: 10px;
}

.email-input {
  flex: 1;
  padding: 8px 12px;
  border: 1px solid #ced4da;
  border-radius: 4px;
  font-size: 14px;
  transition: border-color 0.3s;
}

.email-input:focus {
  outline: none;
  border-color: #007bff;
  box-shadow: 0 0 0 2px rgba(0, 123, 255, 0.25);
}

.submit-btn {
  padding: 8px 15px;
  background: #28a745;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  min-width: 50px;
}

.submit-btn:hover:not(:disabled) {
  background: #218838;
}

.submit-btn:disabled {
  background: #6c757d;
  cursor: not-allowed;
  opacity: 0.6;
}

.error-message {
  color: #dc3545;
  font-size: 13px;
  padding: 5px;
  background: #f8d7da;
  border-radius: 4px;
  border: 1px solid #f5c6cb;
  margin-bottom: 10px;
}

.success-message {
  color: #155724;
  font-size: 13px;
  padding: 5px;
  background: #d4edda;
  border-radius: 4px;
  border: 1px solid #c3e6cb;
  margin-bottom: 10px;
}

.form-actions {
  display: flex;
  justify-content: flex-end;
  margin-top: 10px;
}

.cancel-btn {
  padding: 6px 12px;
  background: #6c757d;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 13px;
}

.cancel-btn:hover:not(:disabled) {
  background: #545b62;
}

.cancel-btn:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}
</style>