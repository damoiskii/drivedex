package com.devdam.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devdam.server.model.Employee;
import com.devdam.server.model.Role;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUsernameEqualsIgnoringCase(String username);
    Employee findByEmailEqualsIgnoringCase(String email);
    Employee findByEmailEqualsIgnoringCaseOrUsernameEqualsIgnoringCase(String email, String username);

    List<Employee> findByProfile_FirstnameEqualsIgnoringCaseAndProfile_LastnameEqualsIgnoringCaseOrderByProfile_FirstnameAscProfile_LastnameAscUsernameAscEmailAsc(String firstname, String lastname);
    // List<Employee> findAllByIsAdminIsTrueOrderByProfile_FirstnameAscProfile_LastnameAscUsernameAscEmailAsc();
    // List<Employee> findAllByIsSupervisorIsTrueOrderByProfile_FirstnameAscProfile_LastnameAscUsernameAscEmailAsc();
    // List<Employee> findAllByIsAdminIsFalseAndIsDriverIsTrueOrderByProfile_FirstnameAscProfile_LastnameAscUsernameAscEmailAsc();
    // List<Employee> findAllByIsSuperuserIsTrueOrderByProfile_FirstnameAscProfile_LastnameAscUsernameAscEmailAsc();

    // List<Employee> findAllByIsDriverIsTrue();

    // Pagination
    // Page<Employee> findAllByIsAdminIsTrue(Pageable pageable);
    // Page<Employee> findAllByIsSupervisorIsTrue(Pageable pageable);
    // Page<Employee> findAllByIsAdminIsFalseAndIsDriverIsTrue(Pageable pageable);

    Page<Employee> findAllByProfile_FirstnameContainsIgnoringCaseOrProfile_LastnameContainsIgnoringCaseOrUsernameContainsIgnoringCaseOrEmailContainsIgnoringCase(String firstname, String lastname, String username, String email, Pageable pageable);
    // Page<Employee> findAllByIsAdmin(boolean isAdmin, Pageable pageable);
    // Page<Employee> findAllByIsDriver(boolean isDriver, Pageable pageable);
    Page<Employee> findAllByIsRegular(boolean isRegular, Pageable pageable);
    // Page<Employee> findAllByIsSupervisor(boolean isSupervisor, Pageable pageable);
    Page<Employee> findAllByIsActive(boolean isActive, Pageable pageable);

    Page<Employee> findAllByRolesIsContaining(Role role, Pageable pageable);
}
