package com.pilon.example.item.rest;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PrincipalController {
	@GetMapping(value="/principal")
	public Principal principal(Principal principal) {
		return principal;
	}
}