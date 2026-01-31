<script setup>
    defineProps({
        error: {
            type:  String,
            default: "Error"
        }
    })
</script>

<template>
    <div class="error">Ошибка: {{ error }} </div>
</template>

<style>
.error {
    background: #f8d7da;
    color: #721c24;
    border: 1px solid #f5c6cb;
}

.error {
    text-align: center;
    padding: 20px;
    margin: 20px 0;
    border-radius: 8px;
}

</style>