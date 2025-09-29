package com.devdam.server.config.generators;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.devdam.server.config.props.SuperuserProperties;
import com.devdam.server.model.Role;
import com.devdam.server.model.User;
import com.devdam.server.repository.RoleRepository;
import com.devdam.server.repository.UserRepository;
import com.github.slugify.Slugify;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Profile("prod")
@Component
@Slf4j
@RequiredArgsConstructor
public class ProdGens implements TaskRunner {
    private final SuperuserProperties superuserProperties;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final Slugify slugify = new Slugify();

    @Override
    public void runTasks() {
        // Implement production-specific tasks here
        log.info("Running production-specific tasks...");

        createSuperuser();

        log.info("Production-specific tasks completed.");
    }

    private Role getRole(String name) {
        Role role = roleRepository.findByNameEqualsIgnoreCase(name);

        if (role == null) {
            role = roleRepository.save(Role.builder()
                .name(name)
                .slug(slugify.slugify(name))
                .read(true)
                .write( true)
                .execute(true)
                .build());
        }

        return role;
    }

    // Additional methods to create superuser or other prod-specific tasks can be added here
    private void createSuperuser() {
        if (userRepository.countByIsSuperuserIsTrue() > 0) {
            log.info("Superuser already exists. Skipping generation.");
            return;
        }

        log.info("Creating superuser here...");
        
        User admin = User.builder()
                .username(superuserProperties.getUsername())
                .email(superuserProperties.getEmail())
                .password(superuserProperties.getPassword()) // encode in real app
                .isActive(true)
                .isStaff(true)
                .isSuperuser(true)
                .roles(Set.of(getRole("Administrator")))
                .build();
        
        userRepository.saveAndFlush(admin);
        log.info("Superuser added successfully!");
    }
}
