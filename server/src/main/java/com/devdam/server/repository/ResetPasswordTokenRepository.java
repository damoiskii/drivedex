package com.devdam.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devdam.server.model.ResetPasswordToken;

import java.util.List;

@Repository
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Long> {
    ResetPasswordToken findByToken(String token);
    List<ResetPasswordToken> findAllByEmailEqualsIgnoreCase(String email);

    void deleteByToken(String token);
    void deleteAllByEmailEqualsIgnoreCase(String email);
}
