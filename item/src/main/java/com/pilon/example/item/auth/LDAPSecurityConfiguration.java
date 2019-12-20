package com.pilon.example.item.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;

@ConditionalOnProperty(value="web.security.auth-method", havingValue="ldap")
@Configuration
@SuppressWarnings("deprecation")
@EnableWebSecurity
public class LDAPSecurityConfiguration extends ItemServiceWebSecurityConfigurerAdapter {

    @Value("${ldap.user-dn}")
    String userDn;

    @Value("${ldap.password}")
    String password;

    @Value("${ldap.user-dn-patterns}")
    String userDnPatterns;

    @Value("${ldap.group-search-base}")
    String groupSearchBase;

    @Value("${ldap.context.url}")
    String contextURL;

    @Value("${ldap.password-attribute}")
    String passwordAttribute;

    @Override
	protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
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

}