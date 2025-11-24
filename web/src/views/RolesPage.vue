<template>
    <div class="p-8">
        <!-- Toast Component -->
        <Toast :show="toast.show" :type="toast.type" :message="toast.message" :description="toast.description"
            @close="toast.show = false" />

        <!-- Header -->
        <div class="mb-6">
            <h1 class="text-3xl font-bold text-gray-800">Roles & Permissions</h1>
            <p class="text-gray-600 mt-2">Manage user roles and access permissions</p>
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
                        <input v-model="searchQuery" type="text" placeholder="Search roles..."
                            class="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent" />
                    </div>
                </div>
                <div class="flex gap-3">
                    <button @click="loadRoles"
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
                        Create Role
                    </button>
                </div>
            </div>
        </div>

        <!-- Stats Cards -->
        <div class="grid grid-cols-1 md:grid-cols-4 gap-6 mb-6">
            <div class="bg-white rounded-lg shadow-sm p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500">Total Roles</p>
                        <p class="text-2xl font-bold text-gray-800 mt-1">{{ roles.length }}</p>
                    </div>
                    <div class="w-12 h-12 bg-indigo-100 rounded-lg flex items-center justify-center">
                        <svg class="w-6 h-6 text-indigo-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z" />
                        </svg>
                    </div>
                </div>
            </div>
            <div class="bg-white rounded-lg shadow-sm p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500">Active Roles</p>
                        <p class="text-2xl font-bold text-green-600 mt-1">{{ activeRoles }}</p>
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
                        <p class="text-sm text-gray-500">Total Users</p>
                        <p class="text-2xl font-bold text-blue-600 mt-1">{{ totalUsersWithRoles }}</p>
                    </div>
                    <div class="w-12 h-12 bg-blue-100 rounded-lg flex items-center justify-center">
                        <svg class="w-6 h-6 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z" />
                        </svg>
                    </div>
                </div>
            </div>
            <div class="bg-white rounded-lg shadow-sm p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500">Permissions</p>
                        <p class="text-2xl font-bold text-purple-600 mt-1">{{ totalPermissions }}</p>
                    </div>
                    <div class="w-12 h-12 bg-purple-100 rounded-lg flex items-center justify-center">
                        <svg class="w-6 h-6 text-purple-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M15 7a2 2 0 012 2m4 0a6 6 0 01-7.743 5.743L11 17H9v2H7v2H4a1 1 0 01-1-1v-2.586a1 1 0 01.293-.707l5.964-5.964A6 6 0 1121 9z" />
                        </svg>
                    </div>
                </div>
            </div>
        </div>

        <!-- Roles Grid -->
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            <div v-if="loading" v-for="n in 6" :key="n" class="bg-white rounded-lg shadow-sm p-6 animate-pulse">
                <div class="h-4 bg-gray-200 rounded w-3/4 mb-4"></div>
                <div class="h-3 bg-gray-200 rounded w-full mb-2"></div>
                <div class="h-3 bg-gray-200 rounded w-5/6"></div>
            </div>

            <div v-else-if="filteredRoles.length === 0" class="col-span-full">
                <div class="bg-white rounded-lg shadow-sm p-12 text-center">
                    <svg class="w-16 h-16 mx-auto text-gray-400 mb-4" fill="none" stroke="currentColor"
                        viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z" />
                    </svg>
                    <p class="text-lg font-semibold text-gray-900">No roles found</p>
                    <p class="text-sm text-gray-500 mt-1">Get started by creating your first role</p>
                </div>
            </div>

            <div v-else v-for="role in filteredRoles" :key="role.id"
                class="bg-white rounded-lg shadow-sm p-6 hover:shadow-md transition-shadow border-l-4"
                :class="role.status === 'active' ? 'border-green-500' : 'border-gray-300'">
                <div class="flex items-start justify-between mb-4">
                    <div class="flex-1">
                        <h3 class="text-lg font-semibold text-gray-900">{{ role.name }}</h3>
                        <p class="text-sm text-gray-500 mt-1">{{ role.description }}</p>
                    </div>
                    <span :class="[
                        'px-2 py-1 text-xs font-semibold rounded-full',
                        role.status === 'active' ? 'bg-green-100 text-green-800' : 'bg-gray-100 text-gray-800'
                    ]">
                        {{ role.status }}
                    </span>
                </div>

                <div class="mb-4">
                    <div class="flex items-center gap-2 text-sm text-gray-600 mb-2">
                        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z" />
                        </svg>
                        <span>{{ role.users }} users</span>
                    </div>
                    <div class="flex items-center gap-2 text-sm text-gray-600">
                        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M15 7a2 2 0 012 2m4 0a6 6 0 01-7.743 5.743L11 17H9v2H7v2H4a1 1 0 01-1-1v-2.586a1 1 0 01.293-.707l5.964-5.964A6 6 0 1121 9z" />
                        </svg>
                        <span>{{ role.permissions.length }} permissions</span>
                    </div>
                </div>

                <div class="mb-4">
                    <p class="text-xs font-semibold text-gray-500 uppercase mb-2">Permissions</p>
                    <div class="flex flex-wrap gap-1">
                        <span v-for="perm in role.permissions.slice(0, 4)" :key="perm"
                            class="px-2 py-1 bg-indigo-50 text-indigo-700 text-xs rounded">
                            {{ perm }}
                        </span>
                        <span v-if="role.permissions.length > 4"
                            class="px-2 py-1 bg-gray-100 text-gray-600 text-xs rounded">
                            +{{ role.permissions.length - 4 }} more
                        </span>
                    </div>
                </div>

                <div class="flex items-center gap-2 pt-4 border-t border-gray-200">
                    <button @click="editRole(role)"
                        class="flex-1 px-3 py-2 bg-indigo-600 text-white text-sm rounded-lg hover:bg-indigo-700 transition-colors flex items-center justify-center gap-2">
                        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z" />
                        </svg>
                        Edit
                    </button>
                    <button @click="deleteRole(role)"
                        class="px-3 py-2 bg-white border border-gray-300 text-gray-700 text-sm rounded-lg hover:bg-gray-50 transition-colors">
                        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
                        </svg>
                    </button>
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

const roles = ref([
    {
        id: 'ROLE001',
        name: 'Administrator',
        description: 'Full system access with all permissions',
        status: 'active',
        users: 3,
        permissions: ['create', 'read', 'update', 'delete', 'manage_users', 'manage_roles', 'system_config']
    },
    {
        id: 'ROLE002',
        name: 'Fleet Manager',
        description: 'Manage buses, drivers, and routes',
        status: 'active',
        users: 5,
        permissions: ['read', 'update', 'manage_fleet', 'manage_drivers', 'view_reports']
    },
    {
        id: 'ROLE003',
        name: 'Driver',
        description: 'Access to assigned routes and trips',
        status: 'active',
        users: 8,
        permissions: ['read', 'update_profile', 'view_routes', 'update_trip_status']
    },
    {
        id: 'ROLE004',
        name: 'Passenger',
        description: 'Book trips and manage bookings',
        status: 'active',
        users: 96,
        permissions: ['read', 'book_trip', 'cancel_booking', 'view_history']
    },
    {
        id: 'ROLE005',
        name: 'HR Manager',
        description: 'Manage employees and departments',
        status: 'active',
        users: 2,
        permissions: ['read', 'create', 'update', 'manage_employees', 'view_payroll']
    },
    {
        id: 'ROLE006',
        name: 'Maintenance Staff',
        description: 'Manage vehicle maintenance and repairs',
        status: 'active',
        users: 4,
        permissions: ['read', 'update', 'manage_maintenance', 'view_vehicles']
    }
])

const filteredRoles = computed(() => {
    if (!searchQuery.value) return roles.value
    const query = searchQuery.value.toLowerCase()
    return roles.value.filter(role =>
        role.name.toLowerCase().includes(query) ||
        role.description.toLowerCase().includes(query)
    )
})

const activeRoles = computed(() => roles.value.filter(r => r.status === 'active').length)
const totalUsersWithRoles = computed(() => roles.value.reduce((sum, r) => sum + r.users, 0))
const totalPermissions = computed(() => {
    const allPerms = new Set()
    roles.value.forEach(role => {
        role.permissions.forEach(perm => allPerms.add(perm))
    })
    return allPerms.size
})

const showToast = (type, message, description = '') => {
    toast.value = { show: true, type, message, description }
}

const loadRoles = () => {
    loading.value = true
    setTimeout(() => {
        loading.value = false
        showToast('success', 'Roles refreshed', 'Role list has been updated')
    }, 1000)
}

const openAddModal = () => {
    showToast('info', 'Create Role', 'This feature will be implemented soon')
}

const editRole = (role) => {
    showToast('info', 'Edit Role', `Editing ${role.name}`)
}

const deleteRole = (role) => {
    if (confirm(`Are you sure you want to delete the role "${role.name}"?`)) {
        const index = roles.value.findIndex(r => r.id === role.id)
        if (index > -1) {
            roles.value.splice(index, 1)
            showToast('success', 'Role deleted', `${role.name} has been removed`)
        }
    }
}

onMounted(() => {
    showToast('success', 'Welcome!', 'Roles & Permissions management loaded')
})
</script>
