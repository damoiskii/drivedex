package com.devdam.server.dto.web;

import com.devdam.server.model.Bus;
import com.devdam.server.model.Location;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationWithBusResponse {
    private Location location;
    private Bus bus;
}
