package com.pilon.example.item.auth;

// import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

// Commented out so that Spring doesn't pick it up.
// @Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();

        http.authorizeRequests().anyRequest().authenticated();
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        // @formatter:off
        auth.ldapAuthentication()
            .userDnPatterns("uid={0},ou=people")
            .groupSearchBase("ou=groups")
            .contextSource()
                .url("ldap://127.0.0.1:8389/dc=springframework,dc=org")
            .and()
            .passwordCompare()
                .passwordAttribute("userPassword")
                .passwordEncoder(new LdapShaPasswordEncoder());
        // @formatter:on

    }
}