package com.devdam.server.config.handlers;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import com.devdam.server.config.generators.TaskRunner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
// @EnableConfigurationProperties
@Slf4j
@RequiredArgsConstructor
public class AppListener {
    private final TaskRunner taskRunner;

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        log.info("Application is ready to serve requests.");
        // Additional initialization logic can be added here
        taskRunner.runTasks();
    }

    @EventListener(ContextClosedEvent.class)
    public void onClose() {
        log.info("Application context is closing. Performing cleanup...");
        // Cleanup logic can be added here
    }
}
