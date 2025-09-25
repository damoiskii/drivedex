-- DriveDex Server Database Schema - Simplified MySQL Version
-- This schema supports all model classes for the reactive Spring Boot server
-- Simplified to avoid R2DBC initialization issues

-- Create users table (base table)
CREATE TABLE IF NOT EXISTS users (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    profile_id BIGINT,
    is_active BOOLEAN DEFAULT FALSE,
    role VARCHAR(20) DEFAULT 'USER'
);

-- Create profiles table
CREATE TABLE IF NOT EXISTS profiles (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    photo_url VARCHAR(500),
    filename VARCHAR(255),
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_logged_in TIMESTAMP,
    user_id BIGINT NOT NULL,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    phone_number VARCHAR(20),
    address TEXT,
    UNIQUE (user_id)
);

-- Create routes table
CREATE TABLE IF NOT EXISTS routes (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    start_point VARCHAR(200) NOT NULL,
    end_point VARCHAR(200) NOT NULL,
    fare DECIMAL(10,2) NOT NULL DEFAULT 0
);

-- Create buses table
CREATE TABLE IF NOT EXISTS buses (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    license_plate VARCHAR(20) NOT NULL UNIQUE,
    bus_number INTEGER NOT NULL UNIQUE,
    driver_id BIGINT,
    passenger_id BIGINT,
    route_id BIGINT
);

-- Create drivers table (extends users)
CREATE TABLE IF NOT EXISTS drivers (
    user_id BIGINT NOT NULL PRIMARY KEY,
    bus_id BIGINT,
    UNIQUE (bus_id)
);

-- Create passengers table (extends users)
CREATE TABLE IF NOT EXISTS passengers (
    user_id BIGINT NOT NULL PRIMARY KEY
);

-- Create employees table (extends users)
CREATE TABLE IF NOT EXISTS employees (
    user_id BIGINT NOT NULL PRIMARY KEY,
    is_regular BOOLEAN DEFAULT FALSE
);

-- Create locations table (GPS tracking for buses)
CREATE TABLE IF NOT EXISTS locations (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
    place VARCHAR(255),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    bus_id BIGINT NOT NULL
);

-- Create passenger_followed_buses table (many-to-many relationship)
CREATE TABLE IF NOT EXISTS passenger_followed_buses (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    passenger_id BIGINT NOT NULL,
    bus_id BIGINT NOT NULL,
    UNIQUE (passenger_id, bus_id)
);

-- Create route_buses table (many-to-many relationship)
CREATE TABLE IF NOT EXISTS route_buses (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    route_id BIGINT NOT NULL,
    bus_id BIGINT NOT NULL,
    UNIQUE (route_id, bus_id)
);

-- Create indexes for better performance
CREATE INDEX IF NOT EXISTS idx_users_username ON users(username);
CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);
CREATE INDEX IF NOT EXISTS idx_users_role ON users(role);
CREATE INDEX IF NOT EXISTS idx_profiles_user_id ON profiles(user_id);
CREATE INDEX IF NOT EXISTS idx_buses_license_plate ON buses(license_plate);
CREATE INDEX IF NOT EXISTS idx_buses_bus_number ON buses(bus_number);
CREATE INDEX IF NOT EXISTS idx_buses_route_id ON buses(route_id);
CREATE INDEX IF NOT EXISTS idx_locations_bus_id ON locations(bus_id);
CREATE INDEX IF NOT EXISTS idx_locations_timestamp ON locations(timestamp);
CREATE INDEX IF NOT EXISTS idx_pfb_passenger_id ON passenger_followed_buses(passenger_id);
CREATE INDEX IF NOT EXISTS idx_pfb_bus_id ON passenger_followed_buses(bus_id);
CREATE INDEX IF NOT EXISTS idx_rb_route_id ON route_buses(route_id);
CREATE INDEX IF NOT EXISTS idx_rb_bus_id ON route_buses(bus_id);