<template>
    <div :class="[
        'rounded-full flex items-center justify-center font-semibold',
        sizeClasses,
        colorClasses
    ]">
        {{ initials }}
    </div>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
    name: {
        type: String,
        required: true
    },
    size: {
        type: String,
        default: 'md',
        validator: (value) => ['sm', 'md', 'lg', 'xl'].includes(value)
    },
    color: {
        type: String,
        default: 'indigo',
        validator: (value) => ['indigo', 'purple', 'blue', 'green', 'red', 'yellow', 'pink', 'gray'].includes(value)
    }
})

const initials = computed(() => {
    const parts = props.name.trim().split(' ')
    if (parts.length === 1) {
        return parts[0].charAt(0).toUpperCase()
    }
    return (parts[0].charAt(0) + parts[parts.length - 1].charAt(0)).toUpperCase()
})

const sizeClasses = computed(() => {
    const sizes = {
        sm: 'w-8 h-8 text-xs',
        md: 'w-10 h-10 text-sm',
        lg: 'w-12 h-12 text-base',
        xl: 'w-16 h-16 text-lg'
    }
    return sizes[props.size]
})

const colorClasses = computed(() => {
    const colors = {
        indigo: 'bg-indigo-100 text-indigo-600',
        purple: 'bg-purple-100 text-purple-600',
        blue: 'bg-blue-100 text-blue-600',
        green: 'bg-green-100 text-green-600',
        red: 'bg-red-100 text-red-600',
        yellow: 'bg-yellow-100 text-yellow-600',
        pink: 'bg-pink-100 text-pink-600',
        gray: 'bg-gray-100 text-gray-600'
    }
    return colors[props.color]
})
</script>
