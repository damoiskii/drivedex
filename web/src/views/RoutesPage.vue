<template>
    <div class="p-8">
        <!-- Toast Component -->
        <Toast :show="toast.show" :type="toast.type" :message="toast.message" :description="toast.description"
            @close="toast.show = false" />

        <!-- Header -->
        <div class="mb-6">
            <h1 class="text-3xl font-bold text-gray-800">Routes</h1>
            <p class="text-gray-600 mt-2">Manage bus routes, schedules, and trip planning</p>
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
                        <input v-model="searchQuery" type="text" placeholder="Search routes..."
                            class="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent" />
                    </div>
                </div>
                <div class="flex gap-3">
                    <button @click="loadRoutes"
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
                        Create Route
                    </button>
                </div>
            </div>
        </div>

        <!-- Stats Cards -->
        <div class="grid grid-cols-1 md:grid-cols-4 gap-6 mb-6">
            <div class="bg-white rounded-lg shadow-sm p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500">Total Routes</p>
                        <p class="text-2xl font-bold text-gray-800 mt-1">{{ routes.length }}</p>
                    </div>
                    <div class="w-12 h-12 bg-indigo-100 rounded-lg flex items-center justify-center">
                        <svg class="w-6 h-6 text-indigo-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M9 20l-5.447-2.724A1 1 0 013 16.382V5.618a1 1 0 011.447-.894L9 7m0 13l6-3m-6 3V7m6 10l4.553 2.276A1 1 0 0021 18.382V7.618a1 1 0 00-.553-.894L15 4m0 13V4m0 0L9 7" />
                        </svg>
                    </div>
                </div>
            </div>
            <div class="bg-white rounded-lg shadow-sm p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500">Active Routes</p>
                        <p class="text-2xl font-bold text-green-600 mt-1">{{ activeRoutes }}</p>
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
                        <p class="text-sm text-gray-500">Daily Trips</p>
                        <p class="text-2xl font-bold text-blue-600 mt-1">{{ dailyTrips }}</p>
                    </div>
                    <div class="w-12 h-12 bg-blue-100 rounded-lg flex items-center justify-center">
                        <svg class="w-6 h-6 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                        </svg>
                    </div>
                </div>
            </div>
            <div class="bg-white rounded-lg shadow-sm p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500">Total Distance</p>
                        <p class="text-2xl font-bold text-purple-600 mt-1">{{ totalDistance }}km</p>
                    </div>
                    <div class="w-12 h-12 bg-purple-100 rounded-lg flex items-center justify-center">
                        <svg class="w-6 h-6 text-purple-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6" />
                        </svg>
                    </div>
                </div>
            </div>
        </div>

        <!-- Routes Grid -->
        <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div v-if="loading" v-for="n in 4" :key="n" class="bg-white rounded-lg shadow-sm p-6 animate-pulse">
                <div class="h-4 bg-gray-200 rounded w-3/4 mb-4"></div>
                <div class="h-3 bg-gray-200 rounded w-full mb-2"></div>
                <div class="h-3 bg-gray-200 rounded w-5/6"></div>
            </div>

            <div v-else-if="filteredRoutes.length === 0" class="col-span-full">
                <div class="bg-white rounded-lg shadow-sm p-12 text-center">
                    <svg class="w-16 h-16 mx-auto text-gray-400 mb-4" fill="none" stroke="currentColor"
                        viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M9 20l-5.447-2.724A1 1 0 013 16.382V5.618a1 1 0 011.447-.894L9 7m0 13l6-3m-6 3V7m6 10l4.553 2.276A1 1 0 0021 18.382V7.618a1 1 0 00-.553-.894L15 4m0 13V4m0 0L9 7" />
                    </svg>
                    <p class="text-lg font-semibold text-gray-900">No routes found</p>
                    <p class="text-sm text-gray-500 mt-1">Get started by creating your first route</p>
                </div>
            </div>

            <div v-else v-for="route in filteredRoutes" :key="route.id"
                class="bg-white rounded-lg shadow-sm hover:shadow-md transition-shadow border-l-4"
                :class="route.status === 'active' ? 'border-green-500' : 'border-gray-300'">
                <div class="p-6">
                    <div class="flex items-start justify-between mb-4">
                        <div class="flex-1">
                            <h3 class="text-lg font-semibold text-gray-900">{{ route.name }}</h3>
                            <p class="text-sm text-gray-500 mt-1">{{ route.description }}</p>
                        </div>
                        <span :class="[
                            'px-2 py-1 text-xs font-semibold rounded-full',
                            route.status === 'active' ? 'bg-green-100 text-green-800' : 'bg-gray-100 text-gray-800'
                        ]">
                            {{ route.status }}
                        </span>
                    </div>

                    <div class="space-y-3 mb-4">
                        <div class="flex items-start gap-3">
                            <svg class="w-5 h-5 text-green-600 mt-0.5 flex-shrink-0" fill="currentColor"
                                viewBox="0 0 24 24">
                                <circle cx="12" cy="12" r="3" />
                            </svg>
                            <div>
                                <p class="text-sm font-medium text-gray-900">{{ route.start }}</p>
                                <p class="text-xs text-gray-500">Starting point</p>
                            </div>
                        </div>
                        <div class="ml-2 h-8 w-0.5 bg-gray-300"></div>
                        <div class="flex items-start gap-3">
                            <svg class="w-5 h-5 text-red-600 mt-0.5 flex-shrink-0" fill="currentColor"
                                viewBox="0 0 24 24">
                                <path
                                    d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z" />
                            </svg>
                            <div>
                                <p class="text-sm font-medium text-gray-900">{{ route.end }}</p>
                                <p class="text-xs text-gray-500">Destination</p>
                            </div>
                        </div>
                    </div>

                    <div class="grid grid-cols-2 gap-4 mb-4 p-3 bg-gray-50 rounded-lg">
                        <div>
                            <p class="text-xs text-gray-500">Distance</p>
                            <p class="text-sm font-semibold text-gray-900">{{ route.distance }} km</p>
                        </div>
                        <div>
                            <p class="text-xs text-gray-500">Est. Duration</p>
                            <p class="text-sm font-semibold text-gray-900">{{ route.duration }}</p>
                        </div>
                        <div>
                            <p class="text-xs text-gray-500">Stops</p>
                            <p class="text-sm font-semibold text-gray-900">{{ route.stops }}</p>
                        </div>
                        <div>
                            <p class="text-xs text-gray-500">Frequency</p>
                            <p class="text-sm font-semibold text-gray-900">{{ route.frequency }}</p>
                        </div>
                    </div>

                    <div class="flex items-center gap-2 pt-4 border-t border-gray-200">
                        <button @click="viewRoute(route)"
                            class="flex-1 px-3 py-2 bg-indigo-600 text-white text-sm rounded-lg hover:bg-indigo-700 transition-colors flex items-center justify-center gap-2">
                            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                    d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                    d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z" />
                            </svg>
                            View Details
                        </button>
                        <button @click="editRoute(route)"
                            class="px-3 py-2 bg-white border border-gray-300 text-gray-700 text-sm rounded-lg hover:bg-gray-50 transition-colors">
                            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                    d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z" />
                            </svg>
                        </button>
                        <button @click="deleteRoute(route)"
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

const searchQuery = ref('')
const loading = ref(false)
const toast = ref({
    show: false,
    type: 'info',
    message: '',
    description: ''
})

const routes = ref([
    {
        id: 'ROUTE001',
        name: 'Route 1: Downtown Express',
        description: 'Fast route connecting downtown to airport',
        start: 'Downtown Terminal',
        end: 'Airport Terminal',
        distance: 25,
        duration: '45 min',
        stops: 8,
        frequency: 'Every 30 min',
        status: 'active'
    },
    {
        id: 'ROUTE002',
        name: 'Route 2: City Loop',
        description: 'Circular route covering main city areas',
        start: 'City Center',
        end: 'City Center',
        distance: 18,
        duration: '60 min',
        stops: 15,
        frequency: 'Every 20 min',
        status: 'active'
    },
    {
        id: 'ROUTE003',
        name: 'Route 3: Suburban Line',
        description: 'Connects suburbs to city center',
        start: 'Suburb A Station',
        end: 'City Center',
        distance: 32,
        duration: '75 min',
        stops: 12,
        frequency: 'Every 45 min',
        status: 'active'
    },
    {
        id: 'ROUTE004',
        name: 'Route 4: North-South Corridor',
        description: 'Main arterial route from north to south',
        start: 'North Terminal',
        end: 'South Station',
        distance: 40,
        duration: '90 min',
        stops: 18,
        frequency: 'Every 40 min',
        status: 'active'
    },
    {
        id: 'ROUTE005',
        name: 'Route 5: Express Line',
        description: 'Limited stops express service',
        start: 'Downtown Terminal',
        end: 'North Terminal',
        distance: 22,
        duration: '35 min',
        stops: 5,
        frequency: 'Every 60 min',
        status: 'inactive'
    }
])

const filteredRoutes = computed(() => {
    if (!searchQuery.value) return routes.value
    const query = searchQuery.value.toLowerCase()
    return routes.value.filter(route =>
        route.name.toLowerCase().includes(query) ||
        route.description.toLowerCase().includes(query) ||
        route.start.toLowerCase().includes(query) ||
        route.end.toLowerCase().includes(query)
    )
})

const activeRoutes = computed(() => routes.value.filter(r => r.status === 'active').length)
const dailyTrips = computed(() => routes.value.filter(r => r.status === 'active').length * 20)
const totalDistance = computed(() => routes.value.reduce((sum, r) => sum + r.distance, 0))

const showToast = (type, message, description = '') => {
    toast.value = { show: true, type, message, description }
}

const loadRoutes = () => {
    loading.value = true
    setTimeout(() => {
        loading.value = false
        showToast('success', 'Routes refreshed', 'Route list has been updated')
    }, 1000)
}

const openAddModal = () => {
    showToast('info', 'Create Route', 'This feature will be implemented soon')
}

const viewRoute = (route) => {
    showToast('info', 'View Route', `Viewing details for ${route.name}`)
}

const editRoute = (route) => {
    showToast('info', 'Edit Route', `Editing ${route.name}`)
}

const deleteRoute = (route) => {
    if (confirm(`Are you sure you want to delete "${route.name}"?`)) {
        const index = routes.value.findIndex(r => r.id === route.id)
        if (index > -1) {
            routes.value.splice(index, 1)
            showToast('success', 'Route deleted', `${route.name} has been removed`)
        }
    }
}

onMounted(() => {
    showToast('success', 'Welcome!', 'Route management system loaded')
})
</script>
