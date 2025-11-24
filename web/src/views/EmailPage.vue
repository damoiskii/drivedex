<template>
    <div class="p-8">
        <!-- Toast Component -->
        <Toast :show="toast.show" :type="toast.type" :message="toast.message" :description="toast.description"
            @close="toast.show = false" />

        <!-- Header -->
        <div class="mb-6">
            <h1 class="text-3xl font-bold text-gray-800">Email Center</h1>
            <p class="text-gray-600 mt-2">Send and manage email communications</p>
        </div>

        <!-- Action Bar -->
        <div class="bg-white rounded-lg shadow-sm p-4 mb-6">
            <div class="flex flex-col md:flex-row gap-4 items-center justify-between">
                <div class="flex gap-3">
                    <button @click="activeTab = 'compose'" :class="[
                        'px-4 py-2 rounded-lg transition-colors',
                        activeTab === 'compose' ? 'bg-indigo-600 text-white' : 'bg-white text-gray-700 border border-gray-300 hover:bg-gray-50'
                    ]">
                        Compose Email
                    </button>
                    <button @click="activeTab = 'history'" :class="[
                        'px-4 py-2 rounded-lg transition-colors',
                        activeTab === 'history' ? 'bg-indigo-600 text-white' : 'bg-white text-gray-700 border border-gray-300 hover:bg-gray-50'
                    ]">
                        Email History
                    </button>
                    <button @click="activeTab = 'templates'" :class="[
                        'px-4 py-2 rounded-lg transition-colors',
                        activeTab === 'templates' ? 'bg-indigo-600 text-white' : 'bg-white text-gray-700 border border-gray-300 hover:bg-gray-50'
                    ]">
                        Templates
                    </button>
                </div>
            </div>
        </div>

        <!-- Compose Email Tab -->
        <div v-if="activeTab === 'compose'" class="bg-white rounded-lg shadow-sm p-6">
            <h2 class="text-xl font-semibold text-gray-800 mb-6">Compose New Email</h2>
            
            <div class="space-y-4">
                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">Recipient Type</label>
                    <select v-model="emailForm.recipientType"
                        class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent">
                        <option value="all">All Users</option>
                        <option value="drivers">All Drivers</option>
                        <option value="passengers">All Passengers</option>
                        <option value="employees">All Employees</option>
                        <option value="custom">Custom Recipients</option>
                    </select>
                </div>

                <div v-if="emailForm.recipientType === 'custom'">
                    <label class="block text-sm font-medium text-gray-700 mb-2">Email Addresses</label>
                    <input v-model="emailForm.customEmails" type="text"
                        placeholder="Enter email addresses separated by commas"
                        class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent" />
                </div>

                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">Subject</label>
                    <input v-model="emailForm.subject" type="text" placeholder="Email subject"
                        class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent" />
                </div>

                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">Message</label>
                    <textarea v-model="emailForm.message" rows="10" placeholder="Type your message here..."
                        class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent"></textarea>
                </div>

                <div class="flex items-center gap-4">
                    <label class="flex items-center gap-2 cursor-pointer">
                        <input v-model="emailForm.sendCopy" type="checkbox"
                            class="w-4 h-4 text-indigo-600 border-gray-300 rounded focus:ring-indigo-500" />
                        <span class="text-sm text-gray-700">Send me a copy</span>
                    </label>
                </div>

                <div class="flex gap-3 pt-4">
                    <button @click="sendEmail"
                        class="px-6 py-2 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700 transition-colors flex items-center gap-2">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                        </svg>
                        Send Email
                    </button>
                    <button @click="clearForm"
                        class="px-6 py-2 bg-white border border-gray-300 text-gray-700 rounded-lg hover:bg-gray-50 transition-colors">
                        Clear
                    </button>
                </div>
            </div>
        </div>

        <!-- Email History Tab -->
        <div v-else-if="activeTab === 'history'" class="bg-white rounded-lg shadow-sm overflow-hidden">
            <div class="p-6">
                <h2 class="text-xl font-semibold text-gray-800 mb-4">Email History</h2>
            </div>
            <div class="overflow-x-auto">
                <table class="w-full">
                    <thead class="bg-gray-50 border-b border-gray-200">
                        <tr>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                Date</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                Recipients</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                Subject</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                Status</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                Actions</th>
                        </tr>
                    </thead>
                    <tbody class="bg-white divide-y divide-gray-200">
                        <tr v-for="email in emailHistory" :key="email.id" class="hover:bg-gray-50 transition-colors">
                            <td class="px-6 py-4 whitespace-nowrap">
                                <div class="text-sm text-gray-900">{{ email.date }}</div>
                                <div class="text-xs text-gray-500">{{ email.time }}</div>
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap">
                                <div class="text-sm text-gray-900">{{ email.recipients }}</div>
                            </td>
                            <td class="px-6 py-4">
                                <div class="text-sm text-gray-900">{{ email.subject }}</div>
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap">
                                <span :class="[
                                    'px-2 py-1 inline-flex text-xs leading-5 font-semibold rounded-full',
                                    email.status === 'sent' ? 'bg-green-100 text-green-800' :
                                        email.status === 'failed' ? 'bg-red-100 text-red-800' :
                                            'bg-yellow-100 text-yellow-800'
                                ]">
                                    {{ email.status }}
                                </span>
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                                <button @click="viewEmail(email)" class="text-indigo-600 hover:text-indigo-900">
                                    View
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Templates Tab -->
        <div v-else class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            <div v-for="template in emailTemplates" :key="template.id"
                class="bg-white rounded-lg shadow-sm p-6 hover:shadow-md transition-shadow">
                <h3 class="text-lg font-semibold text-gray-900 mb-2">{{ template.name }}</h3>
                <p class="text-sm text-gray-600 mb-4">{{ template.description }}</p>
                <div class="flex gap-2">
                    <button @click="useTemplate(template)"
                        class="flex-1 px-3 py-2 bg-indigo-600 text-white text-sm rounded-lg hover:bg-indigo-700 transition-colors">
                        Use Template
                    </button>
                    <button @click="editTemplate(template)"
                        class="px-3 py-2 bg-white border border-gray-300 text-gray-700 text-sm rounded-lg hover:bg-gray-50 transition-colors">
                        Edit
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import Toast from '../components/Toast.vue'

const activeTab = ref('compose')
const toast = ref({
    show: false,
    type: 'info',
    message: '',
    description: ''
})

const emailForm = ref({
    recipientType: 'all',
    customEmails: '',
    subject: '',
    message: '',
    sendCopy: false
})

const emailHistory = ref([
    {
        id: 1,
        date: '2024-11-23',
        time: '10:30 AM',
        recipients: 'All Drivers',
        subject: 'Weekly Schedule Update',
        status: 'sent'
    },
    {
        id: 2,
        date: '2024-11-22',
        time: '02:15 PM',
        recipients: 'All Passengers',
        subject: 'New Route Launch Announcement',
        status: 'sent'
    },
    {
        id: 3,
        date: '2024-11-21',
        time: '09:00 AM',
        recipients: 'All Employees',
        subject: 'System Maintenance Notice',
        status: 'sent'
    }
])

const emailTemplates = ref([
    {
        id: 1,
        name: 'Welcome Email',
        description: 'Welcome new users to the platform',
        subject: 'Welcome to DriveDex!',
        message: 'Dear User,\n\nWelcome to DriveDex...'
    },
    {
        id: 2,
        name: 'Trip Confirmation',
        description: 'Confirm trip bookings',
        subject: 'Trip Confirmation',
        message: 'Your trip has been confirmed...'
    },
    {
        id: 3,
        name: 'Schedule Update',
        description: 'Notify about schedule changes',
        subject: 'Schedule Update',
        message: 'We have updated the schedule...'
    },
    {
        id: 4,
        name: 'Maintenance Notice',
        description: 'Inform about system maintenance',
        subject: 'Scheduled Maintenance',
        message: 'We will be performing maintenance...'
    }
])

const showToast = (type, message, description = '') => {
    toast.value = { show: true, type, message, description }
}

const sendEmail = () => {
    if (!emailForm.value.subject || !emailForm.value.message) {
        showToast('error', 'Missing Information', 'Please fill in subject and message')
        return
    }
    
    showToast('success', 'Email Sent', `Email sent to ${emailForm.value.recipientType}`)
    clearForm()
}

const clearForm = () => {
    emailForm.value = {
        recipientType: 'all',
        customEmails: '',
        subject: '',
        message: '',
        sendCopy: false
    }
}

const viewEmail = (email) => {
    showToast('info', 'View Email', `Viewing email: ${email.subject}`)
}

const useTemplate = (template) => {
    emailForm.value.subject = template.subject
    emailForm.value.message = template.message
    activeTab.value = 'compose'
    showToast('success', 'Template Applied', `Using template: ${template.name}`)
}

const editTemplate = (template) => {
    showToast('info', 'Edit Template', `Editing template: ${template.name}`)
}

onMounted(() => {
    showToast('success', 'Welcome!', 'Email center loaded')
})
</script>
