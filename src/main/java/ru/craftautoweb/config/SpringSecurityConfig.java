package ru.craftautoweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.craftautoweb.security.CraftAuthenticationProvider;


@Configuration
@EnableWebSecurity
//    @WebFilter(asyncSupported = true, urlPatterns = {"/*"})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CraftAuthenticationProvider craftAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);


        http.authorizeRequests()
                .and()
                .formLogin().loginPage("/Login").permitAll().usernameParameter("j_username")
                .passwordParameter("j_password").loginProcessingUrl("/j_spring_security_check")
                .failureUrl("/Login?error=true")
                .and()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/Directories/Users/**").access("hasRole('ROLE_ADM')")
                .antMatchers("/Directories/**", "/Orders/**", "/Bills/**", "/About/**").authenticated()
                .and()
                .logout().logoutUrl("/Logout").logoutSuccessUrl("/")
                .and()
                .csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(craftAuthenticationProvider);
    }

}
