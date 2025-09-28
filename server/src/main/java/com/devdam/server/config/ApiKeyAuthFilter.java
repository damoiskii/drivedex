// package com.devdam.server.config;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// import org.springframework.lang.NonNull;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;

// /**
//  * Filter to authenticate requests using an API key.
//  * This filter checks for a valid API key in the request header.
//  */
// @Component
// public class ApiKeyAuthFilter extends OncePerRequestFilter {
//     @Value("${api.key}")
//     private String apiKey;

//     /**
//      * This method is called for each request to check if the API key is valid.
//      * If the API key is valid, the request is allowed to proceed; otherwise, a 401 Unauthorized response is returned.
//      */
//     @Override
//     protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
//         // Skip API key check for /ping
//         // if ("/ping".equals(request.getRequestURI())) {
//         //     filterChain.doFilter(request, response);
//         //     return;
//         // }
        
//         String requestApiKey = request.getHeader("X-API-KEY");

//         // Check if the API key from the request matches the configured API key
//         if (apiKey != null && apiKey.equals(requestApiKey)) {
//             filterChain.doFilter(request, response);
//         } 
//         else {
//             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//             response.getWriter().write("Unauthorized: Invalid or missing API key");
//         }
//     }
// }
