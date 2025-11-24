<template>
    <div class="p-8">
        <!-- Toast Component -->
        <Toast :show="toast.show" :type="toast.type" :message="toast.message" :description="toast.description"
            @close="toast.show = false" />

        <!-- Header -->
        <div class="mb-6">
            <h1 class="text-3xl font-bold text-gray-800">Drivers</h1>
            <p class="text-gray-600 mt-2">Manage driver profiles, licenses, and assignments</p>
        </div>

        <!-- Action Bar -->
        <div class="bg-white rounded-lg shadow-sm p-4 mb-6">
            <div class="flex flex-col md:flex-row gap-4 items-center justify-between">
                <div class="flex-1 w-full md:w-auto">
                    <div class="relative">
                        <svg class="absolute left-3 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" fill="none"
                            stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
                        </svg>
                        <input v-model="searchQuery" type="text" placeholder="Search drivers..."
                            class="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent" />
                    </div>
                </div>
                <div class="flex gap-3">
                    <button @click="loadDrivers"
                        class="px-4 py-2 text-gray-700 bg-white border border-gray-300 rounded-lg hover:bg-gray-50 transition-colors flex items-center gap-2">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15" />
                        </svg>
                        Refresh
                    </button>
                    <button @click="openAddModal"
                        class="px-4 py-2 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700 transition-colors flex items-center gap-2">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M12 4v16m8-8H4" />
                        </svg>
                        Add Driver
                    </button>
                </div>
            </div>
        </div>

        <!-- Stats Cards -->
        <div class="grid grid-cols-1 md:grid-cols-4 gap-6 mb-6">
            <div class="bg-white rounded-lg shadow-sm p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500">Total Drivers</p>
                        <p class="text-2xl font-bold text-gray-800 mt-1">{{ drivers.length }}</p>
                    </div>
                    <div class="w-12 h-12 bg-indigo-100 rounded-lg flex items-center justify-center">
                        <svg class="w-6 h-6 text-indigo-600" fill="currentColor" viewBox="0 0 24 24">
                            <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/>
                        </svg>
                    </div>
                </div>
            </div>
            <div class="bg-white rounded-lg shadow-sm p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500">On Duty</p>
                        <p class="text-2xl font-bold text-green-600 mt-1">{{ onDutyDrivers }}</p>
                    </div>
                    <div class="w-12 h-12 bg-green-100 rounded-lg flex items-center justify-center">
                        <svg class="w-6 h-6 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                        </svg>
                    </div>
                </div>
            </div>
            <div class="bg-white rounded-lg shadow-sm p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500">Off Duty</p>
                        <p class="text-2xl font-bold text-gray-600 mt-1">{{ offDutyDrivers }}</p>
                    </div>
                    <div class="w-12 h-12 bg-gray-100 rounded-lg flex items-center justify-center">
                        <svg class="w-6 h-6 text-gray-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M20.354 15.354A9 9 0 018.646 3.646 9.003 9.003 0 0012 21a9.003 9.003 0 008.354-5.646z" />
                        </svg>
                    </div>
                </div>
            </div>
            <div class="bg-white rounded-lg shadow-sm p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500">License Expiring</p>
                        <p class="text-2xl font-bold text-yellow-600 mt-1">{{ expiringLicenses }}</p>
                    </div>
                    <div class="w-12 h-12 bg-yellow-100 rounded-lg flex items-center justify-center">
                        <svg class="w-6 h-6 text-yellow-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z" />
                        </svg>
                    </div>
                </div>
            </div>
        </div>

        <!-- Drivers Table -->
        <div class="bg-white rounded-lg shadow-sm overflow-hidden">
            <div class="overflow-x-auto">
                <table class="w-full">
                    <thead class="bg-gray-50 border-b border-gray-200">
                        <tr>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                Driver</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                License No.</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                License Expiry</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                Assigned Bus</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                Status</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                Actions</th>
                        </tr>
                    </thead>
                    <tbody class="bg-white divide-y divide-gray-200">
                        <tr v-if="loading">
                            <td colspan="6" class="px-6 py-12 text-center">
                                <div class="flex items-center justify-center">
                                    <svg class="animate-spin h-8 w-8 text-indigo-600" fill="none" viewBox="0 0 24 24">
                                        <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor"
                                            stroke-width="4"></circle>
                                        <path class="opacity-75" fill="currentColor"
                                            d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z">
                                        </path>
                                    </svg>
                                </div>
                            </td>
                        </tr>
                        <tr v-else-if="filteredDrivers.length === 0">
                            <td colspan="6" class="px-6 py-12 text-center text-gray-500">
                                <svg class="w-16 h-16 mx-auto text-gray-400 mb-4" fill="none" stroke="currentColor"
                                    viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                        d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z" />
                                </svg>
                                <p class="text-lg font-semibold">No drivers found</p>
                                <p class="text-sm mt-1">Get started by adding your first driver</p>
                            </td>
                        </tr>
                        <tr v-else v-for="driver in filteredDrivers" :key="driver.id"
                            class="hover:bg-gray-50 transition-colors">
                            <td class="px-6 py-4 whitespace-nowrap">
                                <div class="flex items-center">
                                    <div
                                        class="w-10 h-10 bg-indigo-100 rounded-full flex items-center justify-center text-indigo-600 font-semibold">
                                        {{ driver.name.charAt(0) }}
                                    </div>
                                    <div class="ml-4">
                                        <div class="text-sm font-medium text-gray-900">{{ driver.name }}</div>
                                        <div class="text-sm text-gray-500">{{ driver.phone }}</div>
                                    </div>
                                </div>
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap">
                                <div class="text-sm text-gray-900">{{ driver.licenseNo }}</div>
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap">
                                <div class="text-sm text-gray-900">{{ driver.licenseExpiry }}</div>
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap">
                                <div class="text-sm text-gray-900">{{ driver.assignedBus || 'Not Assigned' }}</div>
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap">
                                <span :class="[
                                    'px-2 py-1 inline-flex text-xs leading-5 font-semibold rounded-full',
                                    driver.status === 'on-duty' ? 'bg-green-100 text-green-800' :
                                        driver.status === 'off-duty' ? 'bg-gray-100 text-gray-800' :
                                            'bg-red-100 text-red-800'
                                ]">
                                    {{ driver.status }}
                                </span>
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                                <div class="flex items-center gap-3">
                                    <button @click="viewDriver(driver)" class="text-blue-600 hover:text-blue-900">
                                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
                                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z" />
                                        </svg>
                                    </button>
                                    <button @click="editDriver(driver)" class="text-indigo-600 hover:text-indigo-900">
                                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z" />
                                        </svg>
                                    </button>
                                    <button @click="deleteDriver(driver)" class="text-red-600 hover:text-red-900">
                                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
                                        </svg>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import Toast from '../components/Toast.vue'

const searchQuery = ref('')
const loading = ref(false)
const toast = ref({
    show: false,
    type: 'info',
    message: '',
    description: ''
})

const drivers = ref([
    {
        id: 'DRV001',
        name: 'John Doe',
        phone: '+1 234-567-8900',
        licenseNo: 'DL-2024-001',
        licenseExpiry: '2025-12-31',
        assignedBus: 'Bus A-101',
        status: 'on-duty'
    },
    {
        id: 'DRV002',
        name: 'Jane Smith',
        phone: '+1 234-567-8901',
        licenseNo: 'DL-2024-002',
        licenseExpiry: '2026-01-15',
        assignedBus: 'Bus B-202',
        status: 'off-duty'
    },
    {
        id: 'DRV003',
        name: 'Mike Johnson',
        phone: '+1 234-567-8902',
        licenseNo: 'DL-2024-003',
        licenseExpiry: '2025-11-30',
        assignedBus: 'Bus C-303',
        status: 'on-duty'
    },
    {
        id: 'DRV004',
        name: 'Sarah Williams',
        phone: '+1 234-567-8903',
        licenseNo: 'DL-2024-004',
        licenseExpiry: '2025-06-20',
        assignedBus: null,
        status: 'off-duty'
    },
    {
        id: 'DRV005',
        name: 'Robert Brown',
        phone: '+1 234-567-8904',
        licenseNo: 'DL-2024-005',
        licenseExpiry: '2026-03-10',
        assignedBus: 'Bus D-404',
        status: 'on-duty'
    }
])

const filteredDrivers = computed(() => {
    if (!searchQuery.value) return drivers.value
    const query = searchQuery.value.toLowerCase()
    return drivers.value.filter(driver =>
        driver.name.toLowerCase().includes(query) ||
        driver.phone.includes(query) ||
        driver.licenseNo.toLowerCase().includes(query) ||
        (driver.assignedBus && driver.assignedBus.toLowerCase().includes(query))
    )
})

const onDutyDrivers = computed(() => drivers.value.filter(d => d.status === 'on-duty').length)
const offDutyDrivers = computed(() => drivers.value.filter(d => d.status === 'off-duty').length)
const expiringLicenses = computed(() => {
    const today = new Date()
    const threeMonthsLater = new Date(today.setMonth(today.getMonth() + 3))
    return drivers.value.filter(d => new Date(d.licenseExpiry) <= threeMonthsLater).length
})

const showToast = (type, message, description = '') => {
    toast.value = { show: true, type, message, description }
}

const loadDrivers = () => {
    loading.value = true
    setTimeout(() => {
        loading.value = false
        showToast('success', 'Drivers refreshed', 'Driver list has been updated')
    }, 1000)
}

const openAddModal = () => {
    showToast('info', 'Add Driver', 'This feature will be implemented soon')
}

const viewDriver = (driver) => {
    showToast('info', 'View Driver', `Viewing details for ${driver.name}`)
}

const editDriver = (driver) => {
    showToast('info', 'Edit Driver', `Editing ${driver.name}`)
}

const deleteDriver = (driver) => {
    if (confirm(`Are you sure you want to delete ${driver.name}?`)) {
        const index = drivers.value.findIndex(d => d.id === driver.id)
        if (index > -1) {
            drivers.value.splice(index, 1)
            showToast('success', 'Driver deleted', `${driver.name} has been removed`)
        }
    }
}

onMounted(() => {
    showToast('success', 'Welcome!', 'Driver management system loaded')
})
</script>
