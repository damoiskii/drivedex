package com.devdam.server.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "start_point", nullable = false)
    private String startPoint;

    @Column(name = "end_point", nullable = false)
    private String endPoint;

    @Column(name = "fare", nullable = false)
    @Builder.Default
    private Double fare = 0.0;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "route", fetch = FetchType.LAZY) //orphanRemoval = true,
    @OrderBy("busNumber DESC")
    @Builder.Default
    private Set<Bus> buses = new HashSet<>();
}