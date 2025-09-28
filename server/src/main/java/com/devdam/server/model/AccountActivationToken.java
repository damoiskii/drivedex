package com.devdam.server.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

@Data
@Builder
@Entity
@Table(name = "account_activation_tokens")
public class AccountActivationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiring", nullable = false)
    @Builder.Default
    private LocalDateTime expiring = LocalDateTime.now().plusHours(1); // Default to 1 hour from creation

    // Registration Info
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    // @Column(name = "firstname")
    // private String firstname;

    // @Column(name = "lastname")
    // private String lastname;
}
