<script setup>
defineProps({
    items: {
        type: Array,
        default: () => []
    }
})

</script>

<template>
    <tr v-if="items.length === 0" class="empty-row">
        <td colspan="4" class="no-data">
            <div class="empty-state">
                <span class="empty-icon">📭</span>
                <span class="empty-text">Нет данных для отображения</span>
            </div>
        </td>
    </tr>
</template>

<style scoped>

.empty-state {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 60px 20px;
    color: #a0aec0;
}

.empty-icon {
    font-size: 3rem;
    margin-bottom: 16px;
    opacity: 0.5;
}

.empty-text {
    font-size: 1.1rem;
    font-weight: 500;
}
</style>
