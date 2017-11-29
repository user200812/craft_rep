package ru.craftautoweb.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import javax.servlet.ServletContext;

public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    @Override
    protected void afterSpringSecurityFilterChain(ServletContext servletContext) {
        super.afterSpringSecurityFilterChain(servletContext);
        //servletContext.setAttribute("async-supported", true);
    }
    //do nothing
}