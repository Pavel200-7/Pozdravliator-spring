<script setup>
defineProps({
    isLoading: {
        type: Boolean,
        default: false
    },
    isEditing: {
        type: Boolean,
        default: false
    },
    friendId: {
        type: String,
        default: ''
    },
    showDelete: {
        type: Boolean,
        default: true
    }
})

const emit = defineEmits(['edit', 'save', 'cancel', 'delete'])
</script>

<template>
    <div class="action-buttons">
        <div v-if="isEditing" class="editing-buttons">
            <button
                @click="emit('save')"
                :disabled="isLoading"
                class="btn btn-save"
            >
                <span v-if="isLoading" class="btn-loading">
                    <span class="spinner"></span>
                    Сохранение...
                </span>
                <span v-else class="btn-content">
                    <span class="btn-icon">💾</span>
                    Сохранить изменения
                </span>
            </button>
            
            <button
                @click="emit('cancel')"
                :disabled="isLoading"
                class="btn btn-cancel"
            >
                <span class="btn-icon">↶</span>
                Отменить
            </button>
        </div>

        <div v-else class="view-buttons">
            <button
                @click="emit('edit')"
                :disabled="isLoading"
                class="btn btn-edit"
            >
                <span class="btn-icon">✏️</span>
                Редактировать данные
            </button>
            
            <button
                v-if="showDelete"
                @click="emit('delete')"
                :disabled="isLoading"
                class="btn btn-delete"
            >
                <span class="btn-icon">🗑️</span>
                Удалить друга
            </button>
        </div>
    </div>
</template>

<style scoped>
.action-buttons {
    display: flex;
    flex-direction: column;
    gap: 15px;
    margin-top: 20px;
}

.editing-buttons,
.view-buttons {
    display: flex;
    gap: 12px;
    flex-wrap: wrap;
}

.btn {
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
    border: 2px solid transparent;
    flex: 1;
    min-width: 160px;
}

.btn:hover:not(:disabled) {
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.btn:disabled {
    opacity: 0.6;
    cursor: not-allowed;
    transform: none !important;
}

.btn-save {
    background: linear-gradient(135deg, #48bb78 0%, #38a169 100%);
    color: white;
}

.btn-save:hover:not(:disabled) {
    box-shadow: 0 4px 12px rgba(72, 187, 120, 0.3);
}

.btn-cancel {
    background: #e2e8f0;
    color: #4a5568;
    border-color: #cbd5e0;
}

.btn-cancel:hover:not(:disabled) {
    background: #cbd5e0;
}

.btn-edit {
    background: linear-gradient(135deg, #4299e1 0%, #3182ce 100%);
    color: white;
}

.btn-edit:hover:not(:disabled) {
    box-shadow: 0 4px 12px rgba(66, 153, 225, 0.3);
}

.btn-delete {
    background: linear-gradient(135deg, #f56565 0%, #e53e3e 100%);
    color: white;
}

.btn-delete:hover:not(:disabled) {
    box-shadow: 0 4px 12px rgba(245, 101, 101, 0.3);
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
    .editing-buttons,
    .view-buttons {
        flex-direction: column;
    }
    
    .btn {
        width: 100%;
    }
}
</style>