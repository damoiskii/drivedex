package com.devdam.server.config;

// import java.util.concurrent.Executor;
// import java.util.concurrent.Executors;

// import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
// import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *    This class configures asynchronous task execution in the application.
 *    It enables asynchronous processing and can define custom thread pools for different types of tasks.
 *    The configuration can be customized as needed for specific use cases.
 */
@Configuration
@EnableAsync
public class AsyncConfig {
    // @Bean(name = "scheduledTaskExecutor")
    // public Executor taskExecutor() {
    //     ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    //     executor.setCorePoolSize(5);
    //     executor.setMaxPoolSize(10);
    //     executor.setQueueCapacity(25);
    //     executor.setThreadNamePrefix("Scheduled-");
    //     executor.initialize();
        
    //     return executor;
    // }

    // @Bean
    // public Executor asyncTaskExecutor() {
    //     return Executors.newFixedThreadPool(10); // Customize as needed
    // }

    // @Bean(name = "ioTaskExecutor")
    // public Executor ioTaskExecutor() {
    //     // Different configuration for I/O bound tasks
    //     ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    //     executor.setCorePoolSize(10);
    //     executor.setMaxPoolSize(20);
    //     executor.setQueueCapacity(50);
    //     executor.setThreadNamePrefix("IO-");
    //     executor.initialize();
    //     return executor;
    // }
}