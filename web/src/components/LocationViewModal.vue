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
                    <div v-if="show && location"
                        class="inline-block align-bottom bg-white rounded-2xl text-left overflow-hidden shadow-2xl transform transition-all sm:my-8 sm:align-middle sm:max-w-2xl sm:w-full">
                        <!-- Header -->
                        <div class="bg-gradient-to-r from-indigo-500 to-indigo-600 px-6 py-6">
                            <div class="flex items-center justify-between">
                                <div class="flex items-center gap-4">
                                    <div class="w-16 h-16 rounded-full bg-white flex items-center justify-center">
                                        <svg class="w-10 h-10 text-indigo-600" fill="none" stroke="currentColor"
                                            viewBox="0 0 24 24">
                                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                                        </svg>
                                    </div>
                                    <div>
                                        <h3 class="text-2xl font-bold text-white" id="modal-title">
                                            {{ location.name }}
                                        </h3>
                                        <p class="text-indigo-100 text-sm mt-1">{{ location.id }}</p>
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
                                    location.status === 'active' ? 'bg-green-100 text-green-800' :
                                        'bg-gray-100 text-gray-800'
                                ]">
                                    {{ location.status === 'active' ? 'Active' : 'Inactive' }}
                                </span>
                                <span :class="[
                                    'px-3 py-1 inline-flex text-xs leading-5 font-semibold rounded-full',
                                    location.type === 'terminal' ? 'bg-blue-100 text-blue-800' :
                                    location.type === 'station' ? 'bg-purple-100 text-purple-800' :
                                        'bg-gray-100 text-gray-800'
                                ]">
                                    {{ location.type === 'terminal' ? 'Terminal' : location.type === 'station' ? 'Station' : 'Stop' }}
                                </span>
                            </div>

                            <!-- Information Grid -->
                            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                                <!-- Location Information -->
                                <div>
                                    <h4 class="text-sm font-semibold text-gray-500 uppercase tracking-wider mb-3">
                                        Location Information
                                    </h4>
                                    <div class="space-y-3">
                                        <div class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-gray-400 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Address</p>
                                                <p class="text-sm font-medium text-gray-900">{{ location.address }}</p>
                                            </div>
                                        </div>
                                        <div class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-gray-400 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M9 20l-5.447-2.724A1 1 0 013 16.382V5.618a1 1 0 011.447-.894L9 7m0 13l6-3m-6 3V7m6 10l4.553 2.276A1 1 0 0021 18.382V7.618a1 1 0 00-.553-.894L15 4m0 13V4m0 0L9 7" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Coordinates</p>
                                                <p class="text-sm font-medium text-gray-900 font-mono">{{ location.coordinates }}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Statistics -->
                                <div>
                                    <h4 class="text-sm font-semibold text-gray-500 uppercase tracking-wider mb-3">
                                        Usage Statistics
                                    </h4>
                                    <div class="space-y-3">
                                        <div class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-gray-400 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Routes Using This Location</p>
                                                <p class="text-sm font-medium text-gray-900">{{ location.routeCount }}</p>
                                            </div>
                                        </div>
                                        <div v-if="location.avgDailyPassengers" class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-gray-400 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Avg. Daily Passengers</p>
                                                <p class="text-sm font-medium text-gray-900">{{ location.avgDailyPassengers }}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Facilities -->
                                <div v-if="location.facilities && location.facilities.length > 0">
                                    <h4 class="text-sm font-semibold text-gray-500 uppercase tracking-wider mb-3">
                                        Facilities
                                    </h4>
                                    <div class="flex flex-wrap gap-2">
                                        <span v-for="facility in location.facilities" :key="facility"
                                            class="px-3 py-1 bg-indigo-50 text-indigo-700 text-xs font-medium rounded-full">
                                            {{ facility }}
                                        </span>
                                    </div>
                                </div>

                                <!-- Additional Notes -->
                                <div v-if="location.notes">
                                    <h4 class="text-sm font-semibold text-gray-500 uppercase tracking-wider mb-3">
                                        Additional Notes
                                    </h4>
                                    <p class="text-sm text-gray-700 bg-gray-50 p-3 rounded-lg">
                                        {{ location.notes }}
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
                                Edit Location
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
    location: {
        type: Object,
        default: null
    }
})

const emit = defineEmits(['close', 'edit'])

const handleClose = () => {
    emit('close')
}

const handleEdit = () => {
    emit('edit', props.location)
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
