package com.example.hibernate_dao.configuration;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private final static String BY_CITY = "by-city";
    private final static String BY_AGE = "by-age";
    private final static String BY_NAME_AND_SURNAME = "by-nameAndSurname";
    private final static String PERSONS = "/persons/";

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("Ivan").password("{noop}ivan12345").authorities(BY_AGE, BY_NAME_AND_SURNAME)
                .and()
                .withUser("Anna").password("{noop}password").authorities(BY_NAME_AND_SURNAME);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin()
                .and()
                .authorizeRequests().antMatchers(PERSONS + BY_CITY).permitAll()
                .and()
                .authorizeRequests().antMatchers(PERSONS + BY_AGE).hasAuthority(BY_AGE)
                .and()
                .authorizeRequests().antMatchers(PERSONS + BY_NAME_AND_SURNAME).hasAuthority(BY_NAME_AND_SURNAME);
    }

}
