package com.devdam.server.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("locations")
public class Location {
    @Id
    private Long id;

    private Double latitude;
    private Double longitude;
    private String place;

    private LocalDateTime timestamp;

    private Long busId; // FK to Bus.id
}
