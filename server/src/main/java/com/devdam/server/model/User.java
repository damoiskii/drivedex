package com.devdam.server.model;

// import java.util.Set;

// import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.devdam.server.enums.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("users")
public class User {
    @Id
    protected Long id;

    protected String username;
    protected String email;
    protected String password;
    protected Long profileId;

    @Builder.Default
    protected Boolean isActive = false;

    @Builder.Default
    protected Role role = Role.USER;
}
