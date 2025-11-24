<template>
    <Transition name="modal">
        <div v-if="show" class="fixed inset-0 z-50 overflow-y-auto" aria-labelledby="modal-title" role="dialog"
            aria-modal="true">
            <!-- Backdrop -->
            <div class="flex items-center justify-center min-h-screen px-4 pt-4 pb-20 text-center sm:block sm:p-0">
                <Transition name="backdrop">
                    <div v-if="show" class="fixed inset-0 bg-gray-900 bg-opacity-75 transition-opacity"
                        aria-hidden="true" @click="handleClose"></div>
                </Transition>

                <!-- Center modal -->
                <span class="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">&#8203;</span>

                <Transition name="modal-content">
                    <div v-if="show && driver"
                        class="inline-block align-bottom bg-white rounded-2xl text-left overflow-hidden shadow-2xl transform transition-all sm:my-8 sm:align-middle sm:max-w-2xl sm:w-full">
                        <!-- Header -->
                        <div class="bg-gradient-to-r from-indigo-500 to-indigo-600 px-6 py-6">
                            <div class="flex items-center justify-between">
                                <div class="flex items-center gap-4">
                                    <div class="w-16 h-16 rounded-full bg-white flex items-center justify-center">
                                        <span class="text-2xl font-bold text-indigo-600">
                                            {{ getInitials(driver.name) }}
                                        </span>
                                    </div>
                                    <div>
                                        <h3 class="text-2xl font-bold text-white" id="modal-title">
                                            {{ driver.name }}
                                        </h3>
                                        <p class="text-indigo-100 text-sm mt-1">{{ driver.id }}</p>
                                    </div>
                                </div>
                                <button type="button" @click="handleClose"
                                    class="text-white hover:text-indigo-100 transition-colors">
                                    <svg class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                            d="M6 18L18 6M6 6l12 12" />
                                    </svg>
                                </button>
                            </div>
                        </div>

                        <!-- Content -->
                        <div class="bg-white px-6 py-6">
                            <!-- Status Badge -->
                            <div class="flex gap-2 mb-6">
                                <span :class="[
                                    'px-3 py-1 inline-flex text-xs leading-5 font-semibold rounded-full',
                                    driver.status === 'on-duty' ? 'bg-green-100 text-green-800' :
                                        'bg-gray-100 text-gray-800'
                                ]">
                                    {{ driver.status === 'on-duty' ? 'On Duty' : 'Off Duty' }}
                                </span>
                                <span v-if="driver.assignedBus" class="px-3 py-1 inline-flex text-xs leading-5 font-semibold rounded-full bg-blue-100 text-blue-800">
                                    Assigned to {{ driver.assignedBus }}
                                </span>
                            </div>

                            <!-- Information Grid -->
                            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                                <!-- Contact Information -->
                                <div>
                                    <h4 class="text-sm font-semibold text-gray-500 uppercase tracking-wider mb-3">
                                        Contact Information
                                    </h4>
                                    <div class="space-y-3">
                                        <div class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-gray-400 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Phone</p>
                                                <p class="text-sm font-medium text-gray-900">{{ driver.phone }}</p>
                                            </div>
                                        </div>
                                        <div v-if="driver.email" class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-gray-400 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Email</p>
                                                <p class="text-sm font-medium text-gray-900">{{ driver.email }}</p>
                                            </div>
                                        </div>
                                        <div v-if="driver.address" class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-gray-400 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Address</p>
                                                <p class="text-sm font-medium text-gray-900">{{ driver.address }}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- License Information -->
                                <div>
                                    <h4 class="text-sm font-semibold text-gray-500 uppercase tracking-wider mb-3">
                                        License Information
                                    </h4>
                                    <div class="space-y-3">
                                        <div class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-gray-400 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M10 6H5a2 2 0 00-2 2v9a2 2 0 002 2h14a2 2 0 002-2V8a2 2 0 00-2-2h-5m-4 0V5a2 2 0 114 0v1m-4 0a2 2 0 104 0m-5 8a2 2 0 100-4 2 2 0 000 4zm0 0c1.306 0 2.417.835 2.83 2M9 14a3.001 3.001 0 00-2.83 2M15 11h3m-3 4h2" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">License Number</p>
                                                <p class="text-sm font-medium text-gray-900">{{ driver.licenseNo }}</p>
                                            </div>
                                        </div>
                                        <div class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-gray-400 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Expiry Date</p>
                                                <p class="text-sm font-medium text-gray-900">{{ driver.licenseExpiry }}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Assignment Information -->
                                <div>
                                    <h4 class="text-sm font-semibold text-gray-500 uppercase tracking-wider mb-3">
                                        Assignment
                                    </h4>
                                    <div class="space-y-3">
                                        <div class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-gray-400 mt-0.5" fill="currentColor" viewBox="0 0 24 24">
                                                <path d="M18.92 6.01C18.72 5.42 18.16 5 17.5 5h-11c-.66 0-1.21.42-1.42 1.01L3 12v8c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-1h12v1c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-8l-2.08-5.99zM6.5 16c-.83 0-1.5-.67-1.5-1.5S5.67 13 6.5 13s1.5.67 1.5 1.5S7.33 16 6.5 16zm11 0c-.83 0-1.5-.67-1.5-1.5s.67-1.5 1.5-1.5 1.5.67 1.5 1.5-.67 1.5-1.5 1.5zM5 11l1.5-4.5h11L19 11H5z"/>
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Assigned Bus</p>
                                                <p class="text-sm font-medium text-gray-900">
                                                    {{ driver.assignedBus || 'Not Assigned' }}
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Additional Notes -->
                                <div v-if="driver.notes">
                                    <h4 class="text-sm font-semibold text-gray-500 uppercase tracking-wider mb-3">
                                        Additional Notes
                                    </h4>
                                    <p class="text-sm text-gray-700 bg-gray-50 p-3 rounded-lg">
                                        {{ driver.notes }}
                                    </p>
                                </div>
                            </div>
                        </div>

                        <!-- Footer Actions -->
                        <div class="bg-gray-50 px-6 py-4 flex justify-end gap-3">
                            <button type="button" @click="handleClose"
                                class="px-5 py-2.5 text-gray-700 bg-white border border-gray-300 rounded-lg hover:bg-gray-50 transition-colors">
                                Close
                            </button>
                            <button type="button" @click="handleEdit"
                                class="px-5 py-2.5 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700 transition-colors flex items-center gap-2">
                                <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                        d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z" />
                                </svg>
                                Edit Driver
                            </button>
                        </div>
                    </div>
                </Transition>
            </div>
        </div>
    </Transition>
</template>

<script setup>
const props = defineProps({
    show: {
        type: Boolean,
        default: false
    },
    driver: {
        type: Object,
        default: null
    }
})

const emit = defineEmits(['close', 'edit'])

const getInitials = (name) => {
    if (!name) return ''
    const parts = name.split(' ')
    if (parts.length >= 2) {
        return `${parts[0][0]}${parts[1][0]}`.toUpperCase()
    }
    return name.substring(0, 2).toUpperCase()
}

const handleClose = () => {
    emit('close')
}

const handleEdit = () => {
    emit('edit', props.driver)
}
</script>

<style scoped>
/* Modal fade transition */
.modal-enter-active,
.modal-leave-active {
    transition: opacity 0.2s ease;
}

.modal-enter-from,
.modal-leave-to {
    opacity: 0;
}

/* Backdrop transition */
.backdrop-enter-active,
.backdrop-leave-active {
    transition: opacity 0.3s ease;
}

.backdrop-enter-from,
.backdrop-leave-to {
    opacity: 0;
}

/* Modal content transition */
.modal-content-enter-active {
    transition: all 0.3s ease;
}

.modal-content-leave-active {
    transition: all 0.2s ease;
}

.modal-content-enter-from {
    opacity: 0;
    transform: scale(0.95) translateY(-20px);
}

.modal-content-leave-to {
    opacity: 0;
    transform: scale(0.95) translateY(20px);
}
</style>
