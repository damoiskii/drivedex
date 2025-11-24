<template>
    <Transition name="modal">
        <div v-if="show" class="fixed inset-0 z-50 overflow-y-auto" aria-labelledby="modal-title" role="dialog"
            aria-modal="true">
            <!-- Backdrop -->
            <div class="flex items-center justify-center min-h-screen px-4 pt-4 pb-20 text-center sm:block sm:p-0">
                <Transition name="backdrop">
                    <div v-if="show" class="fixed inset-0 bg-gray-900 bg-opacity-75 transition-opacity"
                        aria-hidden="true" @click="handleCancel"></div>
                </Transition>

                <!-- Center modal -->
                <span class="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">&#8203;</span>

                <Transition name="modal-content">
                    <div v-if="show"
                        class="inline-block align-bottom bg-white rounded-2xl text-left overflow-hidden shadow-2xl transform transition-all sm:my-8 sm:align-middle sm:max-w-2xl sm:w-full">
                        <form @submit.prevent="handleSubmit">
                            <div class="bg-white px-6 pt-6 pb-4">
                                <!-- Header -->
                                <div class="flex items-center justify-between mb-6">
                                    <div class="flex items-center">
                                        <div
                                            class="flex-shrink-0 flex items-center justify-center h-12 w-12 rounded-full bg-indigo-100">
                                            <svg class="h-6 w-6 text-indigo-600" fill="none" viewBox="0 0 24 24"
                                                stroke="currentColor">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                    d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                                            </svg>
                                        </div>
                                        <h3 class="ml-4 text-xl font-bold leading-6 text-gray-900" id="modal-title">
                                            {{ isEditMode ? 'Edit Employee' : 'Add New Employee' }}
                                        </h3>
                                    </div>
                                    <button type="button" @click="handleCancel"
                                        class="text-gray-400 hover:text-gray-500 transition-colors">
                                        <svg class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                d="M6 18L18 6M6 6l12 12" />
                                        </svg>
                                    </button>
                                </div>

                                <!-- Form Fields -->
                                <div class="space-y-4">
                                    <!-- Name -->
                                    <div>
                                        <label for="name" class="block text-sm font-medium text-gray-700 mb-1">
                                            Full Name <span class="text-red-500">*</span>
                                        </label>
                                        <input v-model="formData.name" type="text" id="name" required
                                            placeholder="e.g., John Doe"
                                            class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent transition-all" />
                                    </div>

                                    <!-- Email -->
                                    <div>
                                        <label for="email" class="block text-sm font-medium text-gray-700 mb-1">
                                            Email Address <span class="text-red-500">*</span>
                                        </label>
                                        <input v-model="formData.email" type="email" id="email" required
                                            placeholder="e.g., john.doe@drivedex.com"
                                            class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent transition-all" />
                                    </div>

                                    <!-- Department and Position Row -->
                                    <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                                        <!-- Department -->
                                        <div>
                                            <label for="department"
                                                class="block text-sm font-medium text-gray-700 mb-1">
                                                Department <span class="text-red-500">*</span>
                                            </label>
                                            <select v-model="formData.department" id="department" required
                                                class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent transition-all">
                                                <option value="" disabled>Select department</option>
                                                <option v-for="dept in departments" :key="dept" :value="dept">
                                                    {{ dept }}
                                                </option>
                                            </select>
                                        </div>

                                        <!-- Position -->
                                        <div>
                                            <label for="position" class="block text-sm font-medium text-gray-700 mb-1">
                                                Position <span class="text-red-500">*</span>
                                            </label>
                                            <input v-model="formData.position" type="text" id="position" required
                                                placeholder="e.g., Fleet Manager"
                                                class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent transition-all" />
                                        </div>
                                    </div>

                                    <!-- Phone and Status Row -->
                                    <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                                        <!-- Phone -->
                                        <div>
                                            <label for="phone" class="block text-sm font-medium text-gray-700 mb-1">
                                                Phone Number
                                            </label>
                                            <input v-model="formData.phone" type="tel" id="phone"
                                                placeholder="e.g., +1 (555) 123-4567"
                                                class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent transition-all" />
                                        </div>

                                        <!-- Status -->
                                        <div>
                                            <label for="status" class="block text-sm font-medium text-gray-700 mb-1">
                                                Status <span class="text-red-500">*</span>
                                            </label>
                                            <select v-model="formData.status" id="status" required
                                                class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent transition-all">
                                                <option value="active">Active</option>
                                                <option value="leave">On Leave</option>
                                                <option value="inactive">Inactive</option>
                                            </select>
                                        </div>
                                    </div>

                                    <!-- Generated Password (Only for new employees) -->
                                    <div v-if="!isEditMode">
                                        <label for="password" class="block text-sm font-medium text-gray-700 mb-1">
                                            Generated Password
                                        </label>
                                        <div class="relative">
                                            <input :value="generatedPassword" type="text" id="password" readonly
                                                class="w-full px-4 py-2.5 pr-24 border border-gray-300 rounded-lg bg-gray-50 text-gray-700 cursor-not-allowed" />
                                            <div class="absolute inset-y-0 right-0 flex items-center gap-2 pr-3">
                                                <button type="button" @click="copyPassword"
                                                    class="text-indigo-600 hover:text-indigo-800 transition-colors"
                                                    title="Copy password">
                                                    <svg v-if="!passwordCopied" class="w-5 h-5" fill="none"
                                                        stroke="currentColor" viewBox="0 0 24 24">
                                                        <path stroke-linecap="round" stroke-linejoin="round"
                                                            stroke-width="2"
                                                            d="M8 16H6a2 2 0 01-2-2V6a2 2 0 012-2h8a2 2 0 012 2v2m-6 12h8a2 2 0 002-2v-8a2 2 0 00-2-2h-8a2 2 0 00-2 2v8a2 2 0 002 2z" />
                                                    </svg>
                                                    <svg v-else class="w-5 h-5 text-green-600" fill="none"
                                                        stroke="currentColor" viewBox="0 0 24 24">
                                                        <path stroke-linecap="round" stroke-linejoin="round"
                                                            stroke-width="2" d="M5 13l4 4L19 7" />
                                                    </svg>
                                                </button>
                                                <button type="button" @click="regeneratePassword"
                                                    class="text-gray-600 hover:text-gray-800 transition-colors"
                                                    title="Regenerate password">
                                                    <svg class="w-5 h-5" fill="none" stroke="currentColor"
                                                        viewBox="0 0 24 24">
                                                        <path stroke-linecap="round" stroke-linejoin="round"
                                                            stroke-width="2"
                                                            d="M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15" />
                                                    </svg>
                                                </button>
                                            </div>
                                        </div>
                                        <p class="mt-1 text-xs text-gray-500">
                                            This password will be sent to the employee's email. They can change it after
                                            first login.
                                        </p>
                                    </div>

                                    <!-- Additional Notes -->
                                    <div>
                                        <label for="notes" class="block text-sm font-medium text-gray-700 mb-1">
                                            Additional Notes
                                        </label>
                                        <textarea v-model="formData.notes" id="notes" rows="3"
                                            placeholder="Any additional information about the employee..."
                                            class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent transition-all resize-none"></textarea>
                                    </div>
                                </div>
                            </div>

                            <!-- Action Buttons -->
                            <div class="bg-gray-50 px-6 py-4 flex flex-col-reverse sm:flex-row sm:justify-end gap-3">
                                <button type="button" @click="handleCancel"
                                    class="w-full sm:w-auto inline-flex justify-center items-center rounded-lg border border-gray-300 shadow-sm px-5 py-2.5 bg-white text-base font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 transition-all">
                                    Cancel
                                </button>
                                <button type="submit"
                                    class="w-full sm:w-auto inline-flex justify-center items-center rounded-lg border border-transparent shadow-sm px-5 py-2.5 bg-indigo-600 text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 transition-all">
                                    <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                            d="M5 13l4 4L19 7" />
                                    </svg>
                                    {{ isEditMode ? 'Update Employee' : 'Add Employee' }}
                                </button>
                            </div>
                        </form>
                    </div>
                </Transition>
            </div>
        </div>
    </Transition>
</template>

<script setup>
import { ref, computed, watch } from 'vue'

const props = defineProps({
    show: {
        type: Boolean,
        default: false
    },
    employee: {
        type: Object,
        default: null
    },
    departments: {
        type: Array,
        default: () => ['Operations', 'Maintenance', 'HR', 'Finance', 'IT', 'Customer Service']
    }
})

const emit = defineEmits(['submit', 'cancel'])

const formData = ref({
    name: '',
    email: '',
    department: '',
    position: '',
    phone: '',
    status: 'active',
    notes: ''
})

const generatedPassword = ref('')
const passwordCopied = ref(false)

const isEditMode = computed(() => props.employee !== null)

// Generate a random password
const generatePassword = () => {
    const length = 12
    const charset = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*'
    let password = ''
    
    // Ensure at least one of each type
    password += 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'[Math.floor(Math.random() * 26)]
    password += 'abcdefghijklmnopqrstuvwxyz'[Math.floor(Math.random() * 26)]
    password += '0123456789'[Math.floor(Math.random() * 10)]
    password += '!@#$%^&*'[Math.floor(Math.random() * 8)]
    
    // Fill the rest randomly
    for (let i = password.length; i < length; i++) {
        password += charset[Math.floor(Math.random() * charset.length)]
    }
    
    // Shuffle the password
    return password.split('').sort(() => Math.random() - 0.5).join('')
}

const regeneratePassword = () => {
    generatedPassword.value = generatePassword()
    passwordCopied.value = false
}

const copyPassword = async () => {
    try {
        await navigator.clipboard.writeText(generatedPassword.value)
        passwordCopied.value = true
        setTimeout(() => {
            passwordCopied.value = false
        }, 2000)
    } catch (err) {
        console.error('Failed to copy password:', err)
    }
}

const resetForm = () => {
    formData.value = {
        name: '',
        email: '',
        department: '',
        position: '',
        phone: '',
        status: 'active',
        notes: ''
    }
    generatedPassword.value = generatePassword()
    passwordCopied.value = false
}

const handleSubmit = () => {
    const dataToSubmit = { ...formData.value }
    
    // Include password only for new employees
    if (!isEditMode.value) {
        dataToSubmit.password = generatedPassword.value
    }
    
    emit('submit', dataToSubmit)
    resetForm()
}

const handleCancel = () => {
    emit('cancel')
    resetForm()
}

// Watch for modal show/hide to initialize form
watch(() => props.show, (newVal) => {
    if (newVal) {
        if (props.employee) {
            // Edit mode - populate form with employee data
            formData.value = { ...props.employee }
        } else {
            // Add mode - reset form and generate password
            resetForm()
        }
    }
}, { immediate: true })

// Initialize password on component mount
generatedPassword.value = generatePassword()
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
