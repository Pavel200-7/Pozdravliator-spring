<script setup>
import { useDateUtils } from '../../composables/useDateUtils'
import { RouterLink } from 'vue-router'
import TableFooter from '../common/table/TableFooter.vue';
import EmptyState from '../common/table/EmptyState.vue';

const { formatDate, calculateDaysUntilBirthday } = useDateUtils()

defineProps({
    items: {
        type: Array,
        default: () => []
    }
})

function getDaysClass(days) {
    if (typeof days !== 'number') return 'days-default'
    
    if (days === 0) return 'days-today'
    if (days <= 3) return 'days-soon'
    if (days <= 7) return 'days-upcoming'
    if (days <= 30) return 'days-near'
    return 'days-far'
}

function getCurrentTime() {
    return new Date().toLocaleTimeString('ru-RU', {
        hour: '2-digit',
        minute: '2-digit'
    })
}
</script>

<template>
    <div class="table-container">
        <table>            
            <caption><slot></slot></caption>
            <thead>
                <tr>
                    <th scope="col" class="number-column">№</th>
                    <th scope="col" class="name-column">Имя</th>
                    <th scope="col" class="date-column">Дата рождения</th>
                    <th scope="col" class="days-column">Дней до ДР</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(item, index) in items" :key="item.id" class="table-row">

                    <td class="number-cell">{{ index + 1 }}</td>
                    <td>
                        <RouterLink :to="{ name: 'friend_detail', params: { id: item.id } }" 
                            class="table-link"
                        >
                            <div class="person-info">
                                <span class="person-name">{{ item.fullName }}</span>
                                <span class="person-gender" :class="item.gender ? 'male' : 'female'">
                                    {{ item.gender ? '♂' : '♀' }}
                                </span>
                            </div>
                        </RouterLink>
                    </td>
                    <td class="date-cell">
                        <div class="date-wrapper">
                            <span class="date-icon">📅</span>
                            <span class="date-text">{{ formatDate(item.birthdayDate) }}</span>
                        </div>
                    </td>
                    <td class="days-cell">
                        <div class="days-badge" :class="getDaysClass(calculateDaysUntilBirthday(item.birthdayDate))">
                            {{ calculateDaysUntilBirthday(item.birthdayDate) }}
                        </div>
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

.table-link:hover .person-name {
    color: hsla(162, 89%, 18%, 0.678);
    text-decoration: underline;
}

.person-info {
    display: flex;
    align-items: center;
    gap: 12px;
}

.person-name {
    font-weight: 500;
    color: #2d3748;
    font-size: 1rem;
}

.person-gender {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    width: 24px;
    height: 24px;
    border-radius: 50%;
    font-size: 0.85rem;
}

.person-gender.male {
    background: rgba(66, 153, 225, 0.1);
    color: #4299e1;
}

.person-gender.female {
    background: rgba(237, 100, 166, 0.1);
    color: #ed64a6;
}

.date-wrapper {
    display: flex;
    align-items: center;
    gap: 10px;
}

.date-icon {
    font-size: 1.2rem;
}

.date-text {
    font-weight: 500;
    color: #4a5568;
    font-size: 0.95rem;
}

.days-badge {
    display: inline-block;
    padding: 6px 14px;
    border-radius: 20px;
    font-weight: 600;
    font-size: 0.9rem;
    text-align: center;
    min-width: 60px;
    transition: all 0.3s ease;
}

.days-today {
    background: linear-gradient(135deg, #f56565 0%, #ed8936 100%);
    color: white;
    box-shadow: 0 2px 8px rgba(245, 101, 101, 0.3);
    animation: pulse 2s infinite;
}

.days-soon {
    background: linear-gradient(135deg, #ed8936 0%, #ecc94b 100%);
    color: white;
    box-shadow: 0 2px 8px rgba(237, 137, 54, 0.3);
}

.days-upcoming {
    background: linear-gradient(135deg, #38b2ac 0%, #4299e1 100%);
    color: white;
    box-shadow: 0 2px 8px rgba(56, 178, 172, 0.3);
}

.days-near {
    background: linear-gradient(135deg, #4299e1 0%, #667eea 100%);
    color: white;
    box-shadow: 0 2px 8px rgba(66, 153, 225, 0.3);
}

.days-far {
    background: linear-gradient(135deg, #a0aec0 0%, #718096 100%);
    color: white;
    box-shadow: 0 2px 8px rgba(160, 174, 192, 0.3);
}

.days-default {
    background: #f7fafc;
    color: #4a5568;
    border: 1px solid #e2e8f0;
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
    
    .person-info {
        flex-direction: column;
        gap: 6px;
        align-items: flex-start;
    }
    
    .date-wrapper {
        flex-direction: column;
        gap: 4px;
        align-items: flex-start;
    }
    
    .date-icon {
        display: none;
    }
}
</style>