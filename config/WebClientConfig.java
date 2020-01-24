package com.pilon.example.item.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientConfig {

    /**
     * Create a webClient that can be used to exchange OAuth2 credentials with another application.
     */
    @Bean
    WebClient webClient(ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2ExchangeFilterFunction = new ServletOAuth2AuthorizedClientExchangeFilterFunction(
                clientRegistrationRepository, authorizedClientRepository);
        oauth2ExchangeFilterFunction.setDefaultOAuth2AuthorizedClient(true);
        return WebClient.builder().apply(oauth2ExchangeFilterFunction.oauth2Configuration()).build();
    }

}