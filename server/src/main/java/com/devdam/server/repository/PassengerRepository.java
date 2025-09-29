package com.devdam.server.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devdam.server.model.Passenger;
import com.devdam.server.model.Role;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Passenger findByUsernameEqualsIgnoringCase(String username);
    Passenger findByEmailEqualsIgnoringCase(String email);

    List<Passenger> findByProfile_FirstnameEqualsIgnoringCaseAndProfile_LastnameEqualsIgnoringCase(String firstname, String lastname);

    Page<Passenger> findAllByProfile_FirstnameContainsIgnoringCaseOrProfile_LastnameContainsIgnoringCaseOrUsernameContainsIgnoringCaseOrEmailContainsIgnoringCase(String firstname, String lastname, String username, String email, Pageable pageable);
    // Page<Passenger> findAllByIsStaff(boolean isStaff, Pageable pageable);
    Page<Passenger> findAllByIsActive(boolean isActive, Pageable pageable);

    Page<Passenger> findAllByRolesIsContaining(Role role, Pageable pageable);
}
