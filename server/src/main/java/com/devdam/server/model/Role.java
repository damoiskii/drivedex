package com.devdam.server.model;

// import java.beans.Transient;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "users")
@EqualsAndHashCode(exclude = "users")
@Builder
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "slug", nullable = false, unique = true)
    private String slug;

    @Column(name = "x_read_permission")
    @Builder.Default
    private Boolean read = false;

    @Column(name = "x_write_permission")
    @Builder.Default
    private Boolean write = false;

    @Column(name = "x_execute_permission")
    @Builder.Default
    private Boolean execute = false;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<User> users = new HashSet<>();

    /*@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @OrderBy("name ASC")
    private Set<Action> actions;*/

    // @Transient
    // public String getDisplayName() {
    //     return this.name + " (" + this.slug + ")";
    // }

    // Can use transient to get count of users with this role
    // @Transient
    // public int getUserCount() {
    //     return this.users != null ? this.users.size() : 0;
    // }
}
