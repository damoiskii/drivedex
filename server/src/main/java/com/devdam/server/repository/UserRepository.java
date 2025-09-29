package com.devdam.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devdam.server.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameEqualsIgnoringCase(String username);
    User findByEmailEqualsIgnoringCase(String email);

    List<User> findByProfile_FirstnameEqualsIgnoringCaseAndProfile_LastnameEqualsIgnoringCase(String firstname, String lastname);
    List<User> findAllByIsStaffIsTrue();
    List<User> findAllByIsStaffIsFalse();
    List<User> findAllByIsActiveIsTrue();
    List<User> findAllByIsActiveIsFalse();
    List<User> findAllByIsActiveIsTrueAndIsStaffIsFalse(); // Get all online passengers

    Long countByIsSuperuserIsTrue();
}
