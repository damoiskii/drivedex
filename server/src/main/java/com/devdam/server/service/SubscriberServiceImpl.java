package com.devdam.server.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devdam.server.enums.SubscriberType;
import com.devdam.server.model.Subscriber;
import com.devdam.server.repository.SubscriberRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscriberServiceImpl implements SubscriberService {
    private final SubscriberRepository subscriberRepository;
    // private final EmailService emailService;

    @Override
    public Mono<Subscriber> saveSubscriberEmail(String email, SubscriberType type) {
        return subscriberRepository.save(Subscriber.builder()
                .email(email)
                .type(type)
                .build());
    }

    @Override
    public Flux<Subscriber> informSubscribersByType(SubscriberType type, String subject, String content) {
        return subscriberRepository.findAllByType(type)
                .doOnNext(subscriber -> {
                    // send email here
                });
    }

    @Override
    public Mono<Void> unsubscribeByCode(String unsubscribeCode) {
        return subscriberRepository.findByUnsubscribeCode(unsubscribeCode)
                .flatMap(subscriber -> {
                    subscriber.setType(SubscriberType.UNSUBSCRIBED);
                    return subscriberRepository.save(subscriber);
                })
                .then(); // return Mono<Void>
    }

    @Override
    public Mono<Void> deleteSubscriberByEmail(String email) {
        return subscriberRepository.findByEmailEqualsIgnoreCase(email)
                .flatMap(subscriberRepository::delete)
                .then();
    }

    @Override
    public Mono<Boolean> existsByEmail(String email) {
        return subscriberRepository.existsByEmailEqualsIgnoreCase(email);
    }

    @Override
    public Mono<Boolean> existsByUnsubscribeCode(String unsubscribeCode) {
        return subscriberRepository.existsByUnsubscribeCodeEquals(unsubscribeCode);
    }

    @Override
    public Mono<Long> countByType(SubscriberType type) {
        return subscriberRepository.countByType(type);
    }

    @Override
    public Mono<Long> countByIsInformed(Boolean isInformed) {
        return subscriberRepository.countByIsInformed(isInformed);
    }

    @Override
    public Mono<Subscriber> findByEmail(String email) {
        return subscriberRepository.findByEmailEqualsIgnoreCase(email);
    }

    // @Override
    // public Mono<Subscriber> findByUnsubscribeCode(String unsubscribeCode) {
    //     return subscriberRepository.findByUnsubscribeCode(unsubscribeCode);
    // }
}
