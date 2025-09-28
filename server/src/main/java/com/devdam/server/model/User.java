package com.devdam.server.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(exclude = "roles")
@EqualsAndHashCode(exclude = "roles")
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "username", nullable = false, unique = true)
    protected String username;

    @Column(name = "email", nullable = false, unique = true)
    protected String email;

    @Column(name = "password", nullable = false)
    protected String password;

    @Column(name = "is_active")
    @Builder.Default
    protected Boolean isActive = false;

    @Column(name = "is_staff")
    @Builder.Default
    protected Boolean isStaff = false;

    @Column(name = "is_superuser")
    @Builder.Default
    protected Boolean isSuperuser = false;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL})
    protected Profile profile;

    @ManyToMany(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            }
    )
    @OrderBy("name ASC")
    @Builder.Default
    protected Set<Role> roles = new HashSet<>();
}
