package com.devdam.server.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devdam.server.model.Bus;
import com.devdam.server.repository.BusRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BusServiceImpl implements BusService {
    private final BusRepository busRepository;
    
    @Override
    public Mono<Page<Bus>> getBusesByBusNumber(Integer busNumber, Pageable pageable) {
        long limit = pageable.getPageSize();
        long offset = pageable.getOffset();

        return busRepository.findAllByBusNumberPaged(busNumber, limit, offset)
                .collectList()
                .zipWith(busRepository.countByBusNumber(busNumber))
                .map(tuple -> new PageImpl<>(tuple.getT1(), pageable, tuple.getT2()));
    }

    @Override
    public Mono<Page<Bus>> getPassengerFollowedBusesWithFilters(Long passengerId, String licensePlate,
            Integer busNumber, String place, String route, Pageable pageable) {
        long limit = pageable.getPageSize();
        long offset = pageable.getOffset();

        return busRepository.findByPassengerAndFiltersPaged(passengerId, licensePlate, busNumber, place, route, limit, offset)
                .collectList()
                .zipWith(busRepository.countByPassengerId(passengerId))
                .map(tuple -> new PageImpl<>(tuple.getT1(), pageable, tuple.getT2()));
    }
    
}
