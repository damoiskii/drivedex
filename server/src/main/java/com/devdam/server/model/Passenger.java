package com.devdam.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("passengers")
public class Passenger {
    @Id
    private Long userId; // PK and FK to users.id
    // private Set<Long> followedBusesIds;
}

