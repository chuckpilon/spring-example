package com.pilon.example.item.auth;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@ConditionalOnProperty(value = "web.security.auth-method", havingValue = "oauth2")
@Configuration
@EnableWebSecurity
public class OAuth2SecurityConfiguration extends ItemServiceWebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

}