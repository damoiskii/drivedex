package com.devdam.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {
    @Value("${spring.mail.username}")
    private String USERNAME;

    @Value("${spring.mail.password}")
    private String PASSWORD;

    @Value("${spring.mail.host}")
    private String HOST;

    @Value("${spring.mail.port}")
    private Integer PORT;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private Boolean SMTP_AUTH;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private Boolean ENABLE_TLS;

    @Value("${spring.mail.properties.transport.protocol}")
    private String TRANSPORT_PROTOCOL;

    @Value("${spring.mail.properties.mail.debug}")
    private Boolean MAIL_DEBUG;


    @Bean
    JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(HOST);
        mailSender.setPort(PORT);

        mailSender.setUsername(USERNAME);
        mailSender.setPassword(PASSWORD);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", TRANSPORT_PROTOCOL);
        props.put("mail.smtp.auth", SMTP_AUTH);
        props.put("mail.smtp.starttls.enable", ENABLE_TLS);
        props.put("mail.debug", MAIL_DEBUG);

        return mailSender;
    }
}
