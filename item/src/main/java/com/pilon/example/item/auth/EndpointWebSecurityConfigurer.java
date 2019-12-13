package com.pilon.example.item.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Order(30)
public class EndpointWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Allow only managers to access /principal and require authentication for
        // everything else.

        // @formatter:off
		http
            .authorizeRequests()
            .mvcMatchers("/principal")
                .hasAnyAuthority("ROLE_MANAGERS")
            .anyRequest()
                .authenticated();
        // @formatter:on
    }
}