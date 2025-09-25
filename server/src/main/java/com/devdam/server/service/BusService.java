package com.devdam.server.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devdam.server.model.Bus;

import reactor.core.publisher.Mono;

public interface BusService {
    public Mono<Page<Bus>> getBusesByBusNumber(Integer busNumber, Pageable pageable);
    public Mono<Page<Bus>> getPassengerFollowedBusesWithFilters(
            Long passengerId,
            String licensePlate,
            Integer busNumber,
            String place,
            String route,
            Pageable pageable
    );
}
