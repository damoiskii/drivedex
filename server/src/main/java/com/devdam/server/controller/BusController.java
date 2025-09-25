package com.devdam.server.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devdam.server.model.Bus;
import com.devdam.server.service.BusServiceImpl;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("${api.path}/buses")
@RequiredArgsConstructor
public class BusController {
    private final BusServiceImpl busService;

    @GetMapping
    public Mono<Page<Bus>> getBuses(@RequestParam Integer busNumber, Pageable pageable) {
        return busService.getBusesByBusNumber(busNumber, pageable);
    }
}
