// package com.devdam.server.config;


// import com.djjsa.web_app.config.handlers.CustomLogoutSuccessHandler;
// import com.djjsa.web_app.service.UserService;
// import com.djjsa.web_app.service.UserServiceImpl;
// import com.djjsa.web_app.utils.BaseUtils;
// import lombok.RequiredArgsConstructor;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;


// @Configuration
// @EnableWebSecurity
// @RequiredArgsConstructor
// public class SecurityConfig {
//     private final UserServiceImpl userService;

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//         // Setup access restrictions
//         http.authorizeHttpRequests(auth -> {
//             // Basic endpoints
//             auth.requestMatchers("/").permitAll();
//             auth.requestMatchers("/coming-soon").permitAll();
//             auth.requestMatchers("/under-maintenance").permitAll();
//             auth.requestMatchers("/privacy-policy", "/privacy-policy/").permitAll();
//             auth.requestMatchers("/session-expired", "/session-expired/").permitAll();

//             // Authentication endpoints
//             auth.requestMatchers("/auth/**").permitAll();

//             // Error endpoint
//             auth.requestMatchers("/error").permitAll();

//             // Ajax request endpoints
//             auth.requestMatchers(HttpMethod.GET, "/ajax/**").permitAll();

//             // Static files end points
//             auth.requestMatchers("/assets/**").permitAll();
//             auth.requestMatchers("/fonts/**").permitAll();
//             auth.requestMatchers("/js/**").permitAll();
//             auth.requestMatchers("/libs/**").permitAll();
//             auth.requestMatchers("/scss/**").permitAll();
//             auth.requestMatchers("/tasks/**").permitAll();


//             /* Employee Dashboard */
//             // Notifications
//             auth.requestMatchers(HttpMethod.GET, "/admin/dashboard/notifications/**", "/admin/dashboard/search-notifications", "/admin/dashboard/filter-notifications").hasAnyRole(BaseUtils.HIGH_LEVEL_USER_ROLES);


//             // Adding
//             auth.requestMatchers(HttpMethod.GET, "/admin/dashboard/employees/add", "/admin/dashboard/passengers/add", "/admin/dashboard/locations/add").hasAnyRole(BaseUtils.HIGH_LEVEL_USER_ROLES);
//             auth.requestMatchers(HttpMethod.POST, "/admin/dashboard/employee/save", "/admin/dashboard/passenger/save", "/admin/dashboard/location/save").hasAnyRole(BaseUtils.HIGH_LEVEL_USER_ROLES);

//             auth.requestMatchers(HttpMethod.GET, "/admin/dashboard/roles/add").hasAnyRole(BaseUtils.SUPER_USER_ROLES);
//             auth.requestMatchers(HttpMethod.POST, "/admin/dashboard/role/save").hasAnyRole(BaseUtils.SUPER_USER_ROLES);

//             // Updating
//             auth.requestMatchers(HttpMethod.GET, "/admin/dashboard/employees/edit/**", "/admin/dashboard/locations/edit/**").hasAnyRole(BaseUtils.HIGH_LEVEL_USER_ROLES);
//             auth.requestMatchers(HttpMethod.PUT, "/admin/dashboard/employee/update", "/admin/dashboard/location/update").hasAnyRole(BaseUtils.HIGH_LEVEL_USER_ROLES);

//             auth.requestMatchers(HttpMethod.GET, "/admin/dashboard/roles/edit/**").hasAnyRole(BaseUtils.SUPER_USER_ROLES);
//             auth.requestMatchers(HttpMethod.PUT, "/admin/dashboard/role/update").hasAnyRole(BaseUtils.SUPER_USER_ROLES);

//             // Deleting
//             auth.requestMatchers(HttpMethod.DELETE, "/admin/dashboard/employees/delete/**", "/admin/dashboard/passengers/delete/**", "/admin/dashboard/buses/delete/**", "/admin/dashboard/roles/delete/**", "/admin/dashboard/locations/delete/**", "/admin/dashboard/notifications/delete/**", "/admin/dashboard/notifications/delete-all/").hasAnyRole(BaseUtils.HIGH_LEVEL_USER_ROLES);

//             // Everything else...
//             auth.requestMatchers("/admin/dashboard/**").hasAnyRole("ADMIN", "ADMINISTRATOR", "SUPERUSER", "EMPLOYEE");


//             /* Passenger Dashboard */
//             auth.requestMatchers("/passenger/dashboard/**").hasAnyRole("PASSENGER");

//             auth.anyRequest().authenticated(); // must be logged in for all requests...
//         });

//         // Setup custom login page
//         http.formLogin(form -> {
//             form.loginPage("/auth/login/"); // my custom login page
//             form.loginProcessingUrl("/authenticateTheUser"); // use spring boot to process the login submissions [got "/authenticateTheUser" for free from spring boot]
//             //form.successHandler(loginSuccessHandler());
//             form.defaultSuccessUrl("/redirecting", true);
//             form.permitAll();
//         });

//         // Setup logout
//         http.logout(logout -> {
//             logout.logoutSuccessHandler(logoutSuccessHandler());
//             logout.invalidateHttpSession(true);
//             logout.deleteCookies("JSESSIONID");
//             logout.logoutSuccessUrl("/auth/login/?logout");
//             logout.permitAll();
//         });

//         // Setup session management
//         http.sessionManagement()
//                 .invalidSessionUrl("/session-expired")
//                 .maximumSessions(1) // Allow only one session per user
//                 .maxSessionsPreventsLogin(false) // Allow multiple logins from same user
//                 .expiredUrl("/session-expired")
//                 .and()
//                 .sessionFixation().migrateSession()
//                 .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                 .invalidSessionUrl("/session-expired")
//                 .maximumSessions(1) // Allow only one session per user
//                 .maxSessionsPreventsLogin(false); // Allow multiple logins from same user

//         return http.build();
//     }

//     // Telling Spring to look for custom tables in the database
//     @Bean
//     public DaoAuthenticationProvider daoAuthenticationProvider(UserService userService){
//         DaoAuthenticationProvider auth = new DaoAuthenticationProvider();

//         auth.setUserDetailsService(userService); // set the custom details service
//         auth.setPasswordEncoder(passwordEncoder()); // set the password encoder

//         return auth;
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder(){
//         return new BCryptPasswordEncoder();
//     }

//     // Customized logout handler
//     @Bean
//     public CustomLogoutSuccessHandler logoutSuccessHandler() {
//         return new CustomLogoutSuccessHandler(userService);
//     }

//     // Customized login handler
//     /*@Bean
//     public CustomAuthenticationSuccessHandler loginSuccessHandler() {
//         return new CustomAuthenticationSuccessHandler(userService);
//     }*/
// }
