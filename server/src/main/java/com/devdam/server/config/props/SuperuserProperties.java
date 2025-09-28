package com.devdam.server.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "superuser")
public class SuperuserProperties {
    private String username;
    private String email;
    private String password;
}
