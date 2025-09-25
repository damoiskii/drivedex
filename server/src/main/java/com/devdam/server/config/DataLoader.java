package com.devdam.server.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.devdam.server.repository.BusRepository;
import com.devdam.server.service.FakerServiceImpl;

import lombok.RequiredArgsConstructor;

@Profile("dev")
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final FakerServiceImpl fakerService;
    private final BusRepository busRepository;

    @Override
    public void run(String... args) throws Exception {
        // subscribe() to ensure execution in reactive streams
        fakerService.generateBuses(20).forEach(bus -> busRepository.save(bus).subscribe()); // reactive save
        
    }
}
