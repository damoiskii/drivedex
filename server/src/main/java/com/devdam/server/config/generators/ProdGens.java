package com.devdam.server.config.generators;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.devdam.server.config.props.SuperuserProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Profile("prod")
@Component
@Slf4j
@RequiredArgsConstructor
public class ProdGens implements TaskRunner {
    private final SuperuserProperties superuserProperties;

    @Override
    public void runTasks() {
        // Implement production-specific tasks here
        log.info("Running production-specific tasks...");
        createSuperuser();
    }

    // Additional methods to create superuser or other prod-specific tasks can be added here
    private void createSuperuser() {
        String username = superuserProperties.getUsername();
        String email = superuserProperties.getEmail();
        String password = superuserProperties.getPassword();

        // Logic to create a superuser in the system
        log.info("Creating superuser with username: {}, email: {}, password: {}", username, email, password);
        // Add actual user creation logic here
    }
    
}
