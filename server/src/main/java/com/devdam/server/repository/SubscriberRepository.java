package com.devdam.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devdam.server.enums.SubscriberType;
import com.devdam.server.model.Subscriber;

import java.util.List;


@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    Subscriber findByEmailEqualsIgnoreCase(String email);
    Subscriber findByUnsubscribeCodeEqualsIgnoreCase(String code);

    List<Subscriber> findAllByTypeEqualsOrderByTypeAsc(SubscriberType type);
}