<script setup>

defineProps({
  modelValue: Number,  
  max: {
    type: Number,
    default: 100
  },
  min: {
    type: Number,
    default: 1
  },
  step: {
    type: Number,
    default: 1
  },
  isLoading: Boolean
})

const emit = defineEmits([
  'update:modelValue',  
  'change',                         
])

function onSliderChange(event) {
  const value = Number(event.target.value)
  emit('update:modelValue', value)
  emit('change', value)
}

</script>


<template>
    <div class="slider-container">
        <div class="slider-wrapper">
            <input 
                type="range" 
                :value="modelValue"
                :min="min" 
                :max="max" 
                :step="step"
                class="slider"
                @input="onSliderChange"
            />
        </div>
    </div>
</template>

<style scoped>
.slider-container {
    background: #f8f9fa;
    border-radius: 12px;
    padding: 5px;
    box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}

.slider-wrapper {
    margin: 24px 0;
}

.slider {
    width: 100%;
    height: 8px;
    border-radius: 4px;
    background: linear-gradient(to right, #007bff 0%, #007bff var(--value-percent), #e9ecef var(--value-percent), #e9ecef 100%);
    outline: none;
}
</style>