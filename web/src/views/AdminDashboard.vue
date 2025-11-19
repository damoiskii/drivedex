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
                <a href="#" :class="[
                    'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 relative',
                    activeSection === 'dashboard' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                ]" @click.prevent="activeSection = 'dashboard'">
                    <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6" />
                    </svg>
                    <span>Dashboard</span>
                    <span v-if="activeSection === 'dashboard'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                </a>

                <!-- Users Section -->
                <div class="mt-6">
                    <p class="px-3 text-xs font-semibold text-gray-400 uppercase tracking-wider">Users</p>

                    <a href="#" :class="[
                        'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 mt-2 relative',
                        activeSection === 'employees' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                    ]" @click.prevent="activeSection = 'employees'">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z" />
                        </svg>
                        <span>Employees</span>
                        <span v-if="activeSection === 'employees'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                    </a>

                    <a href="#" :class="[
                        'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 relative',
                        activeSection === 'drivers' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                    ]" @click.prevent="activeSection = 'drivers'">
                        <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 24 24">
                            <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/>
                        </svg>
                        <span>Drivers</span>
                        <span v-if="activeSection === 'drivers'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                    </a>

                    <a href="#" :class="[
                        'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 relative',
                        activeSection === 'passengers' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                    ]" @click.prevent="activeSection = 'passengers'">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z" />
                        </svg>
                        <span>Passengers</span>
                        <span v-if="activeSection === 'passengers'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                    </a>

                    <a href="#" :class="[
                        'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 relative',
                        activeSection === 'roles' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                    ]" @click.prevent="activeSection = 'roles'">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z" />
                        </svg>
                        <span>Roles & Permissions</span>
                        <span v-if="activeSection === 'roles'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                    </a>
                </div>

                <!-- Buses Section -->
                <div class="mt-6">
                    <p class="px-3 text-xs font-semibold text-gray-400 uppercase tracking-wider">Buses</p>

                    <a href="#" :class="[
                        'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 mt-2 relative',
                        activeSection === 'buses' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                    ]" @click.prevent="activeSection = 'buses'">
                        <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 24 24">
                            <path d="M18.92 6.01C18.72 5.42 18.16 5 17.5 5h-11c-.66 0-1.21.42-1.42 1.01L3 12v8c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-1h12v1c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-8l-2.08-5.99zM6.5 16c-.83 0-1.5-.67-1.5-1.5S5.67 13 6.5 13s1.5.67 1.5 1.5S7.33 16 6.5 16zm11 0c-.83 0-1.5-.67-1.5-1.5s.67-1.5 1.5-1.5 1.5.67 1.5 1.5-.67 1.5-1.5 1.5zM5 11l1.5-4.5h11L19 11H5z"/>
                        </svg>
                        <span>All Buses</span>
                        <span v-if="activeSection === 'buses'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                    </a>
                </div>

                <!-- Locations Section -->
                <div class="mt-6">
                    <p class="px-3 text-xs font-semibold text-gray-400 uppercase tracking-wider">Locations</p>

                    <a href="#" :class="[
                        'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 mt-2 relative',
                        activeSection === 'locations' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                    ]" @click.prevent="activeSection = 'locations'">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                        </svg>
                        <span>Locations</span>
                        <span v-if="activeSection === 'locations'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                    </a>
                </div>

                <!-- Routes Section -->
                <div class="mt-6">
                    <p class="px-3 text-xs font-semibold text-gray-400 uppercase tracking-wider">Routes</p>

                    <a href="#" :class="[
                        'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 mt-2 relative',
                        activeSection === 'routes' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                    ]" @click.prevent="activeSection = 'routes'">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M9 20l-5.447-2.724A1 1 0 013 16.382V5.618a1 1 0 011.447-.894L9 7m0 13l6-3m-6 3V7m6 10l4.553 2.276A1 1 0 0021 18.382V7.618a1 1 0 00-.553-.894L15 4m0 13V4m0 0L9 7" />
                        </svg>
                        <span>Routes</span>
                        <span v-if="activeSection === 'routes'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                    </a>
                </div>

                <!-- Others Section -->
                <div class="mt-6">
                    <p class="px-3 text-xs font-semibold text-gray-400 uppercase tracking-wider">Others</p>

                    <a href="#" :class="[
                        'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 mt-2 relative',
                        activeSection === 'email' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                    ]" @click.prevent="activeSection = 'email'">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                        </svg>
                        <span>Email</span>
                        <span v-if="activeSection === 'email'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                    </a>

                    <a href="#" :class="[
                        'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 relative',
                        activeSection === 'settings' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                    ]" @click.prevent="activeSection = 'settings'">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10.325 4.317c.426-1.756 2.924-1.756 3.35 0a1.724 1.724 0 002.573 1.066c1.543-.94 3.31.826 2.37 2.37a1.724 1.724 0 001.065 2.572c1.756.426 1.756 2.924 0 3.35a1.724 1.724 0 00-1.066 2.573c.94 1.543-.826 3.31-2.37 2.37a1.724 1.724 0 00-2.572 1.065c-.426 1.756-2.924 1.756-3.35 0a1.724 1.724 0 00-2.573-1.066c-1.543.94-3.31-.826-2.37-2.37a1.724 1.724 0 00-1.065-2.572c-1.756-.426-1.756-2.924 0-3.35a1.724 1.724 0 001.066-2.573c-.94-1.543.826-3.31 2.37-2.37.996.608 2.296.07 2.572-1.065z" />
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
                        </svg>
                        <span>Settings</span>
                        <span v-if="activeSection === 'settings'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                    </a>

                    <a href="#" :class="[
                        'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 relative',
                        activeSection === 'notifications' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                    ]" @click.prevent="activeSection = 'notifications'">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9" />
                        </svg>
                        <span>Notifications</span>
                        <span v-if="activeSection === 'notifications'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                    </a>

                    <a href="#" :class="[
                        'flex items-center gap-3 px-3 py-3 text-gray-700 transition-all rounded-lg mb-1 relative',
                        activeSection === 'logout' ? 'bg-indigo-50 text-indigo-600 font-medium' : 'hover:bg-gray-50'
                    ]" @click.prevent="activeSection = 'logout'">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1" />
                        </svg>
                        <span>Logout</span>
                        <span v-if="activeSection === 'logout'" class="absolute -right-3 top-1/2 -translate-y-1/2 w-1 h-8 bg-indigo-600 rounded-l-full"></span>
                    </a>
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
                            {{ activeSection === 'dashboard' ? 'Dashboard' : getSectionTitle() }}
                        </h2>
                        <p class="text-sm text-gray-500 mt-1">
                            Welcome to your Dashboard! 🎉 Explore all your Properties, where insights meet simplicity.
                        </p>
                        <p class="text-xs text-gray-400 mt-1">
                            Last logged in on May {{ lastLoginDay }}, 2024 at {{ lastLoginTime }}
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
                            <span class="text-sm text-gray-700">Hi, Admin! 👋</span>
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

            <!-- Dashboard Content -->
            <div class="p-8 flex-1">
                <!-- Stats Grid -->
                <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
                    <!-- Users Card -->
                    <div class="bg-white rounded-lg shadow-sm p-6 hover:shadow-md transition-shadow">
                        <div class="flex items-center justify-between">
                            <div>
                                <p class="text-sm text-gray-500 mb-1">Users</p>
                                <p class="text-3xl font-bold text-gray-800">{{ stats.users }}</p>
                                <p class="text-xs text-gray-400 mt-1">Total Active Users</p>
                            </div>
                            <div class="w-12 h-12 bg-indigo-100 rounded-lg flex items-center justify-center">
                                <svg class="w-6 h-6 text-indigo-600" fill="none" stroke="currentColor"
                                    viewBox="0 0 24 24">
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
                                <p class="text-sm text-gray-500 mb-1">Employees</p>
                                <p class="text-3xl font-bold text-gray-800">{{ stats.employees }}</p>
                                <p class="text-xs text-gray-400 mt-1">Total Employees</p>
                            </div>
                            <div class="w-12 h-12 bg-blue-100 rounded-lg flex items-center justify-center">
                                <svg class="w-6 h-6 text-blue-600" fill="none" stroke="currentColor"
                                    viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                        d="M21 13.255A23.931 23.931 0 0112 15c-3.183 0-6.22-.62-9-1.745M16 6V4a2 2 0 00-2-2h-4a2 2 0 00-2 2v2m4 6h.01M5 20h14a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                                </svg>
                            </div>
                        </div>
                    </div>

                    <!-- Passengers Card -->
                    <div class="bg-white rounded-lg shadow-sm p-6 hover:shadow-md transition-shadow">
                        <div class="flex items-center justify-between">
                            <div>
                                <p class="text-sm text-gray-500 mb-1">Passengers</p>
                                <p class="text-3xl font-bold text-gray-800">{{ stats.passengers }}</p>
                                <p class="text-xs text-gray-400 mt-1">Total Passengers</p>
                            </div>
                            <div class="w-12 h-12 bg-purple-100 rounded-lg flex items-center justify-center">
                                <svg class="w-6 h-6 text-purple-600" fill="none" stroke="currentColor"
                                    viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                        d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z" />
                                </svg>
                            </div>
                        </div>
                    </div>

                    <!-- Drivers Card -->
                    <div class="bg-white rounded-lg shadow-sm p-6 hover:shadow-md transition-shadow">
                        <div class="flex items-center justify-between">
                            <div>
                                <p class="text-sm text-gray-500 mb-1">Drivers</p>
                                <p class="text-3xl font-bold text-gray-800">{{ stats.drivers }}</p>
                                <p class="text-xs text-gray-400 mt-1">Total Drivers</p>
                            </div>
                            <div class="w-12 h-12 bg-yellow-100 rounded-lg flex items-center justify-center">
                                <svg class="w-6 h-6 text-yellow-600" fill="currentColor" viewBox="0 0 24 24">
                                    <path
                                        d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z" />
                                </svg>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Second Row Stats -->
                <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
                    <!-- Active Card -->
                    <div class="bg-white rounded-lg shadow-sm p-6 hover:shadow-md transition-shadow">
                        <div class="flex items-center justify-between">
                            <div>
                                <p class="text-sm text-gray-500 mb-1">Active</p>
                                <p class="text-3xl font-bold text-gray-800">{{ stats.active }}</p>
                                <p class="text-xs text-gray-400 mt-1">Total Active Users</p>
                            </div>
                            <div class="w-12 h-12 bg-green-100 rounded-lg flex items-center justify-center">
                                <svg class="w-6 h-6 text-green-600" fill="none" stroke="currentColor"
                                    viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                        d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                                </svg>
                            </div>
                        </div>
                    </div>

                    <!-- Inactive Card -->
                    <div class="bg-white rounded-lg shadow-sm p-6 hover:shadow-md transition-shadow">
                        <div class="flex items-center justify-between">
                            <div>
                                <p class="text-sm text-gray-500 mb-1">Inactive</p>
                                <p class="text-3xl font-bold text-gray-800">{{ stats.inactive }}</p>
                                <p class="text-xs text-gray-400 mt-1">Total Inactive Users</p>
                            </div>
                            <div class="w-12 h-12 bg-red-100 rounded-lg flex items-center justify-center">
                                <svg class="w-6 h-6 text-red-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                        d="M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z" />
                                </svg>
                            </div>
                        </div>
                    </div>

                    <!-- Buses Card -->
                    <div class="bg-white rounded-lg shadow-sm p-6 hover:shadow-md transition-shadow">
                        <div class="flex items-center justify-between">
                            <div>
                                <p class="text-sm text-gray-500 mb-1">Buses</p>
                                <p class="text-3xl font-bold text-gray-800">{{ stats.buses }}</p>
                                <p class="text-xs text-gray-400 mt-1">Total Buses</p>
                            </div>
                            <div class="w-12 h-12 bg-gray-100 rounded-lg flex items-center justify-center">
                                <svg class="w-6 h-6 text-gray-600" fill="currentColor" viewBox="0 0 24 24">
                                    <path
                                        d="M18.92 6.01C18.72 5.42 18.16 5 17.5 5h-11c-.66 0-1.21.42-1.42 1.01L3 12v8c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-1h12v1c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-8l-2.08-5.99zM6.5 16c-.83 0-1.5-.67-1.5-1.5S5.67 13 6.5 13s1.5.67 1.5 1.5S7.33 16 6.5 16zm11 0c-.83 0-1.5-.67-1.5-1.5s.67-1.5 1.5-1.5 1.5.67 1.5 1.5-.67 1.5-1.5 1.5zM5 11l1.5-4.5h11L19 11H5z" />
                                </svg>
                            </div>
                        </div>
                    </div>

                    <!-- Subscribers Card -->
                    <div class="bg-white rounded-lg shadow-sm p-6 hover:shadow-md transition-shadow">
                        <div class="flex items-center justify-between">
                            <div>
                                <p class="text-sm text-gray-500 mb-1">Subscribers</p>
                                <p class="text-3xl font-bold text-gray-800">{{ stats.subscribers }}</p>
                                <p class="text-xs text-gray-400 mt-1">Total Subscribers</p>
                            </div>
                            <div class="w-12 h-12 bg-cyan-100 rounded-lg flex items-center justify-center">
                                <svg class="w-6 h-6 text-cyan-600" fill="none" stroke="currentColor"
                                    viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                        d="M19 11H5m14 0a2 2 0 012 2v6a2 2 0 01-2 2H5a2 2 0 01-2-2v-6a2 2 0 012-2m14 0V9a2 2 0 00-2-2M5 11V9a2 2 0 012-2m0 0V5a2 2 0 012-2h6a2 2 0 012 2v2M7 7h10" />
                                </svg>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Three Column Layout -->
                <div class="grid grid-cols-1 lg:grid-cols-3 gap-6 mt-8">
                    <!-- Left Column: Vehicle Status & Alerts -->
                    <div class="space-y-6">
                        <!-- Live Vehicle Status -->
                        <div class="bg-white rounded-lg shadow-sm p-6">
                            <h3 class="text-lg font-semibold text-gray-800 mb-4">Live Vehicle Status</h3>
                            <div class="space-y-3">
                                <div v-for="vehicle in vehicleStatus" :key="vehicle.id" class="flex items-center justify-between p-3 bg-gray-50 rounded-lg">
                                    <div class="flex items-center gap-3">
                                        <div :class="[
                                            'w-3 h-3 rounded-full',
                                            vehicle.status === 'active' ? 'bg-green-500' :
                                            vehicle.status === 'idle' ? 'bg-yellow-500' :
                                            vehicle.status === 'maintenance' ? 'bg-orange-500' : 'bg-gray-400'
                                        ]"></div>
                                        <div>
                                            <p class="text-sm font-medium text-gray-800">{{ vehicle.name }}</p>
                                            <p class="text-xs text-gray-500">{{ vehicle.driver }}</p>
                                        </div>
                                    </div>
                                    <span :class="[
                                        'text-xs px-2 py-1 rounded-full',
                                        vehicle.status === 'active' ? 'bg-green-100 text-green-700' :
                                        vehicle.status === 'idle' ? 'bg-yellow-100 text-yellow-700' :
                                        vehicle.status === 'maintenance' ? 'bg-orange-100 text-orange-700' : 'bg-gray-100 text-gray-700'
                                    ]">{{ vehicle.status }}</span>
                                </div>
                            </div>
                        </div>

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
import { ref } from 'vue'

const activeSection = ref('dashboard')
const lastLoginDay = ref(12)
const lastLoginTime = ref('1:28 AM')

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

const getSectionTitle = () => {
    const titles = {
        'employees': 'Employees',
        'drivers': 'Drivers',
        'passengers': 'Passengers',
        'roles': 'Roles & Permissions',
        'buses': 'Buses',
        'locations': 'Locations',
        'routes': 'Routes',
        'email': 'Email',
        'settings': 'Settings',
        'notifications': 'Notifications',
        'logout': 'Logout'
    }
    return titles[activeSection.value] || 'Dashboard'
}
</script>

<style scoped>
/* Add any additional custom styles here if needed */
</style>
