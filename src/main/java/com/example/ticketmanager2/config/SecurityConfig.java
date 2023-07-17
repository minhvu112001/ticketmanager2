package com.example.ticketmanager2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("securityDataSource")
    private DataSource securityDataSource;


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(securityDataSource);
    }


    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .dispatcherTypeMatchers(HttpMethod.valueOf("/tickets/showForm*")).hasRole( "ADMIN")
                .dispatcherTypeMatchers(HttpMethod.valueOf("/tickets/save*")).hasRole( "ADMIN")
                .dispatcherTypeMatchers(HttpMethod.valueOf("/tickets/delete")).hasRole("ADMIN")
                .dispatcherTypeMatchers(HttpMethod.valueOf("/tickets/**")).hasAnyRole("USER", "ADMIN")
                .dispatcherTypeMatchers(HttpMethod.valueOf("/resources/**")).permitAll()
                .and()
                .formLogin()
                .loginPage("/showMyLoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }
}