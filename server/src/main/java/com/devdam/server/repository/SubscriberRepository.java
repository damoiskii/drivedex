package com.devdam.server.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.devdam.server.enums.SubscriberType;
import com.devdam.server.model.Subscriber;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SubscriberRepository extends ReactiveCrudRepository<Subscriber, Long> {
    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END " + "FROM subscribers WHERE LOWER(email) = LOWER(:email)")
    Mono<Boolean> existsByEmailEqualsIgnoreCase(String email);

    Mono<Boolean> existsByUnsubscribeCodeEquals(String unsubscribeCode);

    // ✅ Find by email, case-insensitive
    @Query("SELECT * FROM subscribers WHERE LOWER(email) = LOWER(:email) LIMIT 1")
    Mono<Subscriber> findByEmailEqualsIgnoreCase(String email);

    // ✅ Find by unsubscribeCode, case-insensitive
    // @Query("SELECT * FROM subscribers WHERE LOWER(unsubscribe_code) = LOWER(:unsubscribeCode) LIMIT 1")
    Mono<Subscriber> findByUnsubscribeCode(String unsubscribeCode);

    Flux<Subscriber> findAllByType(SubscriberType type);
    Flux<Subscriber> findAllByIsInformed(Boolean isInformed);

    Mono<Long> countByType(SubscriberType type);
    Mono<Long> countByIsInformed(Boolean isInformed);
}
