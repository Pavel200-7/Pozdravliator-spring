<script setup>
const props = defineProps({
  modelValue: {
    type: String,
    default: 'ID'
  },
  options: {
    type: Object,
    default: []
  },
  isLoading: {
    type: Boolean,
    default: false
  },
  label: {
    type: String,
    default: 'Сортировать по:'
  }
})

const emit = defineEmits(['update:modelValue', 'change'])


function onChange(event) {
  const value = event.target.value
  emit('update:modelValue', value)
  emit('change', value)
}
</script>

<template>
  <div class="sort-by-select">
    <label class="select-label">
      <span class="select-icon">📊</span>
      {{ label }}
    </label>
    <select
      :value="modelValue"
      @change="onChange"
      :disabled="isLoading"
      class="select-input"
    >
      <option
        v-for="option in options"
        :key="option.value"
        :value="option.value"
      >
        {{ option.icon }} {{ option.label }}
      </option>
    </select>
  </div>
</template>

<style scoped>
.sort-by-select {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.select-label {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 0.9rem;
  color: #4a5568;
  font-weight: 500;
}

.select-icon {
  font-size: 1rem;
}

.select-input {
  padding: 10px 12px;
  border: 1px solid #cbd5e0;
  border-radius: 8px;
  background: white;
  font-size: 0.95rem;
  color: #2d3748;
  cursor: pointer;
  transition: all 0.2s;
}

.select-input:focus {
  outline: none;
  border-color: #4299e1;
  box-shadow: 0 0 0 3px rgba(66, 153, 225, 0.15);
}

.select-input:disabled {
  background-color: #f7fafc;
  cursor: not-allowed;
}
</style>