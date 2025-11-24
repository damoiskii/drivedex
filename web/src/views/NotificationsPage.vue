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
            <h1 class="text-3xl font-bold text-gray-800">Notifications</h1>
            <p class="text-gray-600 mt-2">View and manage system notifications</p>
        </div>

        <!-- Action Bar -->
        <div class="bg-white rounded-lg shadow-sm p-4 mb-6">
            <div class="flex flex-col md:flex-row gap-4 items-center justify-between">
                <div class="flex gap-3">
                    <button @click="filterType = 'all'" :class="[
                        'px-4 py-2 rounded-lg transition-colors text-sm',
                        filterType === 'all' ? 'bg-indigo-600 text-white' : 'bg-white text-gray-700 border border-gray-300 hover:bg-gray-50'
                    ]">
                        All ({{ notifications.length }})
                    </button>
                    <button @click="filterType = 'unread'" :class="[
                        'px-4 py-2 rounded-lg transition-colors text-sm',
                        filterType === 'unread' ? 'bg-indigo-600 text-white' : 'bg-white text-gray-700 border border-gray-300 hover:bg-gray-50'
                    ]">
                        Unread ({{ unreadCount }})
                    </button>
                </div>
                <div class="flex gap-3">
                    <button @click="markAllAsRead"
                        class="px-4 py-2 text-gray-700 bg-white border border-gray-300 rounded-lg hover:bg-gray-50 transition-colors text-sm">
                        Mark All as Read
                    </button>
                    <button @click="clearAll"
                        class="px-4 py-2 text-red-600 bg-white border border-red-300 rounded-lg hover:bg-red-50 transition-colors text-sm">
                        Clear All
                    </button>
                </div>
            </div>
        </div>

        <!-- Notifications List -->
        <div class="bg-white rounded-lg shadow-sm overflow-hidden">
            <div v-if="filteredNotifications.length === 0" class="p-12 text-center">
                <svg class="w-16 h-16 mx-auto text-gray-400 mb-4" fill="none" stroke="currentColor"
                    viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                        d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9" />
                </svg>
                <p class="text-lg font-semibold text-gray-900">No notifications</p>
                <p class="text-sm text-gray-500 mt-1">You're all caught up!</p>
            </div>

            <div v-else class="divide-y divide-gray-200">
                <div v-for="notification in filteredNotifications" :key="notification.id" :class="[
                    'p-6 hover:bg-gray-50 transition-colors cursor-pointer',
                    !notification.read && 'bg-indigo-50'
                ]" @click="markAsRead(notification)">
                    <div class="flex items-start gap-4">
                        <div :class="[
                            'w-12 h-12 rounded-full flex items-center justify-center flex-shrink-0',
                            notification.type === 'success' ? 'bg-green-100' :
                                notification.type === 'warning' ? 'bg-yellow-100' :
                                    notification.type === 'error' ? 'bg-red-100' :
                                        'bg-blue-100'
                        ]">
                            <svg class="w-6 h-6" :class="[
                                notification.type === 'success' ? 'text-green-600' :
                                    notification.type === 'warning' ? 'text-yellow-600' :
                                        notification.type === 'error' ? 'text-red-600' :
                                            'text-blue-600'
                            ]" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path v-if="notification.type === 'success'" stroke-linecap="round"
                                    stroke-linejoin="round" stroke-width="2"
                                    d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                                <path v-else-if="notification.type === 'warning'" stroke-linecap="round"
                                    stroke-linejoin="round" stroke-width="2"
                                    d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z" />
                                <path v-else-if="notification.type === 'error'" stroke-linecap="round"
                                    stroke-linejoin="round" stroke-width="2"
                                    d="M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z" />
                                <path v-else stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                    d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                            </svg>
                        </div>

                        <div class="flex-1 min-w-0">
                            <div class="flex items-start justify-between">
                                <div class="flex-1">
                                    <p :class="[
                                        'text-sm',
                                        !notification.read ? 'font-semibold text-gray-900' : 'font-medium text-gray-700'
                                    ]">
                                        {{ notification.title }}
                                    </p>
                                    <p class="text-sm text-gray-600 mt-1">{{ notification.message }}</p>
                                    <p class="text-xs text-gray-500 mt-2">{{ notification.time }}</p>
                                </div>
                                <div class="flex items-center gap-2 ml-4">
                                    <span v-if="!notification.read"
                                        class="w-2 h-2 bg-indigo-600 rounded-full flex-shrink-0"></span>
                                    <button @click.stop="deleteNotification(notification)"
                                        class="text-gray-400 hover:text-red-600 transition-colors">
                                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                d="M6 18L18 6M6 6l12 12" />
                                        </svg>
                                    </button>
                                </div>
                            </div>
                        </div>
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

const filterType = ref('all')
const toast = ref({
    show: false,
    type: 'info',
    message: '',
    description: ''
})

const deleteModal = ref({
    show: false,
    title: 'Clear All Notifications',
    message: 'Are you sure you want to clear all notifications? This will permanently remove all notifications from your inbox.',
    itemName: '',
    action: null
})

const notifications = ref([
    {
        id: 1,
        type: 'success',
        title: 'Trip Completed Successfully',
        message: 'Bus A-101 has completed Route 1 on time',
        time: '5 minutes ago',
        read: false
    },
    {
        id: 2,
        type: 'warning',
        title: 'Maintenance Due',
        message: 'Bus D-404 requires maintenance check in 2 days',
        time: '1 hour ago',
        read: false
    },
    {
        id: 3,
        type: 'info',
        title: 'New Driver Added',
        message: 'John Smith has been added to the driver roster',
        time: '2 hours ago',
        read: true
    },
    {
        id: 4,
        type: 'error',
        title: 'Route Delay Detected',
        message: 'Bus B-202 is running 15 minutes behind schedule',
        time: '3 hours ago',
        read: false
    },
    {
        id: 5,
        type: 'success',
        title: 'Payment Received',
        message: 'Monthly subscription payment processed successfully',
        time: '5 hours ago',
        read: true
    },
    {
        id: 6,
        type: 'info',
        title: 'System Update',
        message: 'New features have been added to the dashboard',
        time: '1 day ago',
        read: true
    },
    {
        id: 7,
        type: 'warning',
        title: 'License Expiring Soon',
        message: 'Driver license for Sarah Williams expires in 30 days',
        time: '1 day ago',
        read: false
    },
    {
        id: 8,
        type: 'success',
        title: 'Route Optimization Complete',
        message: 'Route 3 has been optimized for better efficiency',
        time: '2 days ago',
        read: true
    }
])

const filteredNotifications = computed(() => {
    if (filterType.value === 'unread') {
        return notifications.value.filter(n => !n.read)
    }
    return notifications.value
})

const unreadCount = computed(() => notifications.value.filter(n => !n.read).length)

const showToast = (type, message, description = '') => {
    toast.value = { show: true, type, message, description }
}

const markAsRead = (notification) => {
    if (!notification.read) {
        notification.read = true
        showToast('success', 'Marked as read', notification.title)
    }
}

const markAllAsRead = () => {
    notifications.value.forEach(n => n.read = true)
    showToast('success', 'All notifications marked as read', `${notifications.value.length} notifications updated`)
}

const deleteNotification = (notification) => {
    const index = notifications.value.findIndex(n => n.id === notification.id)
    if (index > -1) {
        notifications.value.splice(index, 1)
        showToast('success', 'Notification deleted', 'Notification has been removed')
    }
}

const clearAll = () => {
    deleteModal.value.show = true
    deleteModal.value.itemName = `${notifications.value.length} notifications`
    deleteModal.value.action = 'clearAll'
}

const confirmDelete = () => {
    if (deleteModal.value.action === 'clearAll') {
        const count = notifications.value.length
        notifications.value = []
        showToast('success', 'All notifications cleared', `${count} notifications deleted`)
    }
    deleteModal.value.show = false
    deleteModal.value.action = null
}

const cancelDelete = () => {
    deleteModal.value.show = false
    deleteModal.value.action = null
}

onMounted(() => {
    showToast('success', 'Welcome!', 'Notifications center loaded')
})
</script>
