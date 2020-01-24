package com.pilon.example.item.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class ItemServiceWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Value("${web.security.auth-type}")
    String authType;

    @Override
	protected void configure(HttpSecurity http) throws Exception {

        // @formatter:off

        // NOTE: Wouldn't normally need or want this.
		http
            .csrf()
                .disable();

        // Allow unauthenticated requests to /actuator/health.
        http
            .authorizeRequests()
                .requestMatchers(EndpointRequest.to(HealthEndpoint.class))
                    .permitAll();
    
        // Require manager role for principal
    	http
            .authorizeRequests()
            .mvcMatchers("/principal")
                .hasAnyAuthority("ROLE_MANAGERS");
        
        // Lock down all other requests
        http
            .authorizeRequests()
            .anyRequest()
                .authenticated();

        // Would be better to control the auth type by spoecifying a class in the properties file.                

        // Set the authentication type
        switch (AuthType.valueOf(authType.toUpperCase())) {
            case NONE:
                break;

            case BASIC:
                http.httpBasic();
                break;

            case FORM:
                // Use a form login
                http
                    .formLogin()
                        .loginPage("/login")
                        .permitAll();

                // Use a form logout
                http
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/logout-success")
                        .permitAll();
                break;
            
            case OAUTH2:
                // Not sure this is needed or desired.

                // Use a form login
                http
                    .formLogin()
                        .permitAll();

                // Use a form logout
                http
                    .logout()
                        .logoutUrl("/logout")
                        .permitAll();
                break;

            default:
                break;
        }
        
        // @formatter:on
    }
}