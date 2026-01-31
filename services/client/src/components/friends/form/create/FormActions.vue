<script setup>
import { RouterLink } from 'vue-router'

defineProps({
    isLoading: {
        type: Boolean,
        default: false
    },
    isFormValid: {
        type: Boolean,
        default: false
    },
    submitText: {
        type: String,
        default: 'Создать друга'
    },
    resetText: {
        type: String,
        default: 'Сбросить'
    },
    cancelText: {
        type: String,
        default: 'Назад к списку'
    }
})

const emit = defineEmits(['submit', 'reset'])
</script>

<template>
    <div class="form-actions">
        <button
            type="button"
            @click="emit('submit')"
            :disabled="!isFormValid || isLoading"
            class="submit-btn"
            :class="{ 'loading': isLoading }"
        >
            <span v-if="isLoading" class="btn-loading">
                <span class="spinner"></span>
                Создание...
            </span>
            <span v-else class="btn-text">
                <span class="btn-icon">➕</span>
                {{ submitText }}
            </span>
        </button>
        
        <button
            type="button"
            @click="emit('reset')"
            :disabled="isLoading"
            class="reset-btn"
        >
            <span class="btn-icon">↺</span>
            {{ resetText }}
        </button>
        
        <RouterLink to="/friends" class="cancel-link">
            <span class="btn-icon">←</span>
            {{ cancelText }}
        </RouterLink>
    </div>
</template>

<style scoped>
.form-actions {
    display: flex;
    gap: 15px;
    margin-top: 20px;
    flex-wrap: wrap;
}

.submit-btn, .reset-btn, .cancel-link {
    padding: 12px 24px;
    border-radius: 10px;
    font-size: 1rem;
    font-weight: 600;
    cursor: pointer;
    transition: all 0.3s ease;
    display: inline-flex;
    align-items: center;
    justify-content: center;
    gap: 8px;
    text-decoration: none;
    border: 2px solid transparent;
}

.submit-btn {
    background: linear-gradient(135deg, #4299e1 0%, #3182ce 100%);
    color: white;
    flex: 1;
    min-width: 160px;
}

.submit-btn:hover:not(:disabled) {
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(66, 153, 225, 0.3);
}

.submit-btn:disabled {
    opacity: 0.6;
    cursor: not-allowed;
    transform: none !important;
}

.submit-btn.loading {
    background: #a0aec0;
}

.reset-btn {
    background: #e2e8f0;
    color: #4a5568;
    border-color: #cbd5e0;
}

.reset-btn:hover:not(:disabled) {
    background: #cbd5e0;
}

.cancel-link {
    background: white;
    color: #718096;
    border: 2px solid #e2e8f0;
}

.cancel-link:hover {
    background: #f7fafc;
    border-color: #cbd5e0;
}

.btn-loading {
    display: flex;
    align-items: center;
    gap: 8px;
}

.spinner {
    width: 16px;
    height: 16px;
    border: 2px solid white;
    border-top-color: transparent;
    border-radius: 50%;
    animation: spin 1s linear infinite;
}

@keyframes spin {
    to { transform: rotate(360deg); }
}

@media (max-width: 768px) {
    .form-actions {
        flex-direction: column;
    }
    
    .submit-btn, .reset-btn, .cancel-link {
        width: 100%;
    }
}
</style>