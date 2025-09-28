// package com.devdam.server.config;

// import org.springframework.boot.web.servlet.FilterRegistrationBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// /**
//  * Configuration class for registering the ApiKeyAuthFilter.
//  * This filter checks for a valid API key in the request header.
//  */
// @Configuration
// public class ApiKeyFilterConfig {
//     /**
//      * Registers the ApiKeyAuthFilter to intercept requests to the API.
//      * The filter checks for a valid API key in the request header.
//      *
//      * @param filter the ApiKeyAuthFilter instance
//      * @return FilterRegistrationBean configured with the ApiKeyAuthFilter
//      */
//     @Bean
//     public FilterRegistrationBean<ApiKeyAuthFilter> apiKeyFilter(ApiKeyAuthFilter filter) {
//         FilterRegistrationBean<ApiKeyAuthFilter> registrationBean = new FilterRegistrationBean<>();
//         registrationBean.setFilter(filter);
//         registrationBean.addUrlPatterns("/api/v1/*"); // Adjust this pattern to match your API path
//         registrationBean.setOrder(1);

//         return registrationBean;
//     }
// }
