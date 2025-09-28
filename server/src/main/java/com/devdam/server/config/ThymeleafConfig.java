package com.devdam.server.config;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

/**
 *  Configuration class for Thymeleaf template engine.
 *  This class is responsible for setting up the Thymeleaf template resolver, template engine,
 *  and view resolver to render HTML templates in a Spring application.
 *  It configures the template location, suffix, character encoding, and additional dialects
 *  such as Layout Dialect and Spring Security Dialect.
 *  The ThymeleafViewResolver is used to resolve views based on the templates defined in the application.
 *  This configuration allows the application to render dynamic HTML content using Thymeleaf templates.
 */
@Configuration
public class ThymeleafConfig {
    /**
     *  Creates a SpringResourceTemplateResolver bean.
     *  This method configures the template resolver to look for templates in the classpath under
     *  the "templates" directory with a ".html" suffix.
     *  It sets the template mode to HTML and specifies the character encoding as UTF-8.
     *  The template resolver is used by the Thymeleaf template engine to locate and process templates.
     * @return  a configured SpringResourceTemplateResolver instance
     */
    @Bean
    SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    /**
     *  Creates a SpringTemplateEngine bean.
     *  This method configures the template engine to use the template resolver defined above.
     *  It adds additional dialects such as Layout Dialect for layout management and Spring Security Dialect
     *  for integrating Spring Security features into Thymeleaf templates.
     *  The template engine is responsible for processing the templates and rendering them as HTML views.
     * @return   a configured SpringTemplateEngine instance
     */
    @Bean
    SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        engine.addDialect(new LayoutDialect()); // If using Thymeleaf Layout Dialect
        engine.addDialect(new SpringSecurityDialect()); // If using Spring Security Dialect
        return engine;
    }

    /**
     *  Creates a ThymeleafViewResolver bean.
     *  This method configures the view resolver to use the template engine defined above.
     *  It sets the character encoding to UTF-8, ensuring that the rendered views are properly encoded.
     *  The view resolver is responsible for resolving views based on the templates processed by the template engine.
     * @return    a configured ThymeleafViewResolver instance
     */
    @Bean
    ViewResolver viewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }
}
