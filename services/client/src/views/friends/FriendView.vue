<script setup>
import { ref, reactive, onMounted, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'
import FriendForm from '../../components/friends/form/FriendForm.vue'
import ImageDisplay from '../../components/friends/image/ImageDisplay.vue'
import ImageUpload from '../../components/friends/image/ImageUpload.vue'
import ActionButtons from '../../components/friends/form/update/ActionButtons.vue'
import SuccessMessage from '../../components/friends/form/SuccessMessage.vue'
import ErrorMessage from '../../components/friends/form/ErrorMessage.vue'
import FormPreloader from '../../components/friends/form/FormPreloader.vue'
import { API_CONFIG } from '@/config/api'


const route = useRoute()
const router = useRouter()

const friendId = computed(() => route.params.id)

const friendData = reactive({
    id: '',
    fullName: '',
    birthdayDate: '',
    gender: true
})
const isLoading = ref(false)
const error = ref(null)
const success = ref(null)
const isEditing = ref(false)
const hasImage = ref(false)
const validationErrors = ref({})

async function loadFriendData() {
    isLoading.value = true
    error.value = null
    
    try {
        const response = await axios.get(`${API_CONFIG.BASE_URL}/api/v1/friend/${friendId.value}`)
        Object.assign(friendData, response.data)
        await checkImageExists()
    } catch (err) {
        error.value = err.response?.data?.message || 'Не удалось загрузить данные друга'
        console.error('Ошибка загрузки данных друга:', err)
    } finally {
        isLoading.value = false
    }
}

async function checkImageExists() {
    try {
        await axios.get(`${API_CONFIG.BASE_URL}/api/v1/image/${friendId.value}`)
        hasImage.value = true
    } catch (err) {
        if (err.response?.status === 404) {
            hasImage.value = false
        }
    }
}

async function updateFriend() {
    isLoading.value = true
    error.value = null
    validationErrors.value = {}
    
    try {
        const response = await axios.put(`${API_CONFIG.BASE_URL}/api/v1/friend`, {
            id: friendId.value,
            fullName: friendData.fullName.trim(),
            birthdayDate: friendData.birthdayDate,
            gender: friendData.gender
        })
        
        if (response.data.succeed) {
            success.value = 'Данные успешно обновлены'
            isEditing.value = false
            setTimeout(() => success.value = null, 3000)
        }
    } catch (err) {
        if (err.response?.status === 400 && err.response?.data?.validationErrors) {
            validationErrors.value = err.response.data.validationErrors
            error.value = err.response.data.message || 'Ошибка валидации'
        } else {
            error.value = err.response?.data?.message || err.message || 'Ошибка при обновлении данных'
        }
        console.error('Ошибка обновления данных:', err)
    } finally {
        isLoading.value = false
    }
}

async function deleteFriend() {
    if (!confirm('Вы уверены, что хотите удалить этого друга?')) {
        return
    }
    
    isLoading.value = true
    error.value = null
    
    try {
        const response = await axios.delete(`${API_CONFIG.BASE_URL}/api/v1/friend`, {
            data: { id: friendId.value }
        })
        
        if (response.data.succeed) {
            success.value = 'Друг успешно удален'
            setTimeout(() => {
                router.push('/friends')
            }, 500)
        }
    } catch (err) {
        if (err.response?.status === 404) {
            error.value = 'Друг не найден'
        } else {
            error.value = err.response?.data?.message || err.message || 'Ошибка при удалении друга'
        }
        console.error('Ошибка удаления друга:', err)
    } finally {
        isLoading.value = false
    }
}

function onImageUploaded() {
    hasImage.value = true
    success.value = 'Изображение успешно загружено'
    setTimeout(() => success.value = null, 3000)
}


function toggleEditMode() {
    isEditing.value = !isEditing.value
    if (!isEditing.value) {
        loadFriendData()
        validationErrors.value = {}
        error.value = null
    }
}

onMounted(() => {
    if (friendId.value) {
        loadFriendData()
    }
})
</script>

<template>
    <div class="friend-detail-container">
        <div class="page-header">
            <h1>Информация о друге</h1>
            <RouterLink to="/friends" class="back-link">
                ← Назад к списку друзей
            </RouterLink>
        </div>

        <FormPreloader v-if="isLoading && !friendData.id" message="Загрузка данных..." />

        <SuccessMessage 
            v-if="success" 
            :message="success" 
            :auto-hide="true"
            @hide="success = null"
        />
        
        <ErrorMessage 
            v-if="error" 
            :message="error"
            :dismissible="true"
            @dismiss="error = null"
        />

        <div v-if="friendData.id && !isLoading" class="friend-detail-content">
            <div class="image-section">
                <ImageDisplay
                    :friend-id="friendId"
                    :has-image="hasImage"
                    :friend-name="friendData.fullName"
                    :gender="friendData.gender"
                />
                
                <ImageUpload
                    :friend-id="friendId"
                    :has-image="hasImage"
                    @uploaded="onImageUploaded"
                />
            </div>

            <div class="info-section">
                <FriendForm
                    :form-data="friendData"
                    :validation-errors="validationErrors"
                    :is-loading="isLoading"
                    :is-editing="isEditing"
                    :max-date="new Date().toISOString().split('T')[0]"
                    :min-date="getMinDate()"
                    @update:full-name="friendData.fullName = $event"
                    @update:birthday-date="friendData.birthdayDate = $event"
                    @update:gender="friendData.gender = $event"
                />

                <ActionButtons
                    :is-loading="isLoading"
                    :is-editing="isEditing"
                    :friend-id="friendId"
                    @edit="toggleEditMode"
                    @save="updateFriend"
                    @cancel="toggleEditMode"
                    @delete="deleteFriend"
                />
            </div>
        </div>

        <div v-if="!isLoading && !friendData.id && !error" class="not-found">
            <div class="not-found-icon">❓</div>
            <h2>Друг не найден</h2>
            <p>Пользователь с ID {{ friendId }} не существует.</p>
            <RouterLink to="/friends" class="back-btn">
                Вернуться к списку друзей
            </RouterLink>
        </div>
    </div>
</template>

<script>
function getMinDate() {
    const date = new Date()
    date.setFullYear(date.getFullYear() - 100)
    return date.toISOString().split('T')[0]
}
</script>

<style scoped>
.friend-detail-container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
}

.page-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 30px;
    padding-bottom: 20px;
    border-bottom: 2px solid #e2e8f0;
}

.page-header h1 {
    margin: 0;
    color: #2d3748;
    font-size: 2rem;
}

.back-link {
    color: #4299e1;
    text-decoration: none;
    font-weight: 500;
    display: flex;
    align-items: center;
    gap: 5px;
    transition: color 0.2s;
}

.back-link:hover {
    color: #3182ce;
    text-decoration: underline;
}

.friend-detail-content {
    display: grid;
    grid-template-columns: 350px 1fr;
    gap: 40px;
    margin-top: 20px;
}

.image-section {
    display: flex;
    flex-direction: column;
    gap: 20px;
}

.info-section {
    display: flex;
    flex-direction: column;
    gap: 30px;
}

.not-found {
    text-align: center;
    padding: 60px 20px;
    background: #f7fafc;
    border-radius: 12px;
    margin-top: 40px;
}

.not-found-icon {
    font-size: 4rem;
    margin-bottom: 20px;
    opacity: 0.7;
}

.not-found h2 {
    color: #2d3748;
    margin-bottom: 10px;
}

.not-found p {
    color: #718096;
    margin-bottom: 20px;
}

.back-btn {
    display: inline-block;
    padding: 12px 24px;
    background: #4299e1;
    color: white;
    text-decoration: none;
    border-radius: 8px;
    font-weight: 600;
    transition: all 0.2s;
}

.back-btn:hover {
    background: #3182ce;
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(66, 153, 225, 0.3);
}

@media (max-width: 968px) {
    .friend-detail-content {
        grid-template-columns: 1fr;
        gap: 30px;
    }
    
    .image-section {
        max-width: 400px;
        margin: 0 auto;
    }
}

@media (max-width: 768px) {
    .page-header {
        flex-direction: column;
        align-items: flex-start;
        gap: 15px;
    }
    
    .page-header h1 {
        font-size: 1.8rem;
    }
}
</style>