<script setup>
import { RouterLink } from 'vue-router'

defineProps({
    redirectPath: {
        type: String,
        default: '/friends'
    },
    title: {
        type: String,
        default: 'Друг успешно создан!'
    },
    message: {
        type: String,
        default: 'Перенаправление на страницу списка друзей'
    }
})
</script>

<template>
    <div class="success-message">
        <div class="success-icon">✅</div>
        <div class="success-content">
            <h3>{{ title }}</h3>
            <p>{{ message }}</p>
            <RouterLink :to="redirectPath" class="success-link">
                Перейти к списку друзей →
            </RouterLink>
        </div>
    </div>
</template>

<style scoped>
.success-message {
    background: linear-gradient(135deg, #38b2ac 0%, #319795 100%);
    color: white;
    padding: 25px;
    border-radius: 12px;
    margin-bottom: 30px;
    display: flex;
    align-items: flex-start;
    gap: 15px;
    animation: slideIn 0.5s ease;
}

.success-icon {
    font-size: 2rem;
    flex-shrink: 0;
}

.success-content h3 {
    margin: 0 0 10px 0;
    font-size: 1.3rem;
}

.success-content p {
    margin: 0 0 15px 0;
    opacity: 0.9;
}

.success-link {
    color: white;
    text-decoration: none;
    font-weight: 600;
    display: inline-flex;
    align-items: center;
    gap: 5px;
    border: 2px solid rgba(255, 255, 255, 0.3);
    padding: 8px 16px;
    border-radius: 8px;
    transition: all 0.2s;
}

.success-link:hover {
    background: rgba(255, 255, 255, 0.1);
    border-color: white;
}

@keyframes slideIn {
    from {
        opacity: 0;
        transform: translateY(-20px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}
</style>