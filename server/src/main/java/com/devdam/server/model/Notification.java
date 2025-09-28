package com.devdam.server.model;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.devdam.server.enums.NotificationType;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "message", length = 10000, nullable = false)
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name = "opened")
    @Builder.Default
    private Boolean opened = false;

    @Column(name = "message_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private NotificationType messageType = NotificationType.INFO;
}
