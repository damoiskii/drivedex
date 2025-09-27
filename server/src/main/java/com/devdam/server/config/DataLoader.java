package com.devdam.server.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.devdam.server.repository.BusRepository;
import com.devdam.server.repository.SubscriberRepository;
import com.devdam.server.service.FakerServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Profile("dev")
@Component
@Slf4j
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final FakerServiceImpl fakerService;
    
    private final BusRepository busRepository;
    private final SubscriberRepository subscriberRepository;

    @Override
    public void run(String... args) throws Exception {
        // subscribe() to ensure execution in reactive streams
        fakerService.generateBuses(20).forEach(bus -> busRepository.save(bus).subscribe()); // reactive save
        fakerService.generateSubscribers(20).forEach(subscriber -> {
            subscriberRepository.save(subscriber).subscribe();
        });

        log.info("Sample data loaded.");
    }
}
