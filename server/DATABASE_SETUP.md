# DriveDex Server Database Configuration Guide

## Overview
Your Spring Boot reactive server is now configured to automatically initialize the database schema using SQL scripts. The configuration supports both MySQL (development) and PostgreSQL (production) environments.

## Configuration Files Updated

### 1. **application-dev.properties**
```properties
# MySQL Development Environment
spring.r2dbc.url=r2dbc:mysql://localhost:3306/drivedex
spring.r2dbc.username=root
spring.r2dbc.password=12345

# SQL Initialization Configuration
spring.sql.init.mode=always
spring.sql.init.platform=mysql
spring.sql.init.schema-locations=classpath:schema-mysql.sql
spring.sql.init.continue-on-error=false
spring.sql.init.separator=;

# JDBC DataSource for Schema Initialization
spring.datasource.url=jdbc:mysql://localhost:3306/drivedx
spring.datasource.username=root
spring.datasource.password=12345
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### 2. **application-prod.properties**
```properties
# PostgreSQL Production Environment
spring.r2dbc.url=r2dbc:postgresql://prod-host:5432/drivedx
spring.r2dbc.username=prod_user
spring.r2dbc.password=prod_password

# SQL Initialization Configuration
spring.sql.init.mode=always
spring.sql.init.platform=postgresql
spring.sql.init.schema-locations=classpath:schema-postgresql.sql
spring.sql.init.continue-on-error=false
spring.sql.init.separator=;

# JDBC DataSource for Schema Initialization
spring.datasource.url=jdbc:postgresql://prod-host:5432/drivedx
spring.datasource.username=prod_user
spring.datasource.password=prod_password
spring.datasource.driver-class-name=org.postgresql.Driver
```

## Schema Files Created

### 1. **schema.sql** (Original - MySQL compatible)
- Main schema file with AUTO_INCREMENT syntax

### 2. **schema-mysql.sql** (MySQL optimized)
- Uses AUTO_INCREMENT for primary keys
- MySQL-specific data types and syntax

### 3. **schema-postgresql.sql** (PostgreSQL optimized)
- Uses BIGSERIAL for primary keys
- DOUBLE PRECISION instead of DOUBLE
- PostgreSQL-specific syntax

## Database Tables Created

| Table | Purpose | Relationships |
|-------|---------|--------------|
| `users` | Base user authentication | → profiles |
| `profiles` | Extended user information | ← users |
| `routes` | Bus route definitions | → buses (via route_buses) |
| `buses` | Vehicle information | → routes, drivers, passengers |
| `drivers` | Driver-specific data | ← users, → buses |
| `passengers` | Passenger-specific data | ← users, → buses (via following) |
| `employees` | Employee-specific data | ← users |
| `locations` | GPS tracking for buses | ← buses |
| `passenger_followed_buses` | Many-to-many: passengers ↔ buses | Junction table |
| `route_buses` | Many-to-many: routes ↔ buses | Junction table |

## Key Configuration Properties

- **`spring.sql.init.mode=always`**: Always run SQL initialization scripts
- **`spring.sql.init.schema-locations`**: Points to the appropriate schema file
- **`spring.sql.init.continue-on-error=false`**: Stop on SQL errors
- **`spring.sql.init.separator=;`**: SQL statement separator

## Dependencies Added

```xml
<!-- JDBC Starter for SQL Initialization -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
```

## How It Works

1. **Application Startup**: Spring Boot detects the SQL initialization configuration
2. **Database Connection**: JDBC connection is established using the datasource properties
3. **Schema Execution**: The appropriate schema file is executed based on the platform
4. **R2DBC Ready**: After schema initialization, R2DBC connections are available for reactive operations
5. **Logging**: DatabaseInitializationConfig logs successful completion

## Verification Steps

1. **Check Logs**: Look for schema initialization messages during startup
2. **Database Inspection**: Connect to your database and verify tables exist
3. **Application Health**: Use actuator endpoints to check application health

## Environment Switching

The schema will be automatically selected based on your active Spring profile:
- **Development**: Uses `schema-mysql.sql` with MySQL
- **Production**: Uses `schema-postgresql.sql` with PostgreSQL

## Troubleshooting

If schemas don't initialize:
1. Check database connectivity
2. Verify schema file syntax
3. Review application logs for errors
4. Ensure `spring.sql.init.mode=always` is set
5. Confirm JDBC drivers are in classpath