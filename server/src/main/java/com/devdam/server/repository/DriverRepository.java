package com.devdam.server.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devdam.server.model.Driver;
// import com.devdam.server.model.Role;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Driver findByUsernameEqualsIgnoringCase(String username);
    Driver findByEmailEqualsIgnoringCase(String email);
    Driver findByEmailEqualsIgnoringCaseOrUsernameEqualsIgnoringCase(String email, String username);

    List<Driver> findByProfile_FirstnameEqualsIgnoringCaseAndProfile_LastnameEqualsIgnoringCaseOrderByProfile_FirstnameAscProfile_LastnameAscUsernameAscEmailAsc(String firstname, String lastname);
    // List<Driver> findAllByIsAdminIsTrueOrderByProfile_FirstnameAscProfile_LastnameAscUsernameAscEmailAsc();
    // List<Driver> findAllByIsSupervisorIsTrueOrderByProfile_FirstnameAscProfile_LastnameAscUsernameAscEmailAsc();
    // List<Driver> findAllByIsAdminIsFalseAndIsDriverIsTrueOrderByProfile_FirstnameAscProfile_LastnameAscUsernameAscEmailAsc();
    // List<Driver> findAllByIsSuperuserIsTrueOrderByProfile_FirstnameAscProfile_LastnameAscUsernameAscEmailAsc();

    // List<Driver> findAllByIsDriverIsTrue();

    // Pagination
    // Page<Driver> findAllByIsAdminIsTrue(Pageable pageable);
    // Page<Driver> findAllByIsSupervisorIsTrue(Pageable pageable);
    // Page<Driver> findAllByIsAdminIsFalseAndIsDriverIsTrue(Pageable pageable);

    Page<Driver> findAllByProfile_FirstnameContainsIgnoringCaseOrProfile_LastnameContainsIgnoringCaseOrUsernameContainsIgnoringCaseOrEmailContainsIgnoringCase(String firstname, String lastname, String username, String email, Pageable pageable);
    // Page<Driver> findAllByIsAdmin(boolean isAdmin, Pageable pageable);
    // Page<Driver> findAllByIsDriver(boolean isDriver, Pageable pageable);
    // Page<Driver> findAllByIsRegular(boolean isRegular, Pageable pageable);
    // Page<Driver> findAllByIsSupervisor(boolean isSupervisor, Pageable pageable);
    Page<Driver> findAllByIsActive(boolean isActive, Pageable pageable);

    // Page<Driver> findAllByRolesIsContaining(Role role, Pageable pageable);
}
