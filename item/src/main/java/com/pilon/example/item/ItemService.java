package com.pilon.example.item;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
// import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
// import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
// import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@Configuration
@EnableJpaAuditing
@ImportResource("classpath:integration-context.xml")
public class ItemService {

    @Bean
    AuditorAware<String> auditor() {
        return new AuditorAware<String>() {

            @Override
            public Optional<String> getCurrentAuditor() {
                SecurityContext context = SecurityContextHolder.getContext();
                Authentication authentication = context.getAuthentication();
                if (authentication == null) {
                    return Optional.empty();
                } else {
                    return Optional.ofNullable(authentication.getName());
                }
            }

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ItemService.class, args);
    }

}
