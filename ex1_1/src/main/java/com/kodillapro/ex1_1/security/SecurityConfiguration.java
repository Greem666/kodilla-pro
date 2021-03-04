package com.kodillapro.ex1_1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user_R1").password(passwordEncoder.encode("password")).roles("R1");
        auth.inMemoryAuthentication()
                .withUser("user_R2").password(passwordEncoder.encode("password")).roles("R2");
        auth.inMemoryAuthentication()
                .withUser("user_R3").password(passwordEncoder.encode("password")).roles("R3");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/methods/m1")
                .hasAnyRole("R1", "R2", "R3")
                .mvcMatchers("/methods/m2")
                .hasAnyRole("R2", "R3")
                .mvcMatchers("/methods/m3")
                .hasAnyRole("R3")
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .httpBasic();
    }
}
