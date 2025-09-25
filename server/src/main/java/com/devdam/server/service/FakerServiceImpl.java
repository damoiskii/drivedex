package com.devdam.server.service;

import com.devdam.server.model.Bus;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class FakerServiceImpl implements FakerService {
    private final Faker faker = new Faker();

    @Override
    public List<Bus> generateBuses(int count) {
        List<Bus> buses = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Bus bus = Bus.builder()
                    .licensePlate(faker.bothify("??-####"))
                    .busNumber(ThreadLocalRandom.current().nextInt(1, 100))
                    .driverId(null)   // no driver assigned yet
                    .passengerId(null) // no passenger yet
                    //.routeId(ThreadLocalRandom.current().nextLong(1, 15)) // assuming 5 routes exist
                    .routeId(null) // assuming 15 routes exist
                    .build();

            buses.add(bus);
        }

        return buses;
    }
}
