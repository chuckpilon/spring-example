package com.pilon.example.item.auth;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

// Disabled to omit JDBC auth
// @Configuration
public class JDBCUserDetailsManagerInitializer {

    @Bean
    UserDetailsManager userDetailsService(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(dataSource);
		return jdbcUserDetailsManager;
	}
	
	// @Bean
	// @SuppressWarnings("deprecation")
	// InitializingBean initializer(UserDetailsManager userDetailsManager) {
	// 	return () -> {
	// 		UserDetails cpilon = User.withDefaultPasswordEncoder().username("cpilon").password("password").roles("USER").build();
	// 		userDetailsManager.createUser(cpilon);
	// 		UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("admin").roles("USER").build();
	// 		userDetailsManager.createUser(admin);
	// 	};
	// }

}