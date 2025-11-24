<template>
    <div class="p-8">
        <!-- Toast Component -->
        <Toast :show="toast.show" :type="toast.type" :message="toast.message" :description="toast.description"
            @close="toast.show = false" />

        <!-- Delete Confirmation Modal -->
        <DeleteModal :show="deleteModal.show" :title="deleteModal.title" :message="deleteModal.message"
            :itemName="deleteModal.itemName" @confirm="confirmDelete" @cancel="cancelDelete" />

        <!-- Header -->
        <div class="mb-6">
            <h1 class="text-3xl font-bold text-gray-800">All Buses</h1>
            <p class="text-gray-600 mt-2">Manage your bus fleet and track vehicle status</p>
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
                        <input v-model="searchQuery" type="text" placeholder="Search buses..."
                            class="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent" />
                    </div>
                </div>
                <div class="flex gap-3">
                    <button @click="loadBuses"
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
                        Add Bus
                    </button>
                </div>
            </div>
        </div>

        <!-- Stats Cards -->
        <div class="grid grid-cols-1 md:grid-cols-5 gap-6 mb-6">
            <div class="bg-white rounded-lg shadow-sm p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500">Total Buses</p>
                        <p class="text-2xl font-bold text-gray-800 mt-1">{{ buses.length }}</p>
                    </div>
                    <div class="w-12 h-12 bg-indigo-100 rounded-lg flex items-center justify-center">
                        <svg class="w-6 h-6 text-indigo-600" fill="currentColor" viewBox="0 0 24 24">
                            <path d="M18.92 6.01C18.72 5.42 18.16 5 17.5 5h-11c-.66 0-1.21.42-1.42 1.01L3 12v8c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-1h12v1c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-8l-2.08-5.99z"/>
                        </svg>
                    </div>
                </div>
            </div>
            <div class="bg-white rounded-lg shadow-sm p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500">Active</p>
                        <p class="text-2xl font-bold text-green-600 mt-1">{{ activeBuses }}</p>
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
                        <p class="text-sm text-gray-500">In Maintenance</p>
                        <p class="text-2xl font-bold text-yellow-600 mt-1">{{ maintenanceBuses }}</p>
                    </div>
                    <div class="w-12 h-12 bg-yellow-100 rounded-lg flex items-center justify-center">
                        <svg class="w-6 h-6 text-yellow-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M10.325 4.317c.426-1.756 2.924-1.756 3.35 0a1.724 1.724 0 002.573 1.066c1.543-.94 3.31.826 2.37 2.37a1.724 1.724 0 001.065 2.572c1.756.426 1.756 2.924 0 3.35a1.724 1.724 0 00-1.066 2.573c.94 1.543-.826 3.31-2.37 2.37a1.724 1.724 0 00-2.572 1.065c-.426 1.756-2.924 1.756-3.35 0a1.724 1.724 0 00-2.573-1.066c-1.543.94-3.31-.826-2.37-2.37a1.724 1.724 0 00-1.065-2.572c-1.756-.426-1.756-2.924 0-3.35a1.724 1.724 0 001.066-2.573c-.94-1.543.826-3.31 2.37-2.37.996.608 2.296.07 2.572-1.065z" />
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
                        </svg>
                    </div>
                </div>
            </div>
            <div class="bg-white rounded-lg shadow-sm p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500">Offline</p>
                        <p class="text-2xl font-bold text-red-600 mt-1">{{ offlineBuses }}</p>
                    </div>
                    <div class="w-12 h-12 bg-red-100 rounded-lg flex items-center justify-center">
                        <svg class="w-6 h-6 text-red-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M18.364 18.364A9 9 0 005.636 5.636m12.728 12.728A9 9 0 015.636 5.636m12.728 12.728L5.636 5.636" />
                        </svg>
                    </div>
                </div>
            </div>
            <div class="bg-white rounded-lg shadow-sm p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500">Idle</p>
                        <p class="text-2xl font-bold text-gray-600 mt-1">{{ idleBuses }}</p>
                    </div>
                    <div class="w-12 h-12 bg-gray-100 rounded-lg flex items-center justify-center">
                        <svg class="w-6 h-6 text-gray-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                        </svg>
                    </div>
                </div>
            </div>
        </div>

        <!-- Buses Grid -->
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            <div v-if="loading" v-for="n in 6" :key="n" class="bg-white rounded-lg shadow-sm p-6 animate-pulse">
                <div class="h-4 bg-gray-200 rounded w-3/4 mb-4"></div>
                <div class="h-3 bg-gray-200 rounded w-full mb-2"></div>
                <div class="h-3 bg-gray-200 rounded w-5/6"></div>
            </div>

            <div v-else-if="filteredBuses.length === 0" class="col-span-full">
                <div class="bg-white rounded-lg shadow-sm p-12 text-center">
                    <svg class="w-16 h-16 mx-auto text-gray-400 mb-4" fill="currentColor" viewBox="0 0 24 24">
                        <path d="M18.92 6.01C18.72 5.42 18.16 5 17.5 5h-11c-.66 0-1.21.42-1.42 1.01L3 12v8c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-1h12v1c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-8l-2.08-5.99z"/>
                    </svg>
                    <p class="text-lg font-semibold text-gray-900">No buses found</p>
                    <p class="text-sm text-gray-500 mt-1">Get started by adding your first bus</p>
                </div>
            </div>

            <div v-else v-for="bus in filteredBuses" :key="bus.id"
                class="bg-white rounded-lg shadow-sm hover:shadow-md transition-shadow border-l-4"
                :class="{
                    'border-green-500': bus.status === 'active',
                    'border-yellow-500': bus.status === 'maintenance',
                    'border-red-500': bus.status === 'offline',
                    'border-gray-500': bus.status === 'idle'
                }">
                <div class="p-6">
                    <div class="flex items-start justify-between mb-4">
                        <div class="flex-1">
                            <h3 class="text-lg font-semibold text-gray-900">{{ bus.name }}</h3>
                            <p class="text-sm text-gray-500 mt-1">{{ bus.model }}</p>
                        </div>
                        <span :class="[
                            'px-2 py-1 text-xs font-semibold rounded-full',
                            bus.status === 'active' ? 'bg-green-100 text-green-800' :
                                bus.status === 'maintenance' ? 'bg-yellow-100 text-yellow-800' :
                                    bus.status === 'offline' ? 'bg-red-100 text-red-800' :
                                        'bg-gray-100 text-gray-800'
                        ]">
                            {{ bus.status }}
                        </span>
                    </div>

                    <div class="space-y-3 mb-4">
                        <div class="flex items-center gap-2 text-sm text-gray-600">
                            <svg class="w-4 h-4" fill="currentColor" viewBox="0 0 24 24">
                                <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/>
                            </svg>
                            <span>Driver: {{ bus.driver || 'Not Assigned' }}</span>
                        </div>
                        <div class="flex items-center gap-2 text-sm text-gray-600">
                            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                    d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                    d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                            </svg>
                            <span>Route: {{ bus.route || 'Not Assigned' }}</span>
                        </div>
                        <div class="flex items-center gap-2 text-sm text-gray-600">
                            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                    d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                            </svg>
                            <span>Capacity: {{ bus.capacity }} seats</span>
                        </div>
                        <div class="flex items-center gap-2 text-sm text-gray-600">
                            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                    d="M9 7h6m0 10v-3m-3 3h.01M9 17h.01M9 14h.01M12 14h.01M15 11h.01M12 11h.01M9 11h.01M7 21h10a2 2 0 002-2V5a2 2 0 00-2-2H7a2 2 0 00-2 2v14a2 2 0 002 2z" />
                            </svg>
                            <span>Plate: {{ bus.plateNumber }}</span>
                        </div>
                    </div>

                    <div class="flex items-center gap-2 pt-4 border-t border-gray-200">
                        <button @click="viewBus(bus)"
                            class="flex-1 px-3 py-2 bg-indigo-600 text-white text-sm rounded-lg hover:bg-indigo-700 transition-colors flex items-center justify-center gap-2">
                            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                    d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                    d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z" />
                            </svg>
                            View
                        </button>
                        <button @click="editBus(bus)"
                            class="px-3 py-2 bg-white border border-gray-300 text-gray-700 text-sm rounded-lg hover:bg-gray-50 transition-colors">
                            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                    d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z" />
                            </svg>
                        </button>
                        <button @click="deleteBus(bus)"
                            class="px-3 py-2 bg-white border border-gray-300 text-red-600 text-sm rounded-lg hover:bg-red-50 transition-colors">
                            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                    d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
                            </svg>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import Toast from '../components/Toast.vue'
import DeleteModal from '../components/DeleteModal.vue'

const searchQuery = ref('')
const loading = ref(false)
const toast = ref({
    show: false,
    type: 'info',
    message: '',
    description: ''
})

const deleteModal = ref({
    show: false,
    title: 'Delete Bus',
    message: 'Are you sure you want to delete this bus? This will remove it from the fleet and all associated records.',
    itemName: '',
    busToDelete: null
})

const buses = ref([
    {
        id: 'BUS001',
        name: 'Bus A-101',
        model: 'Mercedes Sprinter 2023',
        plateNumber: 'ABC-1234',
        capacity: 50,
        driver: 'John Doe',
        route: 'Downtown → Airport',
        status: 'active'
    },
    {
        id: 'BUS002',
        name: 'Bus B-202',
        model: 'Volvo 9700 2022',
        plateNumber: 'DEF-5678',
        capacity: 45,
        driver: 'Jane Smith',
        route: 'Suburb A → City Center',
        status: 'idle'
    },
    {
        id: 'BUS003',
        name: 'Bus C-303',
        model: 'Mercedes Sprinter 2023',
        plateNumber: 'GHI-9012',
        capacity: 50,
        driver: 'Mike Johnson',
        route: 'Route 5 Express',
        status: 'active'
    },
    {
        id: 'BUS004',
        name: 'Bus D-404',
        model: 'Scania K360 2021',
        plateNumber: 'JKL-3456',
        capacity: 55,
        driver: null,
        route: null,
        status: 'maintenance'
    },
    {
        id: 'BUS005',
        name: 'Bus E-505',
        model: 'Volvo 9700 2022',
        plateNumber: 'MNO-7890',
        capacity: 45,
        driver: null,
        route: null,
        status: 'offline'
    },
    {
        id: 'BUS006',
        name: 'Bus F-606',
        model: 'Mercedes Sprinter 2023',
        plateNumber: 'PQR-1122',
        capacity: 50,
        driver: 'Robert Brown',
        route: 'North Loop',
        status: 'active'
    },
    {
        id: 'BUS007',
        name: 'Bus G-707',
        model: 'Scania K360 2021',
        plateNumber: 'STU-3344',
        capacity: 55,
        driver: null,
        route: null,
        status: 'idle'
    },
    {
        id: 'BUS008',
        name: 'Bus H-808',
        model: 'Volvo 9700 2022',
        plateNumber: 'VWX-5566',
        capacity: 45,
        driver: null,
        route: null,
        status: 'maintenance'
    }
])

const filteredBuses = computed(() => {
    if (!searchQuery.value) return buses.value
    const query = searchQuery.value.toLowerCase()
    return buses.value.filter(bus =>
        bus.name.toLowerCase().includes(query) ||
        bus.model.toLowerCase().includes(query) ||
        bus.plateNumber.toLowerCase().includes(query) ||
        (bus.driver && bus.driver.toLowerCase().includes(query)) ||
        (bus.route && bus.route.toLowerCase().includes(query))
    )
})

const activeBuses = computed(() => buses.value.filter(b => b.status === 'active').length)
const maintenanceBuses = computed(() => buses.value.filter(b => b.status === 'maintenance').length)
const offlineBuses = computed(() => buses.value.filter(b => b.status === 'offline').length)
const idleBuses = computed(() => buses.value.filter(b => b.status === 'idle').length)

const showToast = (type, message, description = '') => {
    toast.value = { show: true, type, message, description }
}

const loadBuses = () => {
    loading.value = true
    setTimeout(() => {
        loading.value = false
        showToast('success', 'Buses refreshed', 'Bus fleet list has been updated')
    }, 1000)
}

const openAddModal = () => {
    showToast('info', 'Add Bus', 'This feature will be implemented soon')
}

const viewBus = (bus) => {
    showToast('info', 'View Bus', `Viewing details for ${bus.name}`)
}

const editBus = (bus) => {
    showToast('info', 'Edit Bus', `Editing ${bus.name}`)
}

const deleteBus = (bus) => {
    deleteModal.value.show = true
    deleteModal.value.itemName = bus.name
    deleteModal.value.busToDelete = bus
}

const confirmDelete = () => {
    const bus = deleteModal.value.busToDelete
    const index = buses.value.findIndex(b => b.id === bus.id)
    if (index > -1) {
        buses.value.splice(index, 1)
        showToast('success', 'Bus deleted', `${bus.name} has been removed from the fleet`)
    }
    deleteModal.value.show = false
    deleteModal.value.busToDelete = null
}

const cancelDelete = () => {
    deleteModal.value.show = false
    deleteModal.value.busToDelete = null
}

onMounted(() => {
    showToast('success', 'Welcome!', 'Bus fleet management loaded')
})
</script>
