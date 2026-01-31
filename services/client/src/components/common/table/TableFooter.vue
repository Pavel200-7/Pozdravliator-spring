<script setup>
defineProps({
    items: {
        type: Array,
        default: () => []
    }
})

function getCurrentTime() {
    return new Date().toLocaleTimeString('ru-RU', {
        hour: '2-digit',
        minute: '2-digit'
    })
}
</script>

<template>
<tfoot v-if="items.length > 0">
    <tr>
        <td colspan="4" class="table-footer">
            <div class="footer-info">
                <span class="total-count">Всего: {{ items.length }} записей</span>
                <span class="last-updated">Обновлено: {{ getCurrentTime() }}</span>
            </div>
        </td>
    </tr>
</tfoot>   
</template>

<style scoped>
.table-footer {
    padding: 16px;
    background: #f8fafc;
    border-top: 1px solid #e2e8f0;
}

.footer-info {
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 0.875rem;
    color: #718096;
}

.total-count {
    font-weight: 600;
    color: #4a5568;
}

.last-updated {
    font-style: italic;
}

@media (max-width: 768px) {   
    .footer-info {
        flex-direction: column;
        gap: 8px;
        text-align: center;
    }
}
</style>
