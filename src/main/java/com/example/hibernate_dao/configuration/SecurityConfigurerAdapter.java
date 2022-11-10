package com.example.hibernate_dao.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
@Configuration
public class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private final static String BY_CITY = "BY-CITY";
    private final static String BY_AGE = "BY-AGE";
    private final static String BY_NAME_AND_SURNAME = "BY-NAME-AND-SURNAME";

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("Ivan").password(encoder().encode("ivan12345")).roles(BY_AGE, BY_NAME_AND_SURNAME)
                .and()
                .withUser("Anna").password(encoder().encode("password")).roles(BY_NAME_AND_SURNAME);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin()
                .and()
                .authorizeRequests().antMatchers("/persons/" + BY_CITY).permitAll();
    }
}
