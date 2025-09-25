package com.devdam.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("route_buses")
public class RouteBus {
    @Id
    private Long id; // optional, could also use composite PK
    private Long routeId;
    private Long busId;
}
