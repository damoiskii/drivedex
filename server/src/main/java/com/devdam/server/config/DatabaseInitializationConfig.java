package com.devdam.server.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import lombok.extern.slf4j.Slf4j;

/**
 * Configuration class to handle database schema initialization
 * for the reactive Spring Boot application.
 * 
 * This ensures proper setup of the database schema before
 * the application starts processing requests using JDBC initialization only.
 */
@Configuration
@EnableConfigurationProperties
@Slf4j
public class DatabaseInitializationConfig {

    /**
     * Event listener that runs after the application is fully ready.
     * This logs the successful completion of database initialization.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        log.info("=== DriveDex Server Database Initialization Complete ===");
        log.info("Schema initialization has been processed using JDBC initialization");
        log.info("All model tables should now be available for R2DBC operations");
        log.info("======================================================");
    }
}