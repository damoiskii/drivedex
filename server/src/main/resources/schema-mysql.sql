-- DriveDx Server Database Schema - MySQL Version
-- This schema supports all model classes for the reactive Spring Boot server

-- Drop existing tables in correct order (respecting foreign key constraints)
DROP TABLE IF EXISTS subscribers;
DROP TABLE IF EXISTS passenger_followed_buses;
DROP TABLE IF EXISTS route_buses;
DROP TABLE IF EXISTS locations;
DROP TABLE IF EXISTS drivers;
DROP TABLE IF EXISTS passengers;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS buses;
DROP TABLE IF EXISTS routes;
DROP TABLE IF EXISTS profiles;
DROP TABLE IF EXISTS users;

-- Create users table (base table)
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    profile_id BIGINT,
    is_active BOOLEAN DEFAULT FALSE,
    role VARCHAR(20) DEFAULT 'USER',
    
    CONSTRAINT chk_user_role CHECK (role IN ('USER', 'PASSENGER', 'EMPLOYEE', 'DRIVER', 'ADMIN'))
);

-- Create profiles table
CREATE TABLE profiles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    photo_url VARCHAR(500),
    filename VARCHAR(255),
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_logged_in TIMESTAMP,
    user_id BIGINT NOT NULL,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    phone_number VARCHAR(20),
    address TEXT,
    
    CONSTRAINT fk_profile_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT uk_profile_user UNIQUE (user_id)
);

-- Create routes table
CREATE TABLE routes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    start_point VARCHAR(200) NOT NULL,
    end_point VARCHAR(200) NOT NULL,
    fare DECIMAL(10,2) NOT NULL,
    
    CONSTRAINT chk_fare_positive CHECK (fare >= 0)
);

-- Create buses table
CREATE TABLE buses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    license_plate VARCHAR(20) NOT NULL UNIQUE,
    bus_number INTEGER NOT NULL UNIQUE,
    driver_id BIGINT,
    passenger_id BIGINT,
    route_id BIGINT,
    
    CONSTRAINT fk_bus_route FOREIGN KEY (route_id) REFERENCES routes(id) ON DELETE SET NULL,
    CONSTRAINT chk_bus_number_positive CHECK (bus_number > 0)
);

-- Create drivers table (extends users)
CREATE TABLE drivers (
    user_id BIGINT PRIMARY KEY,
    bus_id BIGINT,
    
    CONSTRAINT fk_driver_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_driver_bus FOREIGN KEY (bus_id) REFERENCES buses(id) ON DELETE SET NULL,
    CONSTRAINT uk_driver_bus UNIQUE (bus_id)
);

-- Create passengers table (extends users)
CREATE TABLE passengers (
    user_id BIGINT PRIMARY KEY,
    
    CONSTRAINT fk_passenger_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Create employees table (extends users)
CREATE TABLE employees (
    user_id BIGINT PRIMARY KEY,
    is_regular BOOLEAN DEFAULT FALSE,
    
    CONSTRAINT fk_employee_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Create locations table (GPS tracking for buses)
CREATE TABLE locations (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
    place VARCHAR(255),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    bus_id BIGINT NOT NULL,
    
    CONSTRAINT fk_location_bus FOREIGN KEY (bus_id) REFERENCES buses(id) ON DELETE CASCADE,
    CONSTRAINT chk_latitude CHECK (latitude >= -90 AND latitude <= 90),
    CONSTRAINT chk_longitude CHECK (longitude >= -180 AND longitude <= 180)
);

-- Create passenger_followed_buses table (many-to-many relationship)
CREATE TABLE passenger_followed_buses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    passenger_id BIGINT NOT NULL,
    bus_id BIGINT NOT NULL,
    
    CONSTRAINT fk_pfb_passenger FOREIGN KEY (passenger_id) REFERENCES passengers(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_pfb_bus FOREIGN KEY (bus_id) REFERENCES buses(id) ON DELETE CASCADE,
    CONSTRAINT uk_passenger_bus UNIQUE (passenger_id, bus_id)
);

-- Create route_buses table (many-to-many relationship)
CREATE TABLE route_buses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    route_id BIGINT NOT NULL,
    bus_id BIGINT NOT NULL,
    
    CONSTRAINT fk_rb_route FOREIGN KEY (route_id) REFERENCES routes(id) ON DELETE CASCADE,
    CONSTRAINT fk_rb_bus FOREIGN KEY (bus_id) REFERENCES buses(id) ON DELETE CASCADE,
    CONSTRAINT uk_route_bus UNIQUE (route_id, bus_id)
);

-- Create subscribers table
CREATE TABLE subscribers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL,
    type VARCHAR(50),
    is_informed BOOLEAN DEFAULT FALSE,
    unsubscribe_code VARCHAR(255),
    date_subscribed TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT uk_subscriber_email_type UNIQUE (email, type),
    CONSTRAINT chk_email_format CHECK (email LIKE '%_@%_._%')
);

-- Add foreign key constraint from users to profiles (circular reference)
ALTER TABLE users ADD CONSTRAINT fk_user_profile FOREIGN KEY (profile_id) REFERENCES profiles(id) ON DELETE SET NULL;

-- Add foreign key constraints to buses table (after driver and passenger tables exist)
ALTER TABLE buses ADD CONSTRAINT fk_bus_driver FOREIGN KEY (driver_id) REFERENCES drivers(user_id) ON DELETE SET NULL;
ALTER TABLE buses ADD CONSTRAINT fk_bus_passenger FOREIGN KEY (passenger_id) REFERENCES passengers(user_id) ON DELETE SET NULL;

-- Create indexes for better performance
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_role ON users(role);
CREATE INDEX idx_profiles_user_id ON profiles(user_id);
CREATE INDEX idx_buses_license_plate ON buses(license_plate);
CREATE INDEX idx_buses_bus_number ON buses(bus_number);
CREATE INDEX idx_buses_route_id ON buses(route_id);
CREATE INDEX idx_locations_bus_id ON locations(bus_id);
CREATE INDEX idx_locations_timestamp ON locations(timestamp);
CREATE INDEX idx_pfb_passenger_id ON passenger_followed_buses(passenger_id);
CREATE INDEX idx_pfb_bus_id ON passenger_followed_buses(bus_id);
CREATE INDEX idx_rb_route_id ON route_buses(route_id);
CREATE INDEX idx_rb_bus_id ON route_buses(bus_id);
CREATE INDEX idx_subscribers_email ON subscribers(email);
CREATE INDEX idx_subscribers_type ON subscribers(type);
CREATE INDEX idx_subscribers_date_subscribed ON subscribers(date_subscribed);