package com.devdam.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
@Table("profiles")
public class Profile {
    @Id
    private Long id;
    private String photoURL;
    private String filename;

    @DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime dateCreated;

    @DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime lastLoggedIn;

    private Long userId;

    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String address;

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