package com.devdam.server.config;

import com.opencagedata.jopencage.JOpenCageGeocoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JOpenCageConfig {
    @Value("${OPENCAGE_API_KEY}")
    private String OPENCAGE_API_KEY;

    @Bean
    JOpenCageGeocoder getJOpenCageGeocoder(){
        return new JOpenCageGeocoder(OPENCAGE_API_KEY);
    }
}
