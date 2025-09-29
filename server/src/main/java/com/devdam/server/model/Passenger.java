package com.devdam.server.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "passengers")
@PrimaryKeyJoinColumn(name = "id")
public class Passenger extends User {

    @ManyToMany(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "passengers_buses",
            joinColumns = {
                    @JoinColumn(name = "passenger_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "bus_id")
            }
    )
    // @OneToMany(cascade = CascadeType.ALL, mappedBy = "passenger", fetch = FetchType.LAZY)
    @OrderBy("busNumber DESC")
    @Builder.Default
    private Set<Bus> followedBuses = new HashSet<>();
}