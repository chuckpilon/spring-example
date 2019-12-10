package com.pilon.example.item.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
class LoginController {
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	String login() {
		return "login";
	}

	@RequestMapping(value = "logout-success", method=RequestMethod.GET)
	String logoutSuccess() {
		return "logout";
	}

}
