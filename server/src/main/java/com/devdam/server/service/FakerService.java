package com.devdam.server.service;

import java.util.List;

import com.devdam.server.model.Bus;

public interface FakerService {
    List<Bus> generateBuses(int count);
}
