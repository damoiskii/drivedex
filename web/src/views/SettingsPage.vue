<template>
    <div class="p-8">
        <!-- Toast Component -->
        <Toast :show="toast.show" :type="toast.type" :message="toast.message" :description="toast.description"
            @close="toast.show = false" />

        <!-- Delete Confirmation Modal -->
        <DeleteModal :show="deleteModal.show" :title="deleteModal.title" :message="deleteModal.message"
            :itemName="deleteModal.itemName" @confirm="confirmReset" @cancel="cancelReset" />

        <!-- Header -->
        <div class="mb-6">
            <h1 class="text-3xl font-bold text-gray-800">Settings</h1>
            <p class="text-gray-600 mt-2">Manage system configuration and preferences</p>
        </div>

        <!-- Settings Tabs -->
        <div class="bg-white rounded-lg shadow-sm mb-6">
            <div class="border-b border-gray-200">
                <nav class="flex -mb-px">
                    <button @click="activeTab = 'general'" :class="[
                        'px-6 py-3 border-b-2 font-medium text-sm transition-colors',
                        activeTab === 'general' ? 'border-indigo-600 text-indigo-600' : 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300'
                    ]">
                        General
                    </button>
                    <button @click="activeTab = 'notifications'" :class="[
                        'px-6 py-3 border-b-2 font-medium text-sm transition-colors',
                        activeTab === 'notifications' ? 'border-indigo-600 text-indigo-600' : 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300'
                    ]">
                        Notifications
                    </button>
                    <button @click="activeTab = 'security'" :class="[
                        'px-6 py-3 border-b-2 font-medium text-sm transition-colors',
                        activeTab === 'security' ? 'border-indigo-600 text-indigo-600' : 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300'
                    ]">
                        Security
                    </button>
                    <button @click="activeTab = 'appearance'" :class="[
                        'px-6 py-3 border-b-2 font-medium text-sm transition-colors',
                        activeTab === 'appearance' ? 'border-indigo-600 text-indigo-600' : 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300'
                    ]">
                        Appearance
                    </button>
                </nav>
            </div>
        </div>

        <!-- General Settings -->
        <div v-if="activeTab === 'general'" class="bg-white rounded-lg shadow-sm p-6">
            <h2 class="text-xl font-semibold text-gray-800 mb-6">General Settings</h2>
            
            <div class="space-y-6">
                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">System Name</label>
                    <input v-model="settings.general.systemName" type="text"
                        class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent" />
                </div>

                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">Contact Email</label>
                    <input v-model="settings.general.contactEmail" type="email"
                        class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent" />
                </div>

                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">Time Zone</label>
                    <select v-model="settings.general.timezone"
                        class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent">
                        <option value="UTC">UTC (GMT +0:00)</option>
                        <option value="EST">EST (GMT -5:00)</option>
                        <option value="PST">PST (GMT -8:00)</option>
                        <option value="CST">CST (GMT -6:00)</option>
                    </select>
                </div>

                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">Language</label>
                    <select v-model="settings.general.language"
                        class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent">
                        <option value="en">English</option>
                        <option value="es">Spanish</option>
                        <option value="fr">French</option>
                    </select>
                </div>

                <div class="flex items-center">
                    <input v-model="settings.general.maintenanceMode" type="checkbox"
                        class="w-4 h-4 text-indigo-600 border-gray-300 rounded focus:ring-indigo-500" />
                    <label class="ml-2 text-sm text-gray-700">Enable Maintenance Mode</label>
                </div>
            </div>

            <div class="flex gap-3 pt-6">
                <button @click="saveSettings"
                    class="px-6 py-2 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700 transition-colors">
                    Save Changes
                </button>
                <button @click="resetSettings"
                    class="px-6 py-2 bg-white border border-gray-300 text-gray-700 rounded-lg hover:bg-gray-50 transition-colors">
                    Reset
                </button>
            </div>
        </div>

        <!-- Notification Settings -->
        <div v-else-if="activeTab === 'notifications'" class="bg-white rounded-lg shadow-sm p-6">
            <h2 class="text-xl font-semibold text-gray-800 mb-6">Notification Preferences</h2>
            
            <div class="space-y-4">
                <div class="flex items-center justify-between p-4 bg-gray-50 rounded-lg">
                    <div>
                        <p class="font-medium text-gray-900">Email Notifications</p>
                        <p class="text-sm text-gray-500">Receive email updates</p>
                    </div>
                    <input v-model="settings.notifications.email" type="checkbox"
                        class="w-4 h-4 text-indigo-600 border-gray-300 rounded focus:ring-indigo-500" />
                </div>

                <div class="flex items-center justify-between p-4 bg-gray-50 rounded-lg">
                    <div>
                        <p class="font-medium text-gray-900">SMS Notifications</p>
                        <p class="text-sm text-gray-500">Receive text messages</p>
                    </div>
                    <input v-model="settings.notifications.sms" type="checkbox"
                        class="w-4 h-4 text-indigo-600 border-gray-300 rounded focus:ring-indigo-500" />
                </div>

                <div class="flex items-center justify-between p-4 bg-gray-50 rounded-lg">
                    <div>
                        <p class="font-medium text-gray-900">Push Notifications</p>
                        <p class="text-sm text-gray-500">Receive browser notifications</p>
                    </div>
                    <input v-model="settings.notifications.push" type="checkbox"
                        class="w-4 h-4 text-indigo-600 border-gray-300 rounded focus:ring-indigo-500" />
                </div>

                <div class="flex items-center justify-between p-4 bg-gray-50 rounded-lg">
                    <div>
                        <p class="font-medium text-gray-900">Trip Updates</p>
                        <p class="text-sm text-gray-500">Notify about trip status changes</p>
                    </div>
                    <input v-model="settings.notifications.tripUpdates" type="checkbox"
                        class="w-4 h-4 text-indigo-600 border-gray-300 rounded focus:ring-indigo-500" />
                </div>

                <div class="flex items-center justify-between p-4 bg-gray-50 rounded-lg">
                    <div>
                        <p class="font-medium text-gray-900">Maintenance Alerts</p>
                        <p class="text-sm text-gray-500">Notify about vehicle maintenance</p>
                    </div>
                    <input v-model="settings.notifications.maintenance" type="checkbox"
                        class="w-4 h-4 text-indigo-600 border-gray-300 rounded focus:ring-indigo-500" />
                </div>
            </div>

            <div class="flex gap-3 pt-6">
                <button @click="saveSettings"
                    class="px-6 py-2 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700 transition-colors">
                    Save Changes
                </button>
            </div>
        </div>

        <!-- Security Settings -->
        <div v-else-if="activeTab === 'security'" class="bg-white rounded-lg shadow-sm p-6">
            <h2 class="text-xl font-semibold text-gray-800 mb-6">Security Settings</h2>
            
            <div class="space-y-6">
                <div>
                    <h3 class="text-lg font-medium text-gray-900 mb-4">Change Password</h3>
                    <div class="space-y-4">
                        <input type="password" placeholder="Current Password"
                            class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent" />
                        <input type="password" placeholder="New Password"
                            class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent" />
                        <input type="password" placeholder="Confirm New Password"
                            class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent" />
                    </div>
                </div>

                <div class="flex items-center justify-between p-4 bg-gray-50 rounded-lg">
                    <div>
                        <p class="font-medium text-gray-900">Two-Factor Authentication</p>
                        <p class="text-sm text-gray-500">Add an extra layer of security</p>
                    </div>
                    <input v-model="settings.security.twoFactor" type="checkbox"
                        class="w-4 h-4 text-indigo-600 border-gray-300 rounded focus:ring-indigo-500" />
                </div>

                <div class="flex items-center justify-between p-4 bg-gray-50 rounded-lg">
                    <div>
                        <p class="font-medium text-gray-900">Login Alerts</p>
                        <p class="text-sm text-gray-500">Get notified of new login attempts</p>
                    </div>
                    <input v-model="settings.security.loginAlerts" type="checkbox"
                        class="w-4 h-4 text-indigo-600 border-gray-300 rounded focus:ring-indigo-500" />
                </div>
            </div>

            <div class="flex gap-3 pt-6">
                <button @click="saveSettings"
                    class="px-6 py-2 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700 transition-colors">
                    Update Security
                </button>
            </div>
        </div>

        <!-- Appearance Settings -->
        <div v-else class="bg-white rounded-lg shadow-sm p-6">
            <h2 class="text-xl font-semibold text-gray-800 mb-6">Appearance Settings</h2>
            
            <div class="space-y-6">
                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">Theme</label>
                    <select v-model="settings.appearance.theme"
                        class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent">
                        <option value="light">Light</option>
                        <option value="dark">Dark</option>
                        <option value="auto">Auto</option>
                    </select>
                </div>

                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">Accent Color</label>
                    <div class="flex gap-3">
                        <div v-for="color in accentColors" :key="color"
                            @click="settings.appearance.accentColor = color" :class="[
                                'w-10 h-10 rounded-lg cursor-pointer border-2 transition-all',
                                settings.appearance.accentColor === color ? 'border-gray-900 scale-110' : 'border-transparent'
                            ]" :style="{ backgroundColor: color }"></div>
                    </div>
                </div>

                <div class="flex items-center">
                    <input v-model="settings.appearance.compactMode" type="checkbox"
                        class="w-4 h-4 text-indigo-600 border-gray-300 rounded focus:ring-indigo-500" />
                    <label class="ml-2 text-sm text-gray-700">Enable Compact Mode</label>
                </div>
            </div>

            <div class="flex gap-3 pt-6">
                <button @click="saveSettings"
                    class="px-6 py-2 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700 transition-colors">
                    Save Changes
                </button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import Toast from '../components/Toast.vue'
import DeleteModal from '../components/DeleteModal.vue'

const activeTab = ref('general')
const toast = ref({
    show: false,
    type: 'info',
    message: '',
    description: ''
})

const deleteModal = ref({
    show: false,
    title: 'Reset Settings',
    message: 'Are you sure you want to reset all settings to their default values? This action cannot be undone.',
    itemName: 'All system settings',
})

const settings = ref({
    general: {
        systemName: 'DriveDex',
        contactEmail: 'admin@drivedex.com',
        timezone: 'UTC',
        language: 'en',
        maintenanceMode: false
    },
    notifications: {
        email: true,
        sms: false,
        push: true,
        tripUpdates: true,
        maintenance: true
    },
    security: {
        twoFactor: false,
        loginAlerts: true
    },
    appearance: {
        theme: 'light',
        accentColor: '#4F46E5',
        compactMode: false
    }
})

const accentColors = ['#4F46E5', '#7C3AED', '#DB2777', '#DC2626', '#EA580C', '#16A34A', '#0891B2']

const showToast = (type, message, description = '') => {
    toast.value = { show: true, type, message, description }
}

const saveSettings = () => {
    showToast('success', 'Settings Saved', 'Your settings have been updated successfully')
}

const resetSettings = () => {
    deleteModal.value.show = true
}

const confirmReset = () => {
    showToast('success', 'Settings Reset', 'All settings have been reset to default values')
    deleteModal.value.show = false
}

const cancelReset = () => {
    deleteModal.value.show = false
}

onMounted(() => {
    showToast('success', 'Welcome!', 'Settings page loaded')
})
</script>
