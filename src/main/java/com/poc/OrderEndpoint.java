package com.poc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Secured("ROLE_USER")
@RequestMapping("/api/order")
@RestController
class OrderEndpoint {

	@PostMapping
	@PreAuthorize("hasPermission(#order, 'place-order')")
	Map<String, Object> placeOrder(Order order) {

		Map<String, Object> map = new HashMap<>();
		map.put("orderId", UUID.randomUUID());
		return map;
	}
}
