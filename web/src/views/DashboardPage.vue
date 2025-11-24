<template>
    <div class="p-8">
        <!-- Stats Grid -->
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
            <!-- Users Card -->
            <div class="bg-white rounded-lg shadow-sm p-6 hover:shadow-md transition-shadow">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500 font-medium">Total Users</p>
                        <p class="text-3xl font-bold text-gray-800 mt-2">{{ stats.users }}</p>
                    </div>
                    <div class="p-3 bg-blue-50 rounded-full">
                        <svg class="w-8 h-8 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z" />
                        </svg>
                    </div>
                </div>
            </div>

            <!-- Employees Card -->
            <div class="bg-white rounded-lg shadow-sm p-6 hover:shadow-md transition-shadow">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500 font-medium">Employees</p>
                        <p class="text-3xl font-bold text-gray-800 mt-2">{{ stats.employees }}</p>
                    </div>
                    <div class="p-3 bg-green-50 rounded-full">
                        <svg class="w-8 h-8 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z" />
                        </svg>
                    </div>
                </div>
            </div>

            <!-- Passengers Card -->
            <div class="bg-white rounded-lg shadow-sm p-6 hover:shadow-md transition-shadow">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500 font-medium">Passengers</p>
                        <p class="text-3xl font-bold text-gray-800 mt-2">{{ stats.passengers }}</p>
                    </div>
                    <div class="p-3 bg-purple-50 rounded-full">
                        <svg class="w-8 h-8 text-purple-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z" />
                        </svg>
                    </div>
                </div>
            </div>

            <!-- Drivers Card -->
            <div class="bg-white rounded-lg shadow-sm p-6 hover:shadow-md transition-shadow">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500 font-medium">Drivers</p>
                        <p class="text-3xl font-bold text-gray-800 mt-2">{{ stats.drivers }}</p>
                    </div>
                    <div class="p-3 bg-indigo-50 rounded-full">
                        <svg class="w-8 h-8 text-indigo-600" fill="currentColor" viewBox="0 0 24 24">
                            <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/>
                        </svg>
                    </div>
                </div>
            </div>

            <!-- Active Users Card -->
            <div class="bg-white rounded-lg shadow-sm p-6 hover:shadow-md transition-shadow">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500 font-medium">Active Users</p>
                        <p class="text-3xl font-bold text-gray-800 mt-2">{{ stats.active }}</p>
                    </div>
                    <div class="p-3 bg-green-50 rounded-full">
                        <svg class="w-8 h-8 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                        </svg>
                    </div>
                </div>
            </div>

            <!-- Inactive Users Card -->
            <div class="bg-white rounded-lg shadow-sm p-6 hover:shadow-md transition-shadow">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500 font-medium">Inactive Users</p>
                        <p class="text-3xl font-bold text-gray-800 mt-2">{{ stats.inactive }}</p>
                    </div>
                    <div class="p-3 bg-red-50 rounded-full">
                        <svg class="w-8 h-8 text-red-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z" />
                        </svg>
                    </div>
                </div>
            </div>

            <!-- Buses Card -->
            <div class="bg-white rounded-lg shadow-sm p-6 hover:shadow-md transition-shadow">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500 font-medium">Total Buses</p>
                        <p class="text-3xl font-bold text-gray-800 mt-2">{{ stats.buses }}</p>
                    </div>
                    <div class="p-3 bg-yellow-50 rounded-full">
                        <svg class="w-8 h-8 text-yellow-600" fill="currentColor" viewBox="0 0 24 24">
                            <path d="M18.92 6.01C18.72 5.42 18.16 5 17.5 5h-11c-.66 0-1.21.42-1.42 1.01L3 12v8c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-1h12v1c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-8l-2.08-5.99zM6.5 16c-.83 0-1.5-.67-1.5-1.5S5.67 13 6.5 13s1.5.67 1.5 1.5S7.33 16 6.5 16zm11 0c-.83 0-1.5-.67-1.5-1.5s.67-1.5 1.5-1.5 1.5.67 1.5 1.5-.67 1.5-1.5 1.5zM5 11l1.5-4.5h11L19 11H5z"/>
                        </svg>
                    </div>
                </div>
            </div>

            <!-- Subscribers Card -->
            <div class="bg-white rounded-lg shadow-sm p-6 hover:shadow-md transition-shadow">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-sm text-gray-500 font-medium">Subscribers</p>
                        <p class="text-3xl font-bold text-gray-800 mt-2">{{ stats.subscribers }}</p>
                    </div>
                    <div class="p-3 bg-pink-50 rounded-full">
                        <svg class="w-8 h-8 text-pink-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11.049 2.927c.3-.921 1.603-.921 1.902 0l1.519 4.674a1 1 0 00.95.69h4.915c.969 0 1.371 1.24.588 1.81l-3.976 2.888a1 1 0 00-.363 1.118l1.518 4.674c.3.922-.755 1.688-1.538 1.118l-3.976-2.888a1 1 0 00-1.176 0l-3.976 2.888c-.783.57-1.838-.197-1.538-1.118l1.518-4.674a1 1 0 00-.363-1.118l-3.976-2.888c-.784-.57-.38-1.81.588-1.81h4.914a1 1 0 00.951-.69l1.519-4.674z" />
                        </svg>
                    </div>
                </div>
            </div>
        </div>

        <!-- Vehicle Status Section -->
        <div class="bg-white rounded-lg shadow-sm p-6 mb-8">
            <h3 class="text-lg font-semibold text-gray-800 mb-4">Vehicle Status</h3>
            <div class="overflow-x-auto">
                <table class="w-full">
                    <thead>
                        <tr class="border-b border-gray-200">
                            <th class="text-left py-3 px-4 text-sm font-medium text-gray-600">Vehicle</th>
                            <th class="text-left py-3 px-4 text-sm font-medium text-gray-600">Driver</th>
                            <th class="text-left py-3 px-4 text-sm font-medium text-gray-600">Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="vehicle in vehicleStatus" :key="vehicle.id" class="border-b border-gray-100 hover:bg-gray-50">
                            <td class="py-3 px-4 text-sm text-gray-800">{{ vehicle.name }}</td>
                            <td class="py-3 px-4 text-sm text-gray-600">{{ vehicle.driver }}</td>
                            <td class="py-3 px-4">
                                <span :class="[
                                    'text-xs px-3 py-1 rounded-full font-medium',
                                    vehicle.status === 'active' ? 'bg-green-100 text-green-700' :
                                    vehicle.status === 'idle' ? 'bg-blue-100 text-blue-700' :
                                    vehicle.status === 'maintenance' ? 'bg-orange-100 text-orange-700' : 'bg-gray-100 text-gray-700'
                                ]">{{ vehicle.status }}</span>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Three Column Layout -->
        <div class="grid grid-cols-1 lg:grid-cols-3 gap-6">
            <!-- Left Column: Alerts & Weather -->
            <div class="space-y-6">
                <!-- Critical Alerts -->
                <div class="bg-white rounded-lg shadow-sm p-6">
                    <h3 class="text-lg font-semibold text-gray-800 mb-4">Critical Alerts</h3>
                    <div class="space-y-3">
                        <div v-for="alert in criticalAlerts" :key="alert.id" :class="[
                            'p-3 rounded-lg border-l-4',
                            alert.severity === 'high' ? 'bg-red-50 border-red-500' :
                            alert.severity === 'medium' ? 'bg-yellow-50 border-yellow-500' : 'bg-blue-50 border-blue-500'
                        ]">
                            <div class="flex items-start gap-2">
                                <svg class="w-5 h-5 mt-0.5" :class="[
                                    alert.severity === 'high' ? 'text-red-500' :
                                    alert.severity === 'medium' ? 'text-yellow-500' : 'text-blue-500'
                                ]" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z" />
                                </svg>
                                <div class="flex-1">
                                    <p class="text-sm font-medium text-gray-800">{{ alert.message }}</p>
                                    <p class="text-xs text-gray-500 mt-1">{{ alert.time }}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Weather Widget -->
                <div class="bg-gradient-to-br from-blue-500 to-blue-600 rounded-lg shadow-sm p-6 text-white">
                    <h3 class="text-lg font-semibold mb-4">Weather Conditions</h3>
                    <div class="flex items-center justify-between">
                        <div>
                            <p class="text-4xl font-bold">{{ weather.temp }}°C</p>
                            <p class="text-sm opacity-90 mt-1">{{ weather.condition }}</p>
                            <p class="text-xs opacity-75 mt-2">{{ weather.location }}</p>
                        </div>
                        <svg class="w-16 h-16 opacity-90" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 15a4 4 0 004 4h9a5 5 0 10-.1-9.999 5.002 5.002 0 10-9.78 2.096A4.001 4.001 0 003 15z" />
                        </svg>
                    </div>
                </div>
            </div>

            <!-- Middle Column: Recent Trips & Activity -->
            <div class="space-y-6">
                <!-- Recent Trips -->
                <div class="bg-white rounded-lg shadow-sm p-6">
                    <div class="flex items-center justify-between mb-4">
                        <h3 class="text-lg font-semibold text-gray-800">Recent Trips</h3>
                        <button class="text-sm text-indigo-600 hover:text-indigo-700">View All</button>
                    </div>
                    <div class="space-y-3">
                        <div v-for="trip in recentTrips" :key="trip.id" class="p-3 border border-gray-200 rounded-lg hover:border-indigo-300 transition-colors">
                            <div class="flex items-center justify-between mb-2">
                                <span class="text-sm font-medium text-gray-800">{{ trip.route }}</span>
                                <span :class="[
                                    'text-xs px-2 py-1 rounded-full',
                                    trip.status === 'completed' ? 'bg-green-100 text-green-700' :
                                    trip.status === 'ongoing' ? 'bg-blue-100 text-blue-700' :
                                    trip.status === 'delayed' ? 'bg-red-100 text-red-700' : 'bg-gray-100 text-gray-700'
                                ]">{{ trip.status }}</span>
                            </div>
                            <div class="flex items-center justify-between text-xs text-gray-500">
                                <span>{{ trip.driver }}</span>
                                <span>{{ trip.time }}</span>
                            </div>
                            <div class="mt-2 flex items-center gap-2">
                                <div class="flex-1 bg-gray-200 rounded-full h-1.5">
                                    <div :class="[
                                        'h-1.5 rounded-full',
                                        trip.status === 'completed' ? 'bg-green-500' :
                                        trip.status === 'ongoing' ? 'bg-blue-500' : 'bg-red-500'
                                    ]" :style="`width: ${trip.progress}%`"></div>
                                </div>
                                <span class="text-xs text-gray-500">{{ trip.progress }}%</span>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Activity Feed -->
                <div class="bg-white rounded-lg shadow-sm p-6">
                    <h3 class="text-lg font-semibold text-gray-800 mb-4">Recent Activity</h3>
                    <div class="space-y-4">
                        <div v-for="activity in activityFeed" :key="activity.id" class="flex gap-3">
                            <div :class="[
                                'w-8 h-8 rounded-full flex items-center justify-center flex-shrink-0',
                                activity.type === 'trip' ? 'bg-blue-100' :
                                activity.type === 'maintenance' ? 'bg-orange-100' :
                                activity.type === 'booking' ? 'bg-green-100' : 'bg-purple-100'
                            ]">
                                <svg class="w-4 h-4" :class="[
                                    activity.type === 'trip' ? 'text-blue-600' :
                                    activity.type === 'maintenance' ? 'text-orange-600' :
                                    activity.type === 'booking' ? 'text-green-600' : 'text-purple-600'
                                ]" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                                </svg>
                            </div>
                            <div class="flex-1">
                                <p class="text-sm text-gray-800">{{ activity.message }}</p>
                                <p class="text-xs text-gray-500 mt-1">{{ activity.time }}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Right Column: Charts & Performance -->
            <div class="space-y-6">
                <!-- Performance Chart -->
                <div class="bg-white rounded-lg shadow-sm p-6">
                    <h3 class="text-lg font-semibold text-gray-800 mb-4">Trip Statistics (This Week)</h3>
                    <div class="h-48 flex items-end justify-between gap-2">
                        <div v-for="(day, index) in tripStats" :key="index" class="flex-1 flex flex-col items-center gap-2">
                            <div class="w-full bg-indigo-100 rounded-t-lg relative" :style="`height: ${(day.value / 50) * 100}%`">
                                <div class="absolute inset-0 bg-gradient-to-t from-indigo-500 to-indigo-400 rounded-t-lg"></div>
                            </div>
                            <span class="text-xs text-gray-600">{{ day.day }}</span>
                        </div>
                    </div>
                    <div class="mt-4 text-center">
                        <p class="text-2xl font-bold text-gray-800">{{ tripStats.reduce((sum, day) => sum + day.value, 0) }}</p>
                        <p class="text-xs text-gray-500">Total Trips This Week</p>
                    </div>
                </div>

                <!-- KPI Cards -->
                <div class="grid grid-cols-2 gap-4">
                    <div class="bg-white rounded-lg shadow-sm p-4">
                        <div class="flex items-center gap-2 mb-2">
                            <svg class="w-5 h-5 text-indigo-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                            </svg>
                            <p class="text-xs text-gray-500">Avg Trip Time</p>
                        </div>
                        <p class="text-2xl font-bold text-gray-800">42 min</p>
                    </div>
                    <div class="bg-white rounded-lg shadow-sm p-4">
                        <div class="flex items-center gap-2 mb-2">
                            <svg class="w-5 h-5 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4M7.835 4.697a3.42 3.42 0 001.946-.806 3.42 3.42 0 014.438 0 3.42 3.42 0 001.946.806 3.42 3.42 0 013.138 3.138 3.42 3.42 0 00.806 1.946 3.42 3.42 0 010 4.438 3.42 3.42 0 00-.806 1.946 3.42 3.42 0 01-3.138 3.138 3.42 3.42 0 00-1.946.806 3.42 3.42 0 01-4.438 0 3.42 3.42 0 00-1.946-.806 3.42 3.42 0 01-3.138-3.138 3.42 3.42 0 00-.806-1.946 3.42 3.42 0 010-4.438 3.42 3.42 0 00.806-1.946 3.42 3.42 0 013.138-3.138z" />
                            </svg>
                            <p class="text-xs text-gray-500">On-Time Rate</p>
                        </div>
                        <p class="text-2xl font-bold text-gray-800">94%</p>
                    </div>
                    <div class="bg-white rounded-lg shadow-sm p-4">
                        <div class="flex items-center gap-2 mb-2">
                            <svg class="w-5 h-5 text-yellow-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6" />
                            </svg>
                            <p class="text-xs text-gray-500">Fuel Efficiency</p>
                        </div>
                        <p class="text-2xl font-bold text-gray-800">12.5 km/l</p>
                    </div>
                    <div class="bg-white rounded-lg shadow-sm p-4">
                        <div class="flex items-center gap-2 mb-2">
                            <svg class="w-5 h-5 text-purple-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11.049 2.927c.3-.921 1.603-.921 1.902 0l1.519 4.674a1 1 0 00.95.69h4.915c.969 0 1.371 1.24.588 1.81l-3.976 2.888a1 1 0 00-.363 1.118l1.518 4.674c.3.922-.755 1.688-1.538 1.118l-3.976-2.888a1 1 0 00-1.176 0l-3.976 2.888c-.783.57-1.838-.197-1.538-1.118l1.518-4.674a1 1 0 00-.363-1.118l-3.976-2.888c-.784-.57-.38-1.81.588-1.81h4.914a1 1 0 00.951-.69l1.519-4.674z" />
                            </svg>
                            <p class="text-xs text-gray-500">Satisfaction</p>
                        </div>
                        <p class="text-2xl font-bold text-gray-800">4.8/5</p>
                    </div>
                </div>

                <!-- Maintenance Schedule -->
                <div class="bg-white rounded-lg shadow-sm p-6">
                    <h3 class="text-lg font-semibold text-gray-800 mb-4">Upcoming Maintenance</h3>
                    <div class="space-y-3">
                        <div v-for="maintenance in maintenanceSchedule" :key="maintenance.id" class="flex items-center justify-between p-3 bg-orange-50 rounded-lg">
                            <div>
                                <p class="text-sm font-medium text-gray-800">{{ maintenance.vehicle }}</p>
                                <p class="text-xs text-gray-500">{{ maintenance.type }}</p>
                            </div>
                            <span class="text-xs text-orange-600 font-medium">{{ maintenance.date }}</span>
                        </div>
                    </div>
                </div>

                <!-- Quick Actions -->
                <div class="bg-white rounded-lg shadow-sm p-6">
                    <h3 class="text-lg font-semibold text-gray-800 mb-4">Quick Actions</h3>
                    <div class="grid grid-cols-2 gap-3">
                        <button class="p-3 bg-indigo-50 text-indigo-600 rounded-lg hover:bg-indigo-100 transition-colors text-sm font-medium">
                            Assign Driver
                        </button>
                        <button class="p-3 bg-green-50 text-green-600 rounded-lg hover:bg-green-100 transition-colors text-sm font-medium">
                            Add Route
                        </button>
                        <button class="p-3 bg-purple-50 text-purple-600 rounded-lg hover:bg-purple-100 transition-colors text-sm font-medium">
                            Send Alert
                        </button>
                        <button class="p-3 bg-blue-50 text-blue-600 rounded-lg hover:bg-blue-100 transition-colors text-sm font-medium">
                            Generate Report
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'

const stats = ref({
    users: 105,
    employees: 9,
    passengers: 96,
    drivers: 8,
    active: 1,
    inactive: 104,
    buses: 8,
    subscribers: 7
})

const vehicleStatus = ref([
    { id: 1, name: 'Bus A-101', driver: 'John Doe', status: 'active' },
    { id: 2, name: 'Bus B-202', driver: 'Jane Smith', status: 'idle' },
    { id: 3, name: 'Bus C-303', driver: 'Mike Johnson', status: 'active' },
    { id: 4, name: 'Bus D-404', driver: 'Sarah Williams', status: 'maintenance' },
    { id: 5, name: 'Bus E-505', driver: 'Not Assigned', status: 'offline' }
])

const criticalAlerts = ref([
    { id: 1, message: 'Bus A-101 speeding detected on Route 5', time: '5 mins ago', severity: 'high' },
    { id: 2, message: 'Bus C-303 deviated from assigned route', time: '12 mins ago', severity: 'medium' },
    { id: 3, message: 'Bus D-404 maintenance due in 2 days', time: '1 hour ago', severity: 'low' }
])

const weather = ref({
    temp: 24,
    condition: 'Partly Cloudy',
    location: 'City Center'
})

const recentTrips = ref([
    { id: 1, route: 'Downtown → Airport', driver: 'John Doe', time: '08:30 AM', status: 'completed', progress: 100 },
    { id: 2, route: 'Suburb A → City Center', driver: 'Jane Smith', time: '09:15 AM', status: 'ongoing', progress: 65 },
    { id: 3, route: 'Route 5 Express', driver: 'Mike Johnson', time: '10:00 AM', status: 'ongoing', progress: 45 },
    { id: 4, route: 'North Loop', driver: 'Sarah Williams', time: '10:30 AM', status: 'delayed', progress: 30 }
])

const activityFeed = ref([
    { id: 1, message: 'Driver John Doe checked in for shift', time: '2 mins ago', type: 'trip' },
    { id: 2, message: 'Passenger booked trip on Route 3', time: '15 mins ago', type: 'booking' },
    { id: 3, message: 'Bus D-404 maintenance completed', time: '1 hour ago', type: 'maintenance' },
    { id: 4, message: 'New route added: Express Line 7', time: '2 hours ago', type: 'system' },
    { id: 5, message: 'Monthly report generated', time: '3 hours ago', type: 'system' }
])

const tripStats = ref([
    { day: 'Mon', value: 42 },
    { day: 'Tue', value: 38 },
    { day: 'Wed', value: 45 },
    { day: 'Thu', value: 50 },
    { day: 'Fri', value: 48 },
    { day: 'Sat', value: 35 },
    { day: 'Sun', value: 30 }
])

const maintenanceSchedule = ref([
    { id: 1, vehicle: 'Bus A-101', type: 'Oil Change', date: 'Nov 20' },
    { id: 2, vehicle: 'Bus B-202', type: 'Tire Rotation', date: 'Nov 22' },
    { id: 3, vehicle: 'Bus E-505', type: 'Full Service', date: 'Nov 25' }
])
</script>
