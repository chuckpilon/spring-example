package com.pilon.example.item.auth;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

// Commented out so that Spring doesn't pick it up.
// @Configuration()
public class InMemoryUserDetailsManagerInitializer {

    @Bean
    UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager();
	}
	
	@Bean
	@SuppressWarnings("deprecation")
	InitializingBean initializer(UserDetailsManager userDetailsManager) {
		return () -> {
			UserDetails cpilon = User.withDefaultPasswordEncoder().username("cpilon").password("password").roles("USER").build();
			userDetailsManager.createUser(cpilon);
			UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("admin").roles("USER").build();
			userDetailsManager.createUser(admin);
		};
	}

}