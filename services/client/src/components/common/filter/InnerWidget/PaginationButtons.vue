<script setup>
const props = defineProps({
  page: {
    type: Number,
    default: 0
  },
  pageSize: {
    type: Number,
    default: 25
  },
  hasNext: {
    type: Boolean,
    default: false
  },
  hasPrev: {
    type: Boolean,
    default: false
  },
  total: {
    type: Number,
    default: 0
  },
  totalPages: {
    type: Number,
    default: 0
  },
  isLoading: {
    type: Boolean,
    default: false
  },
  showPageSize: {
    type: Boolean,
    default: true
  }
})

const emit = defineEmits([
  'update:page',
  'update:pageSize',
  'prev',
  'next',
  'first',
  'last',
  'refresh'
])

const pageSizeOptions = [
  { value: 10, label: '10 записей' },
  { value: 25, label: '25 записей' },
  { value: 50, label: '50 записей' },
  { value: 100, label: '100 записей' }
]

function goToFirstPage() {
  emit('update:page', 0)
  emit('first')
}

function goToPrevPage() {
  if (props.hasPrev) {
    emit('update:page', props.page - 1)
    emit('prev')
  }
}

function goToNextPage() {
  if (props.hasNext) {
    emit('update:page', props.page + 1)
    emit('next')
  }
}

function goToLastPage() {
  emit('update:page', props.totalPages - 1)
  emit('last')
}

function onPageSizeChange(event) {
  const value = parseInt(event.target.value)
  emit('update:pageSize', value)
  emit('refresh')
}
</script>

<template>
  <div class="pagination-controls">
    <div v-if="showPageSize" class="page-size-select">
      <label class="page-size-label">
        <span class="page-size-icon">📄</span>
        На странице:
      </label>
      <select
        :value="pageSize"
        @change="onPageSizeChange"
        :disabled="isLoading"
        class="page-size-input"
      >
        <option
          v-for="option in pageSizeOptions"
          :key="option.value"
          :value="option.value"
        >
          {{ option.label }}
        </option>
      </select>
    </div>

    <div class="navigation-buttons">
      <button
        @click="goToFirstPage"
        :disabled="!hasPrev || isLoading"
        class="nav-btn first-btn"
        title="Первая страница"
      >
        ««
      </button>
      <button
        @click="goToPrevPage"
        :disabled="!hasPrev || isLoading"
        class="nav-btn prev-btn"
        title="Предыдущая страница"
      >
        «
      </button>
      
      <div class="page-info">
        <span class="current-page">{{ page + 1 }}</span>
        <span class="page-separator">/</span>
        <span class="total-pages">{{ totalPages }}</span>
      </div>
      
      <button
        @click="goToNextPage"
        :disabled="!hasNext || isLoading"
        class="nav-btn next-btn"
        title="Следующая страница"
      >
        »
      </button>
      <button
        @click="goToLastPage"
        :disabled="!hasNext || isLoading"
        class="nav-btn last-btn"
        title="Последняя страница"
      >
        »»
      </button>
    </div>

    <div class="pagination-info">
      <span class="info-text">
        Страница {{ page + 1 }} из {{ totalPages }}
      </span>
      <span class="info-text">
        Всего: {{ total }} записей
      </span>
    </div>
  </div>
</template>

<style scoped>
.pagination-controls {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.page-size-select {
  display: flex;
  align-items: center;
  gap: 10px;
}

.page-size-label {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 0.9rem;
  color: #4a5568;
  font-weight: 500;
  white-space: nowrap;
}

.page-size-icon {
  font-size: 1rem;
}

.page-size-input {
  padding: 8px 10px;
  border: 1px solid #cbd5e0;
  border-radius: 8px;
  background: white;
  font-size: 0.9rem;
  color: #2d3748;
  cursor: pointer;
  transition: all 0.2s;
}

.page-size-input:focus {
  outline: none;
  border-color: #4299e1;
  box-shadow: 0 0 0 2px rgba(66, 153, 225, 0.15);
}

.page-size-input:disabled {
  background-color: #f7fafc;
  cursor: not-allowed;
}

.navigation-buttons {
  display: flex;
  align-items: center;
  gap: 8px;
  justify-content: center;
}

.nav-btn {
  padding: 8px 12px;
  background: #edf2f7;
  border: 1px solid #cbd5e0;
  border-radius: 6px;
  font-size: 0.9rem;
  font-weight: 600;
  color: #4a5568;
  cursor: pointer;
  transition: all 0.2s;
  min-width: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.nav-btn:hover:not(:disabled) {
  background: #4299e1;
  color: white;
  border-color: #4299e1;
}

.nav-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.page-info {
  display: flex;
  align-items: center;
  gap: 4px;
  padding: 8px 12px;
  background: #f7fafc;
  border-radius: 6px;
  font-weight: 600;
  min-width: 80px;
  justify-content: center;
}

.current-page {
  color: #2d3748;
}

.page-separator {
  color: #a0aec0;
}

.total-pages {
  color: #718096;
}

.pagination-info {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  gap: 4px;
}

.info-text {
  font-size: 0.9rem;
  color: #718096;
  font-weight: 500;
}

@media (max-width: 768px) {
  .pagination-controls {
    gap: 10px;
  }
  
  .page-size-select {
    flex-direction: column;
    align-items: flex-start;
  }
  
  .navigation-buttons {
    flex-wrap: wrap;
    justify-content: center;
  }
  
  .pagination-info {
    align-items: center;
    text-align: center;
  }
}

@media (prefers-color-scheme: dark) {
  .page-size-label {
    color: #cbd5e0;
  }
  
  .page-size-input {
    background: #1a202c;
    border-color: #4a5568;
    color: #e2e8f0;
  }
  
  .page-size-input:focus {
    border-color: #4299e1;
  }
  
  .page-size-input:disabled {
    background-color: #2d3748;
  }
  
  .nav-btn {
    background: #4a5568;
    border-color: #718096;
    color: #e2e8f0;
  }
  
  .nav-btn:hover:not(:disabled) {
    background: #4299e1;
  }
  
  .page-info {
    background: #4a5568;
  }
  
  .current-page {
    color: #e2e8f0;
  }
  
  .info-text {
    color: #cbd5e0;
  }
}
</style>