<script setup>
defineProps({
    message: {
        type: String,
        default: 'Загрузка...'
    }
})
</script>

<template>
    <div class="form-overlay">
        <div class="overlay-spinner"></div>
        <div v-if="message" class="overlay-message">{{ message }}</div>
    </div>
</template>

<style scoped>
.form-overlay {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(255, 255, 255, 0.8);
    border-radius: 16px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    gap: 20px;
    z-index: 10;
}

.overlay-spinner {
    width: 50px;
    height: 50px;
    border: 4px solid #e2e8f0;
    border-top-color: #4299e1;
    border-radius: 50%;
    animation: spin 1s linear infinite;
}

.overlay-message {
    color: #4a5568;
    font-weight: 500;
    font-size: 1rem;
}

@keyframes spin {
    to { transform: rotate(360deg); }
}
</style>