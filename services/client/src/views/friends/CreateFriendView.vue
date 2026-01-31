<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import FriendForm from '../../components/friends/form/FriendForm.vue'
import SuccessMessage from '../../components/friends/form/SuccessMessage.vue'
import ErrorMessage from '../../components/friends/form/ErrorMessage.vue'
import FormPreloader from '../../components/friends/form/FormPreloader.vue'
import FormActions from '../../components/friends/form/create/FormActions.vue'
import { API_CONFIG } from '@/config/api'

const router = useRouter()

const formData = reactive({
    fullName: '',
    birthdayDate: '',
    gender: true
})

const isLoading = ref(false)
const error = ref(null)
const validationErrors = ref({})
const success = ref(false)

async function createFriend() {
    isLoading.value = true
    error.value = null
    validationErrors.value = {}
    success.value = false

    try {
        const response = await axios.post(`${API_CONFIG.BASE_URL}/api/v1/friend`, {
            fullName: formData.fullName.trim(),
            birthdayDate: formData.birthdayDate,
            gender: formData.gender
        })

        if (response.data.succeed) {
            success.value = true
            resetForm()
            setTimeout(() => {
                router.push('/friends')
            }, 500)
        }
    } catch (err) {
        if (err.response?.status === 400 && err.response?.data?.validationErrors) {
            validationErrors.value = err.response.data.validationErrors
            error.value = err.response.data.message || 'Ошибка валидации'
        } else {
            error.value = err.response?.data?.message || err.message || 'Ошибка при создании друга'
        }
        console.error('Ошибка при создании друга:', err)
    } finally {
        isLoading.value = false
    }
}

function resetForm() {
    formData.fullName = ''
    formData.birthdayDate = ''
    formData.gender = true
    validationErrors.value = {}
    error.value = null
}

function isFormValid() {
    return formData.fullName.trim() && formData.birthdayDate
}

function getMaxDate() {
    return new Date().toISOString().split('T')[0]
}

function getMinDate() {
    const date = new Date()
    date.setFullYear(date.getFullYear() - 100)
    return date.toISOString().split('T')[0]
}

function loadFromLocalStorage() {
    const saved = localStorage.getItem('friendCreateForm')
    if (saved) {
        try {
            Object.assign(formData, JSON.parse(saved))
        } catch (e) {
            console.error('Ошибка загрузки из localStorage:', e)
        }
    }
}

loadFromLocalStorage()
</script>

<template>
    <div class="create-friend-container">
        <div class="create-friend-card">
            <div class="card-header">
                <h1>Добавить нового друга</h1>
                <p class="subtitle">Заполните информацию о друге</p>
            </div>

            <SuccessMessage 
                v-if="success"
                :redirect-path="'/friends'"
            />

            <div v-else>
                <FriendForm
                    :form-data="formData"
                    :validation-errors="validationErrors"
                    :is-loading="isLoading"
                    :max-date="getMaxDate()"
                    :min-date="getMinDate()"
                    @update:full-name="formData.fullName = $event"
                    @update:birthday-date="formData.birthdayDate = $event"
                    @update:gender="formData.gender = $event"
                />

                <ErrorMessage 
                    v-if="error && !validationErrors.fullName && !validationErrors.birthdayDate"
                    :message="error"
                    type="form"
                />

                <FormActions
                    :is-loading="isLoading"
                    :is-form-valid="isFormValid()"
                    @submit="createFriend"
                    @reset="resetForm"
                />
            </div>

            <FormPreloader v-if="isLoading" />
        </div>
    </div>
</template>

<style scoped>
.create-friend-container {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    display: grid;
    gap: 30px;
    display: flex;
    justify-content: center;
}

.create-friend-card {
    background: white;
    border-radius: 16px;
    padding: 30px;
    width: 60%;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
    border: 1px solid #e2e8f0;
    position: relative;
}

.card-header {
    margin-bottom: 30px;
    text-align: center;
}

.card-header h1 {
    margin: 0 0 10px 0;
    color: #2d3748;
    font-size: 2rem;
}

.subtitle {
    color: #718096;
    font-size: 1rem;
    margin: 0;
}

@media (max-width: 768px) {
    .create-friend-container {
        grid-template-columns: 1fr;
        padding: 10px;
        gap: 20px;
    }
    
    .create-friend-card {
        padding: 20px;
    }
}
</style>