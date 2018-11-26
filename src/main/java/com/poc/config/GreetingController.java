package com.poc.config;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Secured("ROLE_USER")
@RequestMapping("/api/greet")
@RestController
class GreetingEndpoint {

	@GetMapping
	Map<String, Object> greet(@AuthenticationPrincipal Principal user) {

		Map<String, Object> map = new HashMap<>();
		map.put("user", user.getName());
		return map;
	}
}