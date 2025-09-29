package com.devdam.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devdam.server.model.Bus;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    Bus findByLicensePlateEqualsIgnoreCase(String licensePlate);
    List<Bus> findAllByBusNumber(Integer busNumber, Sort sort);
    Page<Bus> findAllByBusNumber(Integer busNumber, Pageable pageable);
    // Page<Bus> findAllByPassengerIdEqualsAndBusNumberEquals(Long passengerId, Integer busNumber, Pageable pageable);
    // Page<Bus> findAllByPassengerIdEqualsAndLicensePlateContainsIgnoringCaseOrPassengerIdEqualsAndBusNumberEqualsOrPassengerIdEqualsAndLocationPlaceContainsIgnoringCaseOrPassengerIdEqualsAndRouteNameContainsIgnoringCase(Long passengerId, String licensePlate, Long passengerId2, Integer busNumber, Long passengerId3, String locationPlace, Long passengerId4, String routeName, Pageable pageable);
    // List<Bus> findAllByPassengerIdEquals(Long passengerId, Sort sort);
    // Page<Bus> findAllByPassengerIdEquals(Long passengerId, Pageable pageable);
    Page<Bus> findAllByDriver_Profile_FirstnameContainsIgnoringCaseOrDriver_Profile_LastnameContainsIgnoringCaseOrDriverUsernameContainsIgnoringCaseOrDriverEmailContainsIgnoringCaseOrLicensePlateContainsIgnoringCaseOrBusNumberGreaterThanEqualOrLocationPlaceContainsIgnoringCaseOrRouteNameContainsIgnoringCase(String firstname, String lastname, String username, String email, String licensePlate, Integer busNumber, String place, String route, Pageable pageable);
    Page<Bus> findAllByLicensePlateContainsIgnoringCaseOrBusNumberEqualsOrLocationPlaceContainsIgnoringCaseOrRouteNameContainsIgnoringCase(String licensePlate, Integer busNumber, String place, String route, Pageable pageable);

    List<Bus> findAllByDriverIsNullOrderByBusNumberAsc(); // using for the add employee (driver) form
    List<Bus> findAllByDriverIsNullOrDriverIdEquals(Long driverId); // using for the update employee (driver) form
    // List<Bus> findAllByPassengerId(Long passengerId);

    // Page<Bus> findAllByPassengerId(Long passengerId, Pageable pageable);
    // Page<Bus> findAllByPassengerIdAndLicensePlateContainsIgnoringCaseOrBusNumberEqualsOrLocationPlaceContainsIgnoringCaseOrRouteNameContainsIgnoringCase(Long passenger_id,String licensePlate, Integer busNumber, String place, String route, Pageable pageable);
    
    List<Bus> findAllByLocationIsNull();
    List<Bus> findAllByRouteIsNull();
    // List<Bus> findAllByPassengerIsNull();
    List<Bus> findAllByRouteId(Long routeId);
}