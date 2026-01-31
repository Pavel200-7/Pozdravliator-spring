<script setup>
import { ref, onMounted, watch } from 'vue'
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
    friendName: {
        type: String,
        default: ''
    },
    gender: {
        type: Boolean,
        default: true
    }
})

const imageUrl = ref('')
const isLoading = ref(false)
const error = ref(null)

function getImageUrl() {
    return `http://localhost:8080/api/v1/image/${props.friendId}`
}

function getDefaultAvatarUrl() {
    const initials = props.friendName
        .split(' ')
        .map(word => word.charAt(0))
        .join('')
        .toUpperCase()
        .slice(0, 2)
    
    return `https://ui-avatars.com/api/?name=${initials}&background=${props.gender ? '4299e1' : 'e83e8c'}&color=fff&size=400&bold=true`
}

async function loadImage() {
    if (!props.hasImage) {
        imageUrl.value = getDefaultAvatarUrl()
        return
    }
    
    isLoading.value = true
    error.value = null
    
    try {
        const response = await axios.get(getImageUrl(), {
            responseType: 'blob'
        })
        
        const blob = response.data
        imageUrl.value = URL.createObjectURL(blob)
    } catch (err) {
        if (err.response?.status === 404) {
            imageUrl.value = getDefaultAvatarUrl()
        } else {
            error.value = 'Не удалось загрузить изображение'
            console.error('Ошибка загрузки изображения:', err)
            imageUrl.value = getDefaultAvatarUrl()
        }
    } finally {
        isLoading.value = false
    }
}

watch(() => props.hasImage, loadImage)

onMounted(() => {
    loadImage()
})

import { onUnmounted } from 'vue'
onUnmounted(() => {
    if (imageUrl.value && imageUrl.value.startsWith('blob:')) {
        URL.revokeObjectURL(imageUrl.value)
    }
})
</script>

<template>
    <div class="image-display">
        <div class="image-container">
            <div v-if="isLoading" class="image-loading">
                <div class="loading-spinner"></div>
                <span>Загрузка изображения...</span>
            </div>
            
            <img
                v-else
                :src="imageUrl"
                :alt="`Фотография ${friendName}`"
                class="friend-image"
                :class="{ 'default-avatar': !hasImage }"
            />
            
            <div class="image-status" :class="{ 'has-image': hasImage }">
                <span v-if="hasImage" class="status-icon">📷</span>
                <span v-else class="status-icon">👤</span>
                <span class="status-text">
                    {{ hasImage ? 'Фотография загружена' : 'Аватар по умолчанию' }}
                </span>
            </div>
        </div>
        
        <div class="image-info">
            <h3>{{ friendName || 'Имя не указано' }}</h3>
            <p v-if="!hasImage" class="info-text">Загрузите фотографию для этого друга</p>
        </div>
        
        <ErrorMessage 
            v-if="error"
            :message="error"
            type="field"
            class="image-error"
        />
    </div>
</template>

<style scoped>
.image-display {
    display: flex;
    flex-direction: column;
    gap: 15px;
}

.image-container {
    position: relative;
    border-radius: 16px;
    overflow: hidden;
    background: #f7fafc;
    border: 2px solid #e2e8f0;
    aspect-ratio: 1/1;
}

.friend-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
    display: block;
    transition: transform 0.3s ease;
}

.friend-image:hover {
    transform: scale(1.05);
}

.friend-image.default-avatar {
    object-fit: contain;
    padding: 20px;
    background: linear-gradient(135deg, #f7fafc 0%, #edf2f7 100%);
}

.image-loading {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100%;
    gap: 15px;
    color: #718096;
}

.loading-spinner {
    width: 40px;
    height: 40px;
    border: 3px solid #e2e8f0;
    border-top-color: #4299e1;
    border-radius: 50%;
    animation: spin 1s linear infinite;
}

.image-status {
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    background: rgba(0, 0, 0, 0.7);
    color: white;
    padding: 10px 15px;
    display: flex;
    align-items: center;
    gap: 8px;
    font-size: 0.9rem;
    backdrop-filter: blur(4px);
}

.image-status.has-image {
    background: rgba(66, 153, 225, 0.9);
}

.status-icon {
    font-size: 1rem;
}

.status-text {
    font-weight: 500;
}

.image-info {
    text-align: center;
}

.image-info h3 {
    margin: 0 0 5px 0;
    color: #2d3748;
    font-size: 1.3rem;
}

.info-text {
    margin: 0;
    color: #718096;
    font-size: 0.9rem;
}

.image-error {
    margin-top: 5px;
}

@keyframes spin {
    to { transform: rotate(360deg); }
}
</style>