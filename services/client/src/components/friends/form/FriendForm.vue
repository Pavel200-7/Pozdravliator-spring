<script setup>
defineProps({
    formData: {
        type: Object,
        required: true
    },
    validationErrors: {
        type: Object,
        default: () => ({})
    },
    isLoading: {
        type: Boolean,
        default: false
    },
    maxDate: {
        type: String,
        default: ''
    },
    minDate: {
        type: String,
        default: ''
    }
})

const emit = defineEmits([
    'update:fullName',
    'update:birthdayDate',
    'update:gender'
])

function updateFullName(event) {
    emit('update:fullName', event.target.value)
}

function updateBirthdayDate(event) {
    emit('update:birthdayDate', event.target.value)
}

function updateGender(value) {
    emit('update:gender', value)
}
</script>

<template>
    <form class="create-form" @submit.prevent="$emit('submit')">
        <div class="form-group" :class="{ 'has-error': validationErrors.fullName }">
            <label for="fullName" class="form-label">
                <span class="required">*</span>
                ФИО
            </label>
            <input
                type="text"
                id="fullName"
                :value="formData.fullName"
                @input="updateFullName"
                placeholder="Введите полное имя"
                :disabled="isLoading"
                class="form-input"
                :class="{ 'error': validationErrors.fullName }"
                autocomplete="name"
                autofocus
            />
            <div v-if="validationErrors.fullName" class="error-message">
                {{ validationErrors.fullName }}
            </div>
        </div>

        <div class="form-group" :class="{ 'has-error': validationErrors.birthdayDate }">
            <label for="birthdayDate" class="form-label">
                <span class="required">*</span>
                Дата рождения
            </label>
            <input
                type="date"
                id="birthdayDate"
                :value="formData.birthdayDate"
                @input="updateBirthdayDate"
                :max="maxDate"
                :min="minDate"
                :disabled="isLoading"
                class="form-input"
                :class="{ 'error': validationErrors.birthdayDate }"
            />
            <div v-if="validationErrors.birthdayDate" class="error-message">
                {{ validationErrors.birthdayDate }}
            </div>
        </div>

        <div class="form-group">
            <label class="form-label">
                <span class="required">*</span>
                Пол
            </label>
            <div class="gender-options">
                <label class="gender-option" :class="{ 'selected': formData.gender }">
                    <input
                        type="radio"
                        :checked="formData.gender"
                        @change="updateGender(true)"
                        :disabled="isLoading"
                        class="gender-radio"
                    />
                    <span class="gender-label">
                        <span class="gender-icon">♂</span>
                        <span class="gender-text">Мужской</span>
                    </span>
                </label>
                
                <label class="gender-option" :class="{ 'selected': !formData.gender }">
                    <input
                        type="radio"
                        :checked="!formData.gender"
                        @change="updateGender(false)"
                        :disabled="isLoading"
                        class="gender-radio"
                    />
                    <span class="gender-label">
                        <span class="gender-icon">♀</span>
                        <span class="gender-text">Женский</span>
                    </span>
                </label>
            </div>
        </div>
    </form>
</template>

<style scoped>
.create-form {
    display: flex;
    flex-direction: column;
    gap: 25px;
}

.form-group {
    display: flex;
    flex-direction: column;
    gap: 8px;
}

.form-group.has-error {
    animation: shake 0.5s ease;
}

.form-label {
    font-weight: 600;
    color: #4a5568;
    font-size: 0.95rem;
    display: flex;
    align-items: center;
    gap: 4px;
}

.required {
    color: #e53e3e;
}

.form-input {
    padding: 12px 15px;
    border: 2px solid #e2e8f0;
    border-radius: 10px;
    font-size: 1rem;
    transition: all 0.3s ease;
    background: #f8fafc;
}

.form-input:focus {
    outline: none;
    border-color: #4299e1;
    background: white;
    box-shadow: 0 0 0 3px rgba(66, 153, 225, 0.15);
}

.form-input.error {
    border-color: #fc8181;
    background: #fff5f5;
}

.form-input.error:focus {
    box-shadow: 0 0 0 3px rgba(252, 129, 129, 0.15);
}

.form-input:disabled {
    background: #edf2f7;
    cursor: not-allowed;
    opacity: 0.7;
}

.error-message {
    color: #e53e3e;
    font-size: 0.875rem;
    margin-top: 4px;
    padding-left: 5px;
    border-left: 3px solid #fc8181;
}

.gender-options {
    display: flex;
    gap: 15px;
    margin-top: 5px;
}

.gender-option {
    flex: 1;
    border: 2px solid #e2e8f0;
    border-radius: 10px;
    padding: 3px;
    cursor: pointer;
    transition: all 0.3s ease;
    background: #f8fafc;
}

.gender-option:hover {
    border-color: #cbd5e0;
    background: #edf2f7;
}

.gender-option.selected {
    border-color: #4299e1;
    background: rgba(66, 153, 225, 0.1);
    box-shadow: 0 0 0 3px rgba(66, 153, 225, 0.1);
}

.gender-radio {
    display: none;
}

.gender-label {
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 10px;
    font-weight: 600;
    color: #4a5568;
}

.gender-icon {
    font-size: 1.5rem;
}

.gender-option.selected .gender-label {
    color: #2b6cb0;
}


@media (max-width: 768px) {
    .gender-options {
        flex-direction: column;
    }
}
</style>