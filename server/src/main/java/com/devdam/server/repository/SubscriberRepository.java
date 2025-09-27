package com.devdam.server.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.devdam.server.enums.SubscriberType;
import com.devdam.server.model.Subscriber;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SubscriberRepository extends ReactiveCrudRepository<Subscriber, Long> {
    Mono<Boolean> existsByEmailEqualsIgnoreCase(String email);
    Mono<Boolean> existsByUnsubscribeCodeEqualsIgnoreCase(String unsubscribeCode);

    Mono<Subscriber> findByEmailEqualsIgnoreCase(String email);
    Mono<Subscriber> findByUnsubscribeCode(String unsubscribeCode);

    Flux<Subscriber> findAllByType(SubscriberType type);
    Flux<Subscriber> findAllByIsInformed(Boolean isInformed);

    Mono<Long> countByType(SubscriberType type);
    Mono<Long> countByIsInformed(Boolean isInformed);
}
