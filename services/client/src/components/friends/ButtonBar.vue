<script setup>
import { RouterLink } from 'vue-router'
</script>

<template>
    <div class="button-bar">
        <RouterLink to="/create_friend" class="add-button">
            <span class="button-icon">+</span>
            <span class="button-text">Добавить друга</span>
        </RouterLink>
    </div>
</template>

<style scoped>
.button-bar {
    display: flex;
    justify-content: flex-end;
    padding: 20px 25px;
    width: 100%;
}

.add-button {
    display: inline-flex;
    align-items: center;
    gap: 10px;
    padding: 12px 24px;
    color: rgb(5, 5, 5);
    text-decoration: none;
    border-radius: 8px;
    border: 2px solid #57595c;
    font-size: 1rem;
    font-weight: 600;
    transition: all 0.3s ease;
    box-shadow: 0 2px 8px rgba(0, 123, 255, 0.2);
}

.add-button:hover {
    transform: translateY(-1px);
    box-shadow: 0 4px 12px rgba(0, 123, 255, 0.3);
}

.add-button:active {
    transform: translateY(0);
}

.button-icon {
    font-size: 1.5rem;
    line-height: 1;
    font-weight: 300;
}

.button-text {
    line-height: 1;
}
</style>