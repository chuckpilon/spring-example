package com.pilon.example.item.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;

@Configuration
@SuppressWarnings("deprecation")
@EnableWebSecurity
public class LDAPSecurityConfiguration extends WebSecurityConfigurerAdapter {

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

        // NOTE: Wouldn't normally need or want this.
		http
			.csrf().disable();

        // Allow onky managers to access /principal, allow unauthentiocated requests to /actuator/health.
        // amd require authentication for everything else.
		http
            .authorizeRequests()
            .mvcMatchers("/principal")
                .hasAnyAuthority("ROLE_MANAGERS")
            .mvcMatchers("/actuator/health")
                .permitAll()
            .anyRequest()
                .authenticated();

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

    }

    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(contextURL);
        return contextSource;
    }    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        LdapAuthoritiesPopulator populator = new DefaultLdapAuthoritiesPopulator(contextSource(), groupSearchBase);

        // @formatter:off
        auth.ldapAuthentication()
            .userDnPatterns(userDnPatterns)
            .groupSearchBase(groupSearchBase)
            .ldapAuthoritiesPopulator(populator)
            .contextSource(contextSource())
            .passwordCompare()
                .passwordAttribute(passwordAttribute)
                .passwordEncoder(new LdapShaPasswordEncoder());
        // @formatter:on

    }
}