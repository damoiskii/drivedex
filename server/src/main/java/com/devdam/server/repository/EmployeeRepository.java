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
    Employee findByEmailEqualsIgnoringCaseAndIsAdminIsFalseAndIsDriverIsTrue(String email);

    List<Employee> findByFirstnameEqualsIgnoringCaseAndLastnameEqualsIgnoringCaseOrderByFirstnameAscLastnameAscUsernameAscEmailAsc(String firstname, String lastname);
    List<Employee> findAllByIsAdminIsTrueOrderByFirstnameAscLastnameAscUsernameAscEmailAsc();
    List<Employee> findAllByIsSupervisorIsTrueOrderByFirstnameAscLastnameAscUsernameAscEmailAsc();
    List<Employee> findAllByIsAdminIsFalseAndIsDriverIsTrueOrderByFirstnameAscLastnameAscUsernameAscEmailAsc();
    List<Employee> findAllByIsSuperuserIsTrueOrderByFirstnameAscLastnameAscUsernameAscEmailAsc();

    List<Employee> findAllByIsDriverIsTrue();

    // Pagination
    Page<Employee> findAllByIsAdminIsTrue(Pageable pageable);
    Page<Employee> findAllByIsSupervisorIsTrue(Pageable pageable);
    Page<Employee> findAllByIsAdminIsFalseAndIsDriverIsTrue(Pageable pageable);

    Page<Employee> findAllByFirstnameContainsIgnoringCaseOrLastnameContainsIgnoringCaseOrUsernameContainsIgnoringCaseOrEmailContainsIgnoringCase(String firstname, String lastname, String username, String email, Pageable pageable);
    Page<Employee> findAllByIsAdmin(boolean isAdmin, Pageable pageable);
    Page<Employee> findAllByIsDriver(boolean isDriver, Pageable pageable);
    Page<Employee> findAllByIsRegular(boolean isRegular, Pageable pageable);
    Page<Employee> findAllByIsSupervisor(boolean isSupervisor, Pageable pageable);
    Page<Employee> findAllByIsActive(boolean isActive, Pageable pageable);

    Page<Employee> findAllByRolesIsContaining(Role role, Pageable pageable);
}
