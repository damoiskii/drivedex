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
        log.info("Development-specific tasks completed.");
    }
    
    private void generateFakeData() {
        // Implement data loading logic here
        log.info("Loading development data...");

        fakerService.generateRoles(25);
        fakerService.generateActions(50);
        fakerService.generateSubscribers(100);

        //fakerService.generateUsers(300);
        fakerService.generateAdministrators(10);
        fakerService.generatePassengers(100);
        fakerService.generateEmployees(50);
        fakerService.generateDrivers(50);
        fakerService.generateProfilesForUsers();

        fakerService.generateBuses(50);
        fakerService.assignDriversToBuses();
        fakerService.generateBusLocations();

        fakerService.generateRoutes(50);
        fakerService.assignBusesToRoutes();
        fakerService.assignPassengersToFollowBuses();
    }
}
