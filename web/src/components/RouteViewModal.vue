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
                    <div v-if="show && route"
                        class="inline-block align-bottom bg-white rounded-2xl text-left overflow-hidden shadow-2xl transform transition-all sm:my-8 sm:align-middle sm:max-w-3xl sm:w-full">
                        <!-- Header -->
                        <div class="bg-gradient-to-r from-indigo-500 to-indigo-600 px-6 py-6">
                            <div class="flex items-center justify-between">
                                <div class="flex items-center gap-4">
                                    <div class="w-16 h-16 rounded-full bg-white flex items-center justify-center">
                                        <svg class="w-10 h-10 text-indigo-600" fill="none" stroke="currentColor"
                                            viewBox="0 0 24 24">
                                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                d="M9 20l-5.447-2.724A1 1 0 013 16.382V5.618a1 1 0 011.447-.894L9 7m0 13l6-3m-6 3V7m6 10l4.553 2.276A1 1 0 0021 18.382V7.618a1 1 0 00-.553-.894L15 4m0 13V4m0 0L9 7" />
                                        </svg>
                                    </div>
                                    <div>
                                        <h3 class="text-2xl font-bold text-white" id="modal-title">
                                            {{ route.name }}
                                        </h3>
                                        <p class="text-indigo-100 text-sm mt-1">{{ route.id }}</p>
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
                                    route.status === 'active' ? 'bg-green-100 text-green-800' :
                                        'bg-gray-100 text-gray-800'
                                ]">
                                    {{ route.status === 'active' ? 'Active' : 'Inactive' }}
                                </span>
                            </div>

                            <!-- Description -->
                            <div v-if="route.description" class="mb-6">
                                <p class="text-gray-700">{{ route.description }}</p>
                            </div>

                            <!-- Information Grid -->
                            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                                <!-- Route Information -->
                                <div>
                                    <h4 class="text-sm font-semibold text-gray-500 uppercase tracking-wider mb-3">
                                        Route Information
                                    </h4>
                                    <div class="space-y-3">
                                        <div class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-green-500 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M9 11l3-3m0 0l3 3m-3-3v8m0-13a9 9 0 110 18 9 9 0 010-18z" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Start Point</p>
                                                <p class="text-sm font-medium text-gray-900">{{ route.start }}</p>
                                            </div>
                                        </div>
                                        <div class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-red-500 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M15 13l-3 3m0 0l-3-3m3 3V8m0 13a9 9 0 110-18 9 9 0 010 18z" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">End Point</p>
                                                <p class="text-sm font-medium text-gray-900">{{ route.end }}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Statistics -->
                                <div>
                                    <h4 class="text-sm font-semibold text-gray-500 uppercase tracking-wider mb-3">
                                        Statistics
                                    </h4>
                                    <div class="space-y-3">
                                        <div class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-gray-400 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Distance</p>
                                                <p class="text-sm font-medium text-gray-900">{{ route.distance }} km</p>
                                            </div>
                                        </div>
                                        <div class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-gray-400 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Duration</p>
                                                <p class="text-sm font-medium text-gray-900">{{ route.duration }}</p>
                                            </div>
                                        </div>
                                        <div class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-gray-400 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Number of Stops</p>
                                                <p class="text-sm font-medium text-gray-900">{{ route.stops }}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Schedule Information -->
                                <div>
                                    <h4 class="text-sm font-semibold text-gray-500 uppercase tracking-wider mb-3">
                                        Schedule
                                    </h4>
                                    <div class="space-y-3">
                                        <div class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-gray-400 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Frequency</p>
                                                <p class="text-sm font-medium text-gray-900">{{ route.frequency }}</p>
                                            </div>
                                        </div>
                                        <div v-if="route.operatingHours" class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-gray-400 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Operating Hours</p>
                                                <p class="text-sm font-medium text-gray-900">{{ route.operatingHours }}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Additional Notes -->
                                <div v-if="route.notes">
                                    <h4 class="text-sm font-semibold text-gray-500 uppercase tracking-wider mb-3">
                                        Additional Notes
                                    </h4>
                                    <p class="text-sm text-gray-700 bg-gray-50 p-3 rounded-lg">
                                        {{ route.notes }}
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
                                Edit Route
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
    route: {
        type: Object,
        default: null
    }
})

const emit = defineEmits(['close', 'edit'])

const handleClose = () => {
    emit('close')
}

const handleEdit = () => {
    emit('edit', props.route)
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
