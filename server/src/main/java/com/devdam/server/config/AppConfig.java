package com.devdam.server.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.devdam.server.config.props.SuperuserProperties;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({SuperuserProperties.class})
public class AppConfig {
    
}
