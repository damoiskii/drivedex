package com.devdam.server.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.devdam.server.model.Bus;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BusRepository extends ReactiveCrudRepository<Bus, Long> {
    // Exist by license plate
    Mono<Boolean> existsByLicensePlateEqualsIgnoreCase(String licensePlate);

    // === Simple finders ===
    Mono<Bus> findByLicensePlateEqualsIgnoreCase(String licensePlate);
    Flux<Bus> findAllByRouteId(Long routeId);
    Flux<Bus> findAllByBusNumber(Integer busNumber);
    Flux<Bus> findAllByPassengerId(Long passengerId);
    Flux<Bus> findAllByDriverId(Long driverId);
    // Flux<Bus> findAllByDriverIdIsNull();

    // // === Advanced searches (without paging) ===
    // Flux<Bus> findAllByDriverFirstnameContainsIgnoreCaseOrDriverLastnameContainsIgnoreCaseOrDriverUsernameContainsIgnoreCaseOrDriverEmailContainsIgnoreCaseOrLicensePlateContainsIgnoreCaseOrBusNumberGreaterThanEqualOrLocationPlaceContainsIgnoreCaseOrRouteNameContainsIgnoreCase(
    //     String firstname,
    //     String lastname,
    //     String username,
    //     String email,
    //     String licensePlate,
    //     Integer busNumber,
    //     String place,
    //     String route
    // );

    // Flux<Bus> findAllByLicensePlateContainsIgnoreCaseOrBusNumberEqualsOrLocationPlaceContainsIgnoreCaseOrRouteNameContainsIgnoreCase(
    //     String licensePlate,
    //     Integer busNumber,
    //     String place,
    //     String route
    // );

    // === Custom pagination with LIMIT/OFFSET ===
    @Query("SELECT * FROM buses WHERE bus_number = :busNumber LIMIT :limit OFFSET :offset")
    Flux<Bus> findAllByBusNumberPaged(Integer busNumber, long limit, long offset);

    @Query("SELECT COUNT(*) FROM buses WHERE bus_number = :busNumber")
    Mono<Long> countByBusNumber(Integer busNumber);

    // Example: passenger search with paging
    @Query("""
        SELECT * FROM buses
        WHERE passenger_id = :passengerId
          AND (LOWER(license_plate) LIKE LOWER(CONCAT('%', :licensePlate, '%'))
            OR bus_number = :busNumber
            OR LOWER(location_place) LIKE LOWER(CONCAT('%', :place, '%'))
            OR LOWER(route_name) LIKE LOWER(CONCAT('%', :route, '%')))
        LIMIT :limit OFFSET :offset
    """)
    Flux<Bus> findByPassengerAndFiltersPaged(Long passengerId, String licensePlate, Integer busNumber, String place, String route, long limit, long offset);

    @Query("SELECT COUNT(*) FROM buses WHERE passenger_id = :passengerId")
    Mono<Long> countByPassengerId(Long passengerId);
}

