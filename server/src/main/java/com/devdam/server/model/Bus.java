package com.devdam.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Table("buses")
public class Bus {
    @Id
    private Long id;

    private String licensePlate;
    private Integer busNumber;

    private Long driverId;
    private Long passengerId;
    private Long routeId;
    // private Long locationId;    
}
