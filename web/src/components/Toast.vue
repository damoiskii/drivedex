<template>
    <Transition name="toast">
        <div v-if="visible" :class="[
            'fixed top-4 right-4 px-6 py-4 rounded-lg shadow-lg flex items-center gap-3 z-50 min-w-[300px]',
            typeClasses
        ]">
            <svg class="w-6 h-6 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path v-if="type === 'success'" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                    d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                <path v-else-if="type === 'error'" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                    d="M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z" />
                <path v-else-if="type === 'warning'" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                    d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z" />
                <path v-else stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                    d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
            </svg>
            <div class="flex-1">
                <p class="font-semibold">{{ message }}</p>
                <p v-if="description" class="text-sm mt-1 opacity-90">{{ description }}</p>
            </div>
            <button @click="close" class="flex-shrink-0 hover:opacity-70 transition-opacity">
                <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                </svg>
            </button>
        </div>
    </Transition>
</template>

<script setup>
import { ref, computed, watch } from 'vue'

const props = defineProps({
    type: {
        type: String,
        default: 'info',
        validator: (value) => ['success', 'error', 'warning', 'info'].includes(value)
    },
    message: {
        type: String,
        required: true
    },
    description: {
        type: String,
        default: ''
    },
    duration: {
        type: Number,
        default: 3000
    },
    show: {
        type: Boolean,
        default: false
    }
})

const emit = defineEmits(['close'])

const visible = ref(props.show)
let timer = null

const typeClasses = computed(() => {
    const classes = {
        success: 'bg-green-50 text-green-800 border-l-4 border-green-500',
        error: 'bg-red-50 text-red-800 border-l-4 border-red-500',
        warning: 'bg-yellow-50 text-yellow-800 border-l-4 border-yellow-500',
        info: 'bg-blue-50 text-blue-800 border-l-4 border-blue-500'
    }
    return classes[props.type]
})

const close = () => {
    visible.value = false
    if (timer) clearTimeout(timer)
    emit('close')
}

watch(() => props.show, (newVal) => {
    visible.value = newVal
    if (newVal && props.duration > 0) {
        if (timer) clearTimeout(timer)
        timer = setTimeout(close, props.duration)
    }
})

// Auto close on mount if show is true
if (visible.value && props.duration > 0) {
    timer = setTimeout(close, props.duration)
}
</script>

<style scoped>
.toast-enter-active,
.toast-leave-active {
    transition: all 0.3s ease;
}

.toast-enter-from {
    transform: translateX(100%);
    opacity: 0;
}

.toast-leave-to {
    transform: translateY(-20px);
    opacity: 0;
}
</style>
