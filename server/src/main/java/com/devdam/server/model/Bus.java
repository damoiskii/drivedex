package com.devdam.server.model;

import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"passengers"})
@EqualsAndHashCode(exclude = {"route", "location", "passengers"})
@Entity
@Table(name = "buses")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "license_plate", nullable = false, unique = true)
    private String licensePlate;

    @Column(name = "bus_number", nullable = false)
    private Integer busNumber;

    @OneToOne(mappedBy = "bus", cascade = {CascadeType.ALL})
    private Driver driver;

    @OneToOne(mappedBy = "bus", cascade = {CascadeType.ALL})
    private Location location;

    @ManyToOne() //fetch = FetchType.EAGER
    @JoinColumn(name = "route_id")
    private Route route;

    // @ManyToOne() //fetch = FetchType.EAGER
    // @JoinColumn(name = "passenger_id")
    // private Passenger passenger;

    @ManyToMany(mappedBy = "followedBuses", cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @OrderBy("username ASC")
    @Builder.Default
    private Set<Passenger> passengers = new HashSet<>();
}
