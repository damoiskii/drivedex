// package com.devdam.server.model;

// import jakarta.persistence.*;
// import lombok.*;

// import java.time.LocalDateTime;

// import org.hibernate.annotations.CreationTimestamp;
// import org.springframework.format.annotation.DateTimeFormat;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// @Entity
// @Table(name = "api_tokens")
// public class APIToken {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "id")
//     private Long id;

//     @Column(name = "token", nullable = false, unique = true)
//     private String token;

//     @Temporal(TemporalType.TIMESTAMP)
//     @DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss")
//     @Column(name = "created_at", nullable = false)
//     @CreationTimestamp
//     private LocalDateTime createAt;

//     @Temporal(TemporalType.TIMESTAMP)
//     @DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss")
//     @Column(name = "expiring", nullable = false)
//     @Builder.Default
//     private LocalDateTime expiring = LocalDateTime.now().plusMinutes(15); // expire in 15 minutes

//     @OneToOne(cascade = {CascadeType.DETACH})
//     private User user;
// }