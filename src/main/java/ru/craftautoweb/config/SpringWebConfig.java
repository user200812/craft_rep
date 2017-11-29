package ru.craftautoweb.config;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.servlet.annotation.WebFilter;

/**
 * Created by Администратор on 06.12.2016.
 */
@EnableWebMvc //<mvc:annotation-driven />
@Configuration
@ComponentScan({
        "ru.craftautoweb.controllers",
        "ru.craftautoweb.validators",
        "ru.craftautoweb.models",
        "ru.craftautoweb.hub",
        "ru.craftautoweb.security",
        "ru.craftautoweb.config"})
@Import({ SpringSecurityConfig.class })
public class SpringWebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/Contents/**")
                .addResourceLocations("/WEB-INF/web/Contents/");
        registry.addResourceHandler("/Scripts/**")
                .addResourceLocations("/WEB-INF/web/Scripts/");
        registry.addResourceHandler("/pics/**")
                .addResourceLocations("/WEB-INF/web/pics/");
        registry.addResourceHandler("/fonts/**")
                .addResourceLocations("/WEB-INF/web/fonts/");
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver
                = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/web/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setFallbackToSystemLocale(true);
        // messageSource.setCacheSeconds(10); //reload messages every 10 seconds
        return messageSource;
    }

}
