// package com.devdam.server.config;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// import java.util.Arrays;

// /**
//  * CORS configuration to allow Vue.js client to communicate with the Spring Boot server.
//  */
// @Configuration
// public class CorsConfig implements WebMvcConfigurer {
//     @Value("${api.client.url}")
//     private String clientUrl;

//     /**
//      * Configures CORS (Cross-Origin Resource Sharing) for the application.
//      * This method allows requests from the specified client URL to access the API endpoints
//      * under "/api/**" with specified HTTP methods and headers.
//      *
//      * @param registry the CorsRegistry to configure CORS mappings
//      */
//     @SuppressWarnings("null")
//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         registry.addMapping("/api/**")
//                 //.allowedOrigins("http://localhost:5173", "http://localhost:3000", "http://localhost:8080")
//                 .allowedOrigins(clientUrl)
//                 .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                 // .allowedMethods("GET", "POST")
//                 .allowedHeaders("*")
//                 .allowCredentials(true);
//     }

//     /**
//      * Provides a CorsConfigurationSource bean for CORS configuration.
//      * This method sets up the CORS configuration with allowed origins, methods, headers,
//      * and credentials.
//      *
//      * @return a CorsConfigurationSource instance
//      */
//     @Bean
//     public CorsConfigurationSource corsConfigurationSource() {
//         CorsConfiguration configuration = new CorsConfiguration();
//         configuration.setAllowedOriginPatterns(Arrays.asList("*"));
//         configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//         // configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
//         configuration.setAllowedHeaders(Arrays.asList("*"));
//         configuration.setAllowCredentials(true);
        
//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/api/**", configuration);
//         return source;
//     }
// }
