<script setup>
import { ref } from 'vue'
import axios from 'axios'

const props = defineProps({
    friendId: {
        type: String,
        required: true
    },
    hasImage: {
        type: Boolean,
        default: false
    },
    acceptTypes: {
        type: String,
        default: 'image/jpeg,image/png,image/gif,image/webp'
    },
    maxSizeMB: {
        type: Number,
        default: 5
    }
})

const emit = defineEmits(['uploaded'])

const isLoading = ref(false)
const error = ref(null)
const success = ref(null)

async function uploadImage(event) {
    const file = event.target.files[0]
    if (!file) return
    
    if (!validateFile(file)) {
        return
    }
    
    isLoading.value = true
    error.value = null
    success.value = null
    
    const formData = new FormData()
    formData.append('image', file)
    
    try {
        await axios.put(`http://localhost:8080/api/v1/image/${props.friendId}`, formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
        
        success.value = 'Изображение успешно загружено'
        emit('uploaded')
        
        event.target.value = ''
        
        setTimeout(() => success.value = null, 3000)
    } catch (err) {
        error.value = err.response?.data?.message || 'Ошибка при загрузке изображения'
        console.error('Ошибка загрузки изображения:', err)
    } finally {
        isLoading.value = false
    }
}

function validateFile(file) {
    const maxSize = props.maxSizeMB * 1024 * 1024 
    
    if (!props.acceptTypes.split(',').includes(file.type)) {
        error.value = 'Недопустимый формат файла. Разрешены: JPG, PNG, GIF, WebP'
        return false
    }
    
    if (file.size > maxSize) {
        error.value = `Файл слишком большой. Максимальный размер: ${props.maxSizeMB}MB`
        return false
    }
    
    return true
}

function clearError() {
    error.value = null
}
</script>

<template>
    <div class="image-upload">
        <div class="upload-section">
            <label class="upload-label">
                <span class="upload-icon">📤</span>
                <span class="upload-text">Загрузить фотографию</span>
                <input
                    type="file"
                    :accept="acceptTypes"
                    @change="uploadImage"
                    :disabled="isLoading"
                    class="file-input"
                    @click="clearError"
                />
            </label>
            
            <div class="upload-hint">
                Макс. размер: {{ maxSizeMB }}MB (JPG, PNG, GIF, WebP)
            </div>
        </div>

        <ErrorMessage 
            v-if="error"
            :message="error"
            :dismissible="true"
            @dismiss="clearError"
            class="upload-error"
        />
        
        <div v-if="success" class="upload-success">
            <span class="success-icon">✅</span>
            <span class="success-text">{{ success }}</span>
        </div>
        
        <div v-if="isLoading" class="upload-loading">
            <div class="loading-spinner"></div>
            <span>Обработка...</span>
        </div>
    </div>
</template>

<style scoped>
.image-upload {
    display: flex;
    flex-direction: column;
    gap: 15px;
}

.upload-section {
    display: flex;
    flex-direction: column;
    gap: 8px;
}

.upload-label {
    display: inline-flex;
    align-items: center;
    gap: 10px;
    padding: 12px 20px;
    background: linear-gradient(135deg, #4299e1 0%, #3182ce 100%);
    color: white;
    border-radius: 10px;
    cursor: pointer;
    transition: all 0.3s ease;
    font-weight: 600;
    text-align: center;
    justify-content: center;
}

.upload-label:hover:not(:disabled) {
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(66, 153, 225, 0.3);
}

.upload-label:disabled {
    opacity: 0.6;
    cursor: not-allowed;
    transform: none !important;
}

.file-input {
    display: none;
}

.upload-hint {
    font-size: 0.85rem;
    color: #718096;
    text-align: center;
}

.upload-error {
    margin-top: 10px;
}

.upload-success {
    display: flex;
    align-items: center;
    gap: 8px;
    padding: 10px 15px;
    background: #c6f6d5;
    color: #22543d;
    border-radius: 8px;
    font-weight: 500;
}

.success-icon {
    font-size: 1.2rem;
}

.upload-loading {
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 10px;
    padding: 10px;
    color: #718096;
}

.loading-spinner {
    width: 16px;
    height: 16px;
    border: 2px solid #cbd5e0;
    border-top-color: #4299e1;
    border-radius: 50%;
    animation: spin 1s linear infinite;
}

@keyframes spin {
    to { transform: rotate(360deg); }
}
</style>