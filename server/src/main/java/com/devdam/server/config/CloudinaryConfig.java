package com.devdam.server.config;

import com.cloudinary.Cloudinary;
import com.devdam.server.config.props.CloudinaryProperties;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(CloudinaryProperties.class)
public class CloudinaryConfig {
    private final CloudinaryProperties cloudinaryProperties;

    @Bean
    Cloudinary getCloudinaryInstance(){
        Map<String, Object> config = new HashMap<>();
        config.put("cloud_name", cloudinaryProperties.getCloudName());
        config.put("api_key", cloudinaryProperties.getApiKey());
        config.put("api_secret", cloudinaryProperties.getApiSecret());
        config.put("secure", true);

        return new Cloudinary(config);
    }
}
