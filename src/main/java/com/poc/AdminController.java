package com.poc;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/admin")
@RestController
class AdminEndpoint {

	@GetMapping
	@Secured("ROLE_ADMIN")
	Map<String, Object> manage(@AuthenticationPrincipal Principal user) {
		return Collections.singletonMap("user", user.getName());
	}
}
