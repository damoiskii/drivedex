<template>
    <div class="flex h-screen bg-gray-50">
        <!-- Sidebar -->
        <aside class="w-64 bg-white shadow-md relative">
            <div class="p-6">
                <h1 class="text-2xl font-bold text-indigo-600 flex items-center gap-2">
                    <svg class="w-8 h-8" fill="currentColor" viewBox="0 0 24 24">
                        <path
                            d="M18.92 6.01C18.72 5.42 18.16 5 17.5 5h-11c-.66 0-1.21.42-1.42 1.01L3 12v8c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-1h12v1c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-8l-2.08-5.99zM6.5 16c-.83 0-1.5-.67-1.5-1.5S5.67 13 6.5 13s1.5.67 1.5 1.5S7.33 16 6.5 16zm11 0c-.83 0-1.5-.67-1.5-1.5s.67-1.5 1.5-1.5 1.5.67 1.5 1.5-.67 1.5-1.5 1.5zM5 11l1.5-4.5h11L19 11H5z" />
                    </svg>
                    drivedex
                </h1>
            </div>

            <nav class="mt-6 px-3">
                <!-- Dashboard -->
                <router-link to="/dashboard" :class="[
                    'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 relative',
                    $route.path === '/dashboard' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                ]">
                    <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6" />
                    </svg>
                    <span>Dashboard</span>
                    <span v-if="$route.path === '/dashboard'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                </router-link>

                <!-- Users Section -->
                <div class="mt-6">
                    <p class="px-3 text-xs font-semibold text-gray-400 uppercase tracking-wider">Users</p>

                    <router-link to="/employees" :class="[
                        'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 mt-2 relative',
                        $route.path === '/employees' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                    ]">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z" />
                        </svg>
                        <span>Employees</span>
                        <span v-if="$route.path === '/employees'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                    </router-link>

                    <router-link to="/drivers" :class="[
                        'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 relative',
                        $route.path === '/drivers' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                    ]">
                        <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 24 24">
                            <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/>
                        </svg>
                        <span>Drivers</span>
                        <span v-if="$route.path === '/drivers'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                    </router-link>

                    <router-link to="/passengers" :class="[
                        'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 relative',
                        $route.path === '/passengers' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                    ]">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z" />
                        </svg>
                        <span>Passengers</span>
                        <span v-if="$route.path === '/passengers'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                    </router-link>

                    <router-link to="/roles" :class="[
                        'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 relative',
                        $route.path === '/roles' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                    ]">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z" />
                        </svg>
                        <span>Roles & Permissions</span>
                        <span v-if="$route.path === '/roles'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                    </router-link>
                </div>

                <!-- Buses Section -->
                <div class="mt-6">
                    <p class="px-3 text-xs font-semibold text-gray-400 uppercase tracking-wider">Buses</p>

                    <router-link to="/buses" :class="[
                        'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 mt-2 relative',
                        $route.path === '/buses' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                    ]">
                        <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 24 24">
                            <path d="M18.92 6.01C18.72 5.42 18.16 5 17.5 5h-11c-.66 0-1.21.42-1.42 1.01L3 12v8c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-1h12v1c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-8l-2.08-5.99zM6.5 16c-.83 0-1.5-.67-1.5-1.5S5.67 13 6.5 13s1.5.67 1.5 1.5S7.33 16 6.5 16zm11 0c-.83 0-1.5-.67-1.5-1.5s.67-1.5 1.5-1.5 1.5.67 1.5 1.5-.67 1.5-1.5 1.5zM5 11l1.5-4.5h11L19 11H5z"/>
                        </svg>
                        <span>All Buses</span>
                        <span v-if="$route.path === '/buses'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                    </router-link>
                </div>

                <!-- Locations Section -->
                <div class="mt-6">
                    <p class="px-3 text-xs font-semibold text-gray-400 uppercase tracking-wider">Locations</p>

                    <router-link to="/locations" :class="[
                        'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 mt-2 relative',
                        $route.path === '/locations' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                    ]">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                        </svg>
                        <span>Locations</span>
                        <span v-if="$route.path === '/locations'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                    </router-link>
                </div>

                <!-- Routes Section -->
                <div class="mt-6">
                    <p class="px-3 text-xs font-semibold text-gray-400 uppercase tracking-wider">Routes</p>

                    <router-link to="/routes" :class="[
                        'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 mt-2 relative',
                        $route.path === '/routes' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                    ]">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M9 20l-5.447-2.724A1 1 0 013 16.382V5.618a1 1 0 011.447-.894L9 7m0 13l6-3m-6 3V7m6 10l4.553 2.276A1 1 0 0021 18.382V7.618a1 1 0 00-.553-.894L15 4m0 13V4m0 0L9 7" />
                        </svg>
                        <span>Routes</span>
                        <span v-if="$route.path === '/routes'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                    </router-link>
                </div>

                <!-- Others Section -->
                <div class="mt-6">
                    <p class="px-3 text-xs font-semibold text-gray-400 uppercase tracking-wider">Others</p>

                    <router-link to="/email" :class="[
                        'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 mt-2 relative',
                        $route.path === '/email' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                    ]">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                        </svg>
                        <span>Email</span>
                        <span v-if="$route.path === '/email'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                    </router-link>

                    <router-link to="/settings" :class="[
                        'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 relative',
                        $route.path === '/settings' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                    ]">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10.325 4.317c.426-1.756 2.924-1.756 3.35 0a1.724 1.724 0 002.573 1.066c1.543-.94 3.31.826 2.37 2.37a1.724 1.724 0 001.065 2.572c1.756.426 1.756 2.924 0 3.35a1.724 1.724 0 00-1.066 2.573c.94 1.543-.826 3.31-2.37 2.37a1.724 1.724 0 00-2.572 1.065c-.426 1.756-2.924 1.756-3.35 0a1.724 1.724 0 00-2.573-1.066c-1.543.94-3.31-.826-2.37-2.37a1.724 1.724 0 00-1.065-2.572c-1.756-.426-1.756-2.924 0-3.35a1.724 1.724 0 001.066-2.573c-.94-1.543.826-3.31 2.37-2.37.996.608 2.296.07 2.572-1.065z" />
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
                        </svg>
                        <span>Settings</span>
                        <span v-if="$route.path === '/settings'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                    </router-link>

                    <router-link to="/notifications" :class="[
                        'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 relative',
                        $route.path === '/notifications' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                    ]">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9" />
                        </svg>
                        <span>Notifications</span>
                        <span v-if="$route.path === '/notifications'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                    </router-link>
                </div>
            </nav>
        </aside>

        <!-- Main Content -->
        <main class="flex-1 overflow-y-auto flex flex-col">
            <!-- Header -->
            <header class="bg-white shadow-sm">
                <div class="px-8 py-4 flex items-center justify-between">
                    <div>
                        <h2 class="text-2xl font-semibold text-gray-800">
                            {{ pageTitle }}
                        </h2>
                        <p v-if="$route.path === '/dashboard'" class="text-sm text-gray-500 mt-1">
                            Welcome to your Dashboard! 🎉 Explore all your Properties, where insights meet simplicity.
                        </p>
                        <p v-if="$route.path === '/dashboard'" class="text-xs text-gray-400 mt-1">
                            Last logged in on May 12, 2024 at 1:28 AM
                        </p>
                    </div>
                    <div class="flex items-center gap-4">
                        <button class="p-2 text-gray-400 hover:text-gray-600 transition-colors">
                            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                    d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9" />
                            </svg>
                        </button>
                        <div class="flex items-center gap-3">
                            <span v-if="$route.path === '/dashboard'" class="text-sm text-gray-700">Hi, Admin! 👋</span>
                            <div class="w-10 h-10 bg-indigo-100 rounded-full flex items-center justify-center">
                                <svg class="w-6 h-6 text-indigo-600" fill="none" stroke="currentColor"
                                    viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                        d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                                </svg>
                            </div>
                        </div>
                    </div>
                </div>
            </header>

            <!-- Page Content -->
            <div class="flex-1">
                <router-view />
            </div>

            <!-- Footer -->
            <footer class="bg-white border-t border-gray-200 py-4 mt-auto">
                <div class="text-center">
                    <p class="text-sm text-gray-500">
                        DriveDex © <span>{{ new Date().getFullYear() }}</span>. Made with ❤️ by 
                        <a href="https://devdam.me" class="text-indigo-600 font-semibold hover:text-indigo-400 hover:font-semibold">Devdam</a>
                    </p>
                    <p class="text-xs text-gray-400 mt-1">DriveDex Navigating Success, Tracking Excellence</p>
                </div>
            </footer>
        </main>
    </div>
</template>

<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()

const pageTitle = computed(() => {
    const titles = {
        '/dashboard': 'Dashboard',
        '/employees': 'Employees',
        '/drivers': 'Drivers',
        '/passengers': 'Passengers',
        '/roles': 'Roles & Permissions',
        '/buses': 'All Buses',
        '/locations': 'Locations',
        '/routes': 'Routes',
        '/email': 'Email Center',
        '/settings': 'Settings',
        '/notifications': 'Notifications'
    }
    return titles[route.path] || 'Dashboard'
})
</script>
