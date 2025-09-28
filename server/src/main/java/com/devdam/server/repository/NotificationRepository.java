package com.devdam.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devdam.server.enums.NotificationType;
import com.devdam.server.model.Notification;

import java.util.List;


@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByOpenedIsFalseOrderByDateCreatedDesc();
    int countAllByOpenedIsFalse();

    // Pagination
    // Page<Notification> findAll(Pageable pageable);

    // Filter status
    Page<Notification> findAllByOpenedIsTrue(Pageable pageable);
    Page<Notification> findAllByOpenedIsFalse(Pageable pageable);

    // Filter timeline
    // Page<Notification> findAllByOpenedIsTrue(Pageable pageable);
    // Page<Notification> findAllByOpenedIsFalse(Pageable pageable);

    // Search
    Page<Notification> findAllByUsernameContainsIgnoringCaseOrEmailContainsIgnoringCaseOrMessageContainsIgnoringCaseOrMessageTypeContainsIgnoringCase(String username, String email, String message, NotificationType messageType, Pageable pageable);

    /*Page<Employee> findAllByFirstnameContainsIgnoringCaseOrLastnameContainsIgnoringCaseOrUsernameContainsIgnoringCaseOrEmailContainsIgnoringCase(String firstname, String lastname, String username, String email, Pageable pageable);
    Page<Employee> findAllByIsAdmin(boolean isAdmin, Pageable pageable);
    Page<Employee> findAllByIsDriver(boolean isDriver, Pageable pageable);
    Page<Employee> findAllByIsRegular(boolean isRegular, Pageable pageable);
    Page<Employee> findAllByIsSupervisor(boolean isSupervisor, Pageable pageable);
    Page<Employee> findAllByIsActive(boolean isActive, Pageable pageable);*/
}
