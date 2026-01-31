<script setup>
import { onMounted, ref } from 'vue';
import axios from 'axios'
import SliderControls from '../components/common/SliderControls.vue'
import LoadingBoard from '@/components/common/LoadingBoard.vue';
import ErrorBoard from '@/components/common/ErrorBoard.vue';
import FriendsList from '@/components/friends/FriendsList.vue';
import { API_CONFIG } from '@/config/api'


const items = ref([])
const isLoading = ref(false)
const error = ref(null)
const limit = ref(10)

async function fetchNearestBirthdays() {
    isLoading.value = true
    error.value = null

    try {
        const response = await axios.post(`${API_CONFIG.BASE_URL}/api/v1/friend/nearest`, {
            limit: limit.value
        })
        items.value = response.data.items || []
    } catch (err) {
        error.value = err.message
        console.error('Ошибка при получении данных:', err)
    } finally {
        isLoading.value = false
    }
}


function onLimitChange(value) {
    limit.value = value
    fetchNearestBirthdays()
}


onMounted(() => {
    fetchNearestBirthdays()
})

</script>

<template>
    <div class="view-content">
        <h1 class="page-description">Ближайшие дни рождения.</h1>

        <SliderControls 
            v-model="limit"
            :max="100"
            :min="1"
            :step="1"
            :is-loading="isLoading"
            @change="onLimitChange"
        />

        <LoadingBoard v-if="isLoading"></LoadingBoard>  
        
        <ErrorBoard v-if="error" 
            :error="error" 
        />
        
        <FriendsList v-if="!isLoading && !error" 
            :items="items" 
            :limit="limit">
            Топ {{ limit }} ближайших дней рождения
        </FriendsList>

    </div>
</template>

