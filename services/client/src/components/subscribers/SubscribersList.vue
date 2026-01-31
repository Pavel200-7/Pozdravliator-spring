<script setup>
import { RouterLink } from 'vue-router'
import TableFooter from '../common/table/TableFooter.vue';
import EmptyState from '../common/table/EmptyState.vue';

defineProps({
    items: {
        type: Array,
        default: () => []
    }
})

</script>

<template>
    <div class="table-container">
        <table>            
            <caption><slot></slot></caption>
            <thead>
                <tr>
                    <th scope="col" class="number-column">№</th>
                    <th scope="col" class="name-column">Email</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(item, index) in items" :key="item.id" class="table-row">

                    <td class="number-cell">{{ index + 1 }}</td>
                    <td class="name-cell">
                        <RouterLink 
                        :to="{ 
                            name: 'subscriber_detail', 
                            params: { id: item.id },
                            query: { email: item.email }
                        }"
                        class="table-link"
                        >
                            <div class="subscriber-info">
                                <span class="subscriber-email">{{ item.email }}</span>
                            </div>
                        </RouterLink>
                    </td>

                </tr>

                <EmptyState :items="items"></EmptyState>
            </tbody>

            <TableFooter :items="items" />
        </table>
    </div>
</template>

<style scoped>
.table-container {
    width: 100%;
    overflow-x: auto;
    padding: 0 10px;
}

.number-cell {
    font-weight: 600;
    font-size: 1.1rem;
    text-align: center;
    width: 60px;
}

.table-link {
    text-decoration: none;
    color: inherit;
    display: inline-block;
    transition: all 0.2s ease;
}

.table-link:hover .subscriber-email {
    color: hsla(162, 89%, 18%, 0.678);
    text-decoration: underline;
}

.subscriber-info {
    display: flex;
    align-items: center;
    gap: 12px;
}

.subscriber-email {
    font-weight: 500;
    color: #2d3748;
    font-size: 1rem;
}

@keyframes pulse {
    0% {
        transform: scale(1);
        opacity: 1;
    }
    50% {
        transform: scale(1.05);
        opacity: 0.9;
    }
    100% {
        transform: scale(1);
        opacity: 1;
    }
}

@media (max-width: 768px) {
    .table-container {
        margin: 20px -10px;
        padding: 0 5px;
    }
    
    .table {
        font-size: 0.9rem;
    }
    
    .table th,
    .table td {
        padding: 12px 10px;
    }
    
    .subscriber-info {
        flex-direction: column;
        gap: 6px;
        align-items: flex-start;
    }
}
</style>