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
                    <div v-if="show && bus"
                        class="inline-block align-bottom bg-white rounded-2xl text-left overflow-hidden shadow-2xl transform transition-all sm:my-8 sm:align-middle sm:max-w-2xl sm:w-full">
                        <!-- Header -->
                        <div class="bg-gradient-to-r from-indigo-500 to-indigo-600 px-6 py-6">
                            <div class="flex items-center justify-between">
                                <div class="flex items-center gap-4">
                                    <div class="w-16 h-16 rounded-full bg-white flex items-center justify-center">
                                        <svg class="w-10 h-10 text-indigo-600" fill="currentColor" viewBox="0 0 24 24">
                                            <path d="M18.92 6.01C18.72 5.42 18.16 5 17.5 5h-11c-.66 0-1.21.42-1.42 1.01L3 12v8c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-1h12v1c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-8l-2.08-5.99zM6.5 16c-.83 0-1.5-.67-1.5-1.5S5.67 13 6.5 13s1.5.67 1.5 1.5S7.33 16 6.5 16zm11 0c-.83 0-1.5-.67-1.5-1.5s.67-1.5 1.5-1.5 1.5.67 1.5 1.5-.67 1.5-1.5 1.5zM5 11l1.5-4.5h11L19 11H5z"/>
                                        </svg>
                                    </div>
                                    <div>
                                        <h3 class="text-2xl font-bold text-white" id="modal-title">
                                            {{ bus.name }}
                                        </h3>
                                        <p class="text-indigo-100 text-sm mt-1">{{ bus.id }}</p>
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
                                    bus.status === 'active' ? 'bg-green-100 text-green-800' :
                                    bus.status === 'maintenance' ? 'bg-red-100 text-red-800' :
                                        'bg-gray-100 text-gray-800'
                                ]">
                                    {{ bus.status === 'active' ? 'Active' : bus.status === 'maintenance' ? 'Maintenance' : 'Idle' }}
                                </span>
                            </div>

                            <!-- Information Grid -->
                            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                                <!-- Vehicle Information -->
                                <div>
                                    <h4 class="text-sm font-semibold text-gray-500 uppercase tracking-wider mb-3">
                                        Vehicle Information
                                    </h4>
                                    <div class="space-y-3">
                                        <div class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-gray-400 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Model</p>
                                                <p class="text-sm font-medium text-gray-900">{{ bus.model }}</p>
                                            </div>
                                        </div>
                                        <div class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-gray-400 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M7 7h.01M7 3h5c.512 0 1.024.195 1.414.586l7 7a2 2 0 010 2.828l-7 7a2 2 0 01-2.828 0l-7-7A1.994 1.994 0 013 12V7a4 4 0 014-4z" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Plate Number</p>
                                                <p class="text-sm font-medium text-gray-900">{{ bus.plateNumber }}</p>
                                            </div>
                                        </div>
                                        <div class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-gray-400 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Capacity</p>
                                                <p class="text-sm font-medium text-gray-900">{{ bus.capacity }} Passengers</p>
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
                                                <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/>
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Driver</p>
                                                <p class="text-sm font-medium text-gray-900">{{ bus.driver || 'Not Assigned' }}</p>
                                            </div>
                                        </div>
                                        <div class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-gray-400 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M9 20l-5.447-2.724A1 1 0 013 16.382V5.618a1 1 0 011.447-.894L9 7m0 13l6-3m-6 3V7m6 10l4.553 2.276A1 1 0 0021 18.382V7.618a1 1 0 00-.553-.894L15 4m0 13V4m0 0L9 7" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Route</p>
                                                <p class="text-sm font-medium text-gray-900">{{ bus.route || 'Not Assigned' }}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Maintenance Information -->
                                <div v-if="bus.lastMaintenance || bus.nextMaintenance">
                                    <h4 class="text-sm font-semibold text-gray-500 uppercase tracking-wider mb-3">
                                        Maintenance
                                    </h4>
                                    <div class="space-y-3">
                                        <div v-if="bus.lastMaintenance" class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-gray-400 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Last Maintenance</p>
                                                <p class="text-sm font-medium text-gray-900">{{ bus.lastMaintenance }}</p>
                                            </div>
                                        </div>
                                        <div v-if="bus.nextMaintenance" class="flex items-start gap-3">
                                            <svg class="w-5 h-5 text-gray-400 mt-0.5" fill="none" stroke="currentColor"
                                                viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                                            </svg>
                                            <div>
                                                <p class="text-xs text-gray-500">Next Maintenance</p>
                                                <p class="text-sm font-medium text-gray-900">{{ bus.nextMaintenance }}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Additional Notes -->
                                <div v-if="bus.notes">
                                    <h4 class="text-sm font-semibold text-gray-500 uppercase tracking-wider mb-3">
                                        Additional Notes
                                    </h4>
                                    <p class="text-sm text-gray-700 bg-gray-50 p-3 rounded-lg">
                                        {{ bus.notes }}
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
                                Edit Bus
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
    bus: {
        type: Object,
        default: null
    }
})

const emit = defineEmits(['close', 'edit'])

const handleClose = () => {
    emit('close')
}

const handleEdit = () => {
    emit('edit', props.bus)
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
