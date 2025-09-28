package com.devdam.server.model;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@ToString(exclude = "user")
@EqualsAndHashCode(exclude = "user")
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "phone_no")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "photo_url", nullable = false)
    private String photoURL;

    @Column(name = "photo_filename", nullable = false)
    private String filename;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    @Column(name = "date_created", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    @Column(name = "last_logged_in")
    @UpdateTimestamp
    private LocalDateTime lastLoggedIn;

    @OneToOne(cascade = {CascadeType.ALL}) // {CascadeType.MERGE}
    private User user;

    // @OneToOne(mappedBy = "user", cascade = {CascadeType.DETACH})
    // protected APIToken apiToken;

    // @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // //@OrderBy("dateCreated DESC")
    // private Set<Notification> notifications;

    // // Updating the notifications set whenever
    // public void addNotification(Notification notification) {
    //     if(notifications == null) notifications = new HashSet<>();

    //     notifications.add(notification);
    // }

    // public void removeNotification(Notification notification) {
    //     if(notifications == null) return;

    //     notifications.remove(notification);
    // }
}
