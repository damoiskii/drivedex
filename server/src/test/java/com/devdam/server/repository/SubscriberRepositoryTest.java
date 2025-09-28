package com.devdam.server.repository;

import com.devdam.server.enums.SubscriberType;
import com.devdam.server.model.Subscriber;

import lombok.RequiredArgsConstructor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@DataR2dbcTest
// @ExtendWith(SpringExtension.class)
@RequiredArgsConstructor
class SubscriberRepositoryTest {

    // @Autowired
    private final SubscriberRepository subscriberRepository;

    private Subscriber subscriber;

    @BeforeEach
    void setUp() {
        subscriber = Subscriber.builder()
            .dateSubscribed(java.time.LocalDateTime.now())
            .email("test@example.com")
            .unsubscribeCode("UNSUB123")
            .type(SubscriberType.ALERTS)
            .isInformed(true)
            .build();

        // Clean and insert test data
        subscriberRepository.deleteAll()
            .then(subscriberRepository.save(subscriber))
            .block();
    }

    @Test
    void testExistsByEmailEqualsIgnoreCase() {
        Mono<Boolean> exists = subscriberRepository.existsByEmailEqualsIgnoreCase("TEST@example.com");
        StepVerifier.create(exists)
            .expectNext(true)
            .verifyComplete();
    }

    // @Test
    // void testExistsByUnsubscribeCodeEqualsIgnoreCase() {
    //     Mono<Boolean> exists = subscriberRepository.existsByUnsubscribeCodeEqualsIgnoreCase("unsub123");
    //     StepVerifier.create(exists)
    //         .expectNext(true)
    //         .verifyComplete();
    // }

    // @Test
    // void testFindByEmailEqualsIgnoreCase() {
    //     Mono<Subscriber> found = subscriberRepository.findByEmailEqualsIgnoreCase("TEST@example.com");
    //     StepVerifier.create(found)
    //         .expectNextMatches(s -> s.getEmail().equalsIgnoreCase("test@example.com"))
    //         .verifyComplete();
    // }

    // @Test
    // void testFindByUnsubscribeCode() {
    //     Mono<Subscriber> found = subscriberRepository.findByUnsubscribeCode("UNSUB123");
    //     StepVerifier.create(found)
    //         .expectNextMatches(s -> s.getUnsubscribeCode().equals("UNSUB123"))
    //         .verifyComplete();
    // }

    // @Test
    // void testFindAllByType() {
    //     StepVerifier.create(subscriberRepository.findAllByType(SubscriberType.ALERTS))
    //         .expectNextMatches(s -> s.getType() == SubscriberType.ALERTS)
    //         .verifyComplete();
    // }

    // @Test
    // void testFindAllByIsInformed() {
    //     StepVerifier.create(subscriberRepository.findAllByIsInformed(true))
    //         .expectNextMatches(s -> s.getIsInformed())
    //         .verifyComplete();
    // }

    // @Test
    // void testCountByType() {
    //     StepVerifier.create(subscriberRepository.countByType(SubscriberType.ALERTS))
    //         .expectNext(1L)
    //         .verifyComplete();
    // }

    // @Test
    // void testCountByIsInformed() {
    //     StepVerifier.create(subscriberRepository.countByIsInformed(true))
    //         .expectNext(1L)
    //         .verifyComplete();
    // }
}