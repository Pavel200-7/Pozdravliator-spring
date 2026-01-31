<script setup>
import { onMounted, reactive } from 'vue';
import axios from 'axios'
import FiltersPanel from '@/components/common/filter/FiltersPanel.vue'
import ButtonBar from '@/components/friends/ButtonBar.vue'
import FriendsList from '@/components/friends/FriendsList.vue';
import { API_CONFIG } from '@/config/api'



const state = reactive({
    search: "",
    sortBy: "ID",
    sortDirection: "ASCENDING",
    pageSize: 25,
    page: 0,

    items: [],
    hasNext: true,
    hasPrev: false,
    total: 0,
    totalPages: 0,

    isLoading: false,
    error: null
})


const sortByOptions = [
  { value: 'ID', label: 'ID', icon: '🔢' },
  { value: 'FULL_NAME', label: 'Имя', icon: '👤' },
  { value: 'BIRTHDAY_DATE', label: 'Дата рождения', icon: '🎂' },
  { value: 'GENDER', label: 'Пол', icon: '⚧' },
]

const searchDescription = "Поиск по имени..."

async function fetchFriends() {
    state.isLoading = true
    state.error = null

    try {
        const response = await axios.post(`${API_CONFIG.BASE_URL}/api/v1/friend/all`, {
            search: state.search,
            sortBy: state.sortBy,
            sortDirection: state.sortDirection,
            pageSize: state.pageSize,
            page: state.page
        })

        Object.assign(state, {
            items: response.data.items || [],
            hasNext: response.data.hasNext || false,
            hasPrev: response.data.hasPrev || false,
            total: response.data.total || 0,
            totalPages: response.data.totalPages || 0,
            isLoading: false
        })
    } catch(err) {
        state.error = err.message
        state.isLoading = false
        console.error('Ошибка при получении данных:', err)
    } finally {
        state.isLoading = false
    }
}

function onSearch() {
    state.page = 0 
    fetchFriends()
}

function onPrev() {
    fetchFriends()
}

function onNext() {
    fetchFriends()
}

function onFirst() {
    fetchFriends()
}

function onLast() {
    fetchFriends()
}

function onRefresh() {
    state.page = 0
    fetchFriends()
}


onMounted(() => {
    fetchFriends()
    console.log()
})

</script>

<template> 

    <FiltersPanel
        v-model:search="state.search"
        v-model:sortBy="state.sortBy"
        v-model:sortDirection="state.sortDirection"
        v-model:pageSize="state.pageSize"
        v-model:page="state.page"
        :has-next="state.hasNext"
        :has-prev="state.hasPrev"
        :total="state.total"
        :total-pages="state.totalPages"
        :is-loading="state.isLoading"
        :sort-by-options="sortByOptions"
        :search-description="searchDescription"
        @search="onSearch"
        @prev="onPrev"
        @next="onNext"
        @first="onFirst"
        @last="onLast"
        @refresh="onRefresh"
    />

    <ButtonBar />

    <FriendsList v-if="!isLoading && !error" 
        :items="state.items" 
        :limit="state.pageSize">
    </FriendsList> 

</template>

<style>
</style>