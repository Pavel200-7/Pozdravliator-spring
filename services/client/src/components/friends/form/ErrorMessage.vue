<script setup>
defineProps({
    message: {
        type: String,
        required: true
    },
    type: {
        type: String,
        default: 'form', 
        validator: (value) => ['form', 'field'].includes(value)
    },
    field: {
        type: String,
        default: ''
    }
})
</script>

<template>
    <div v-if="type === 'form'" class="form-error">
        <div class="error-icon">⚠️</div>
        <div class="error-text">{{ message }}</div>
    </div>
    
    <div v-else-if="type === 'field'" class="field-error">
        {{ message }}
    </div>
</template>

<style scoped>
.form-error {
    background: #fff5f5;
    border: 1px solid #fed7d7;
    border-radius: 10px;
    padding: 15px;
    display: flex;
    align-items: center;
    gap: 12px;
    margin-top: 10px;
}

.error-icon {
    font-size: 1.5rem;
    color: #e53e3e;
    flex-shrink: 0;
}

.error-text {
    color: #742a2a;
    font-weight: 500;
}

.field-error {
    color: #e53e3e;
    font-size: 0.875rem;
    margin-top: 4px;
    padding-left: 5px;
    border-left: 3px solid #fc8181;
}
</style>