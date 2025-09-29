package com.devdam.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devdam.server.enums.SubscriberType;
import com.devdam.server.model.Subscriber;
import com.devdam.server.repository.SubscriberRepository;
import com.github.javafaker.Faker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Profile("dev")
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class FakerServiceImpl implements FakerService {
    private final Faker faker = new Faker();

    private final SubscriberRepository subscriberRepository;

    @Override
    public void generateSubscribers(int amount) {
        // Check if subscribers already exist
        if (subscriberRepository.count() > 0) {
            log.info("Subscribers already exist in the database. Skipping generation.");
            return;
        }

        log.info("Generating subscribers...");
        List<Subscriber> subscribers = new ArrayList<>();
        
        for (int i = 0; i < amount; i++) {
            subscribers.add(Subscriber.builder()
                .email(faker.internet().emailAddress())
                .unsubscribeCode(faker.internet().uuid())
                .type(faker.options().option(SubscriberType.class))
                .build()
            );
        }

        subscriberRepository.saveAll(subscribers);
        log.info("Subscriber generation completed!");
    }
    
}
