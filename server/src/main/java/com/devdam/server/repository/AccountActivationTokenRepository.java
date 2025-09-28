package com.devdam.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devdam.server.model.AccountActivationToken;

import java.util.List;

@Repository
public interface AccountActivationTokenRepository extends JpaRepository<AccountActivationToken, Long> {
    AccountActivationToken findByToken(String token);
    List<AccountActivationToken> findAllByEmailEqualsIgnoreCase(String email);

    void deleteByToken(String token);
    void deleteAllByEmailEqualsIgnoreCase(String email);
}
