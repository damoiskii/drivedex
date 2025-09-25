package com.devdam.server.dto.web;

import java.util.List;

import com.devdam.server.model.Bus;
import com.devdam.server.model.Route;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RouteWithBusesResponse {
    private Route route;
    private List<Bus> buses;
}
