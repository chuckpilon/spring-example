package com.pilon.example.item.rest;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PrincipalController {
	@GetMapping(value="/principal")
	public String principal(Principal principal) {
		return principal.toString();
	}
	
	@GetMapping(value="/authentication")
	public String authentication(Authentication authentication) {
        return authentication.toString();
	}
}