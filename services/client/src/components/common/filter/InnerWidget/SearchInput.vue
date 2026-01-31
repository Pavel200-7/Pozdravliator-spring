<script setup>
import { ref, watch } from 'vue'

const props = defineProps({
  modelValue: {
    type: String,
    default: ''
  },
  isLoading: {
    type: Boolean,
    default: false
  },
  placeholder: {
    type: String,
    default: 'Поиск...'
  }
})

const emit = defineEmits(['refresh'])

const localValue = ref(props.modelValue)

let searchTimeout = null
watch(localValue, (newValue) => {
  clearTimeout(searchTimeout)
  searchTimeout = setTimeout(() => {
      emit('refresh', localValue)
  }, 3000)
})

function onRefresh() {
  emit('refresh', localValue)
}
</script>

<template>
  <div class="search-input-container">
    <div class="search-icon">🔍</div>
    <input
      type="text"
      v-model="localValue"
      :placeholder="placeholder"
      class="search-input"
      :disabled="isLoading"
    />
    <button
      @click="onRefresh"
      :disabled="isLoading"
      class="refresh-btn"
      :title="isLoading ? 'Загрузка...' : 'Обновить'"
    >
      <span v-if="isLoading" class="spinner"></span>
      <span v-else>🔄</span>
    </button>
  </div>
</template>

<style scoped>
.search-input-container {
  display: flex;
  align-items: center;
  gap: 10px;
  flex: 1;
}

.search-icon {
  font-size: 1.2rem;
  color: #718096;
}

.search-input {
  flex: 1;
  padding: 10px 15px;
  border: 1px solid #cbd5e0;
  border-radius: 8px;
  font-size: 1rem;
  transition: all 0.2s;
}

.search-input:focus {
  outline: none;
  border-color: #4299e1;
  box-shadow: 0 0 0 3px rgba(66, 153, 225, 0.15);
}

.search-input:disabled {
  background-color: #f7fafc;
  cursor: not-allowed;
}

.refresh-btn {
  padding: 10px;
  background: #edf2f7;
  border: 1px solid #cbd5e0;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.2s;
  display: flex;
  align-items: center;
  justify-content: center;
  min-width: 44px;
}

.refresh-btn:hover:not(:disabled) {
  background: #e2e8f0;
  transform: rotate(15deg);
}

.refresh-btn:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.spinner {
  width: 16px;
  height: 16px;
  border: 2px solid #718096;
  border-top-color: transparent;
  border-radius: 50%;
  animation: spin 1s linear infinite;
}

@keyframes spin {
  to { transform: rotate(360deg); }
}

@media (prefers-color-scheme: dark) {
  .search-input {
    background: #1a202c;
    border-color: #4a5568;
    color: #e2e8f0;
  }
  
  .search-input:focus {
    border-color: #4299e1;
  }
  
  .search-input:disabled {
    background-color: #2d3748;
  }
  
  .refresh-btn {
    background: #4a5568;
    border-color: #718096;
    color: #e2e8f0;
  }
  
  .refresh-btn:hover:not(:disabled) {
    background: #718096;
  }
}
</style>