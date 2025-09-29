package com.devdam.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devdam.server.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Page<Location> findAllByBus_Driver_Profile_FirstnameContainsIgnoringCaseOrBus_Driver_Profile_LastnameContainsIgnoringCaseOrBusDriverUsernameContainsIgnoringCaseOrBusDriverEmailContainsIgnoringCaseOrBusBusNumberContainsIgnoringCaseOrBusLicensePlateContainsIgnoringCaseOrPlaceContainsIgnoringCase(String firstname, String lastname, String username, String email, String busNumber, String licensePlate, String place, Pageable pageable);
}
