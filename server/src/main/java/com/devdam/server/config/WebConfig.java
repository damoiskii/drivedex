// package com.devdam.server.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.DispatcherServlet;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// /**
//  * Configuration class for Spring MVC.
//  * This class is responsible for configuring the DispatcherServlet to handle requests
//  * and to throw exceptions if no handler is found, resulting in a 404 error.
//  */
// @Configuration
// public class WebConfig implements WebMvcConfigurer {

//     /**
//      *  Creates a DispatcherServlet bean.
//      *  This method configures the DispatcherServlet to throw an exception if no handler is found,
//      *  which will result in a 404 error being returned to the client.
//      *  The DispatcherServlet is the central dispatcher for HTTP request handlers/controllers
//      *  in a Spring MVC application.
//      * @return  a configured DispatcherServlet instance
//      * @deprecated This method uses the deprecated `setThrowExceptionIfNoHandlerFound` method.
//      *             Use `setThrowExceptionIfNoHandlerFound` on the `ServletRegistrationBean` instead.
//      *             This method is retained for compatibility with older versions of Spring.
//      *             It is recommended to use the newer approach for configuring the DispatcherServlet.
//      */
//     @Bean
//     public DispatcherServlet dispatcherServlet() {
//         DispatcherServlet servlet = new DispatcherServlet();
//         servlet.setThrowExceptionIfNoHandlerFound(true); // Force 404 to be thrown
//         return servlet;
//     }

//     /**
//      * Configures CORS (Cross-Origin Resource Sharing) for the application.
//      * This method allows requests from any origin to access the API endpoints
//      * under "/api/v1/**" with specified HTTP methods and headers.
//      *
//      * @param registry the CorsRegistry to configure CORS mappings
//      */
//     @SuppressWarnings("null")
//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         registry.addMapping("/api/v1/**")
//                 //.allowedOrigins("*") // or restrict to "http://localhost" etc.
//                 .allowedOriginPatterns("*")
//                 .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
//                 .allowedHeaders("*")
//                 .allowCredentials(false);
//     }
// }
