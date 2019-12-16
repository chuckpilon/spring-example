package com.pilon.example.item.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;

@Configuration
@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${web.security.authType}")
    String authType;

    @Value("${ldap.userDn}")
    String userDn;

    @Value("${ldap.password}")
    String password;

    @Value("${ldap.userDnPatterns}")
    String userDnPatterns;

    @Value("${ldap.groupSearchBase}")
    String groupSearchBase;

    @Value("${ldap.context.url}")
    String contextURL;

    @Value("${ldap.passwordAttribute}")
    String passwordAttribute;

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
        switch (authType) {
            case "basic":
                http.httpBasic();
                break;

            case "form":
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
        }
        
        // @formatter:on
    }

    /**
     * This needs to be a bean so that the context is used for all endpoints.
     * 
     * @return
     */
    @Bean
    public BaseLdapPathContextSource contextSource() {
        DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource(contextURL);
        contextSource.setUserDn(userDn);
        contextSource.setPassword(password);
        contextSource.afterPropertiesSet();

        return contextSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // @formatter:off
        auth.ldapAuthentication()
            .contextSource(contextSource())
            .userDnPatterns(userDnPatterns)
            .ldapAuthoritiesPopulator(new DefaultLdapAuthoritiesPopulator(contextSource(), groupSearchBase))
            .passwordCompare()
                .passwordAttribute(passwordAttribute)
                .passwordEncoder(new LdapShaPasswordEncoder());
        // @formatter:on

    }

    // @Bean
    // UserDetailsManager userDetailsService(DataSource dataSource) {
	// 	JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
	// 	jdbcUserDetailsManager.setDataSource(dataSource);
	// 	return jdbcUserDetailsManager;
	// }
	
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