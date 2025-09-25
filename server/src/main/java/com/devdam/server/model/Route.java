package com.devdam.server.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("routes")
public class Route {
    @Id
    private Long id;

    private String name;
    private String startPoint;
    private String endPoint;
    private Double fare;

    private Set<Bus> buses;
}
