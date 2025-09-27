package com.devdam.server.service;

import com.devdam.server.enums.SubscriberType;
import com.devdam.server.model.Bus;
import com.devdam.server.model.Subscriber;
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
        List<String> plateNumbers = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String plateNumber;
            do {
                plateNumber = faker.bothify("??-####");
            } while (plateNumbers.contains(plateNumber));

            plateNumbers.add(plateNumber);

            Bus bus = Bus.builder()
                    .licensePlate(plateNumber)
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

    @Override
    public List<Subscriber> generateSubscribers(int count) {
        List<Subscriber> subscribers = new ArrayList<>();
        List<String> codes = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String code;

            do {
                code = faker.bothify("????-####");
            } while (codes.contains(code));

            codes.add(code);

            Subscriber subscriber = Subscriber.builder()
                    .email(faker.internet().emailAddress())
                    .type(faker.options().option(SubscriberType.class))
                    .isInformed(faker.bool().bool())
                    .unsubscribeCode(code)
                    .build();

            subscribers.add(subscriber);
        }

        return subscribers;
    }
}
