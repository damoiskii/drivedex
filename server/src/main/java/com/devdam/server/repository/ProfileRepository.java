package com.devdam.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devdam.server.model.Profile;
import com.devdam.server.model.User;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByUserId(Long userId);
    Profile findByUser(User user);
}
