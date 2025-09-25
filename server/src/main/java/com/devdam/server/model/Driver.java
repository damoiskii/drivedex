package com.devdam.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("drivers")
public class Driver {
    @Id
    private Long userId; // PK and FK to users.id
    private Long busId; // FK to buses.id
}
