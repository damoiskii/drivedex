package com.devdam.server.config.generators;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.devdam.server.service.FakerServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Profile("dev")
@Component
@Slf4j
@RequiredArgsConstructor
public class DevGens implements TaskRunner {
    private final FakerServiceImpl fakerService;

    @Override
    public void runTasks() {
        // Implement development-specific tasks here
        log.info("Running development-specific tasks...");
        generateFakeData();
    }
    
    private void generateFakeData() {
        // Implement data loading logic here
        log.info("Loading development data...");

        fakerService.generateSubscribers(100);
    }
}
