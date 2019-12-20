package com.pilon.example.item.rest;

import java.security.Principal;
import java.util.Map;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PrincipalController {
	@GetMapping(value="/principal")
	public Principal principal(Principal principal) {
		return principal;
	}

	@GetMapping("/oauth2user")
	Map<String, Object> oauth2user(OAuth2AuthenticationToken authenticationToken) {
		return authenticationToken.getPrincipal().getAttributes();
	}
}