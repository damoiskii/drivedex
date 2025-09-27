package com.devdam.server.service;

import java.util.List;

import com.devdam.server.model.Bus;
import com.devdam.server.model.Subscriber;

public interface FakerService {
    List<Bus> generateBuses(int count);
    List<Subscriber> generateSubscribers(int count);
}
