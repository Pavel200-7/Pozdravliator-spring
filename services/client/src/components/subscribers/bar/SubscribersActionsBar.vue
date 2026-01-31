<script setup>
import CreateSubscriber from './actions/CreateSubscriber.vue'
import DeleteSubscriber from './actions/DeleteSubscriber.vue'

const emit = defineEmits(['success'])

function handleSuccess() {
  emit('success')
}
</script>

<template>
  <div class="actions-bar">
    <CreateSubscriber @success="handleSuccess" />
    <DeleteSubscriber @success="handleSuccess" />
  </div>
</template>

<style scoped>
.actions-bar {
  display: flex;
  gap: 20px;
  margin-bottom: 20px;
  padding: 15px;
  background: #f8f9fa;
  border-radius: 8px;
  border: 1px solid #e9ecef;
}
</style>