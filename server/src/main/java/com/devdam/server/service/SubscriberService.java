package com.devdam.server.service;

import com.devdam.server.enums.SubscriberType;
import com.devdam.server.model.Subscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SubscriberService {
    Mono<Subscriber> saveSubscriberEmail(String email, SubscriberType type);
    Flux<Subscriber> informSubscribersByType(SubscriberType type, String subject, String content);
    Mono<Void> unsubscribeByCode(String unsubscribeCode);
    Mono<Void> deleteSubscriberByEmail(String email);
    Mono<Boolean> existsByEmail(String email);
    Mono<Boolean> existsByUnsubscribeCode(String unsubscribeCode);
    Mono<Long> countByType(SubscriberType type);
    Mono<Long> countByIsInformed(Boolean isInformed);
    Mono<Subscriber> findByEmail(String email);
    // Mono<Subscriber> findByUnsubscribeCode(String unsubscribeCode);
}

