import { computed } from 'vue'

export function useDateUtils() {
    function formatDate(dateString) {
        if (!dateString) return ''
        try {
            const date = new Date(dateString)
            return date.toLocaleDateString('ru-RU')
        } catch (e) {
            return dateString
        }
    }

    function calculateDaysUntilBirthday(birthdayDate) {
        if (!birthdayDate) return '—'
        
        try {
            const today = new Date()
            const birthDate = new Date(birthdayDate)

            birthDate.setFullYear(today.getFullYear())

            if (birthDate.getDate() === today.getDate() 
                && birthDate.getMonth() === today.getMonth()) {
                return 0;
            }

            if (birthDate < today) {
                birthDate.setFullYear(today.getFullYear() + 1)
            }
            
            const diffTime = birthDate - today
            const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24))
            
            return diffDays
        } catch (e) {
            return '—'
        }
    }

    // Можно добавить реактивную версию
    function useBirthdayCountdown(birthdayDate) {
        return computed(() => calculateDaysUntilBirthday(birthdayDate))
    }

    return {
        formatDate,
        calculateDaysUntilBirthday,
        useBirthdayCountdown
    }
}