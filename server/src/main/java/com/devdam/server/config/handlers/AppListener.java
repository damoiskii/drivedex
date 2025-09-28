package com.devdam.server.config.handlers;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import lombok.extern.slf4j.Slf4j;

@Configuration
// @EnableConfigurationProperties
@Slf4j
public class AppListener {
    

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        log.info("Application is ready to serve requests.");
        // Additional initialization logic can be added here
    }

    @EventListener(ContextClosedEvent.class)
    public void onClose() {
        log.info("Application context is closing. Performing cleanup...");
        // Cleanup logic can be added here
    }
}
