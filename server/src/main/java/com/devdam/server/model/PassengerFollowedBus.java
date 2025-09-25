package com.devdam.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("passenger_followed_buses")
public class PassengerFollowedBus {
    @Id 
    private Long id;
    private Long passengerId;
    private Long busId;
}
