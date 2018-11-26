package com.poc.config;

import java.io.Serializable;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.poc.Order;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

//@Slf4j please use this later
@Component
class DomainAwarePermissionEvaluator implements PermissionEvaluator {
	Logger logger = LoggerFactory.getLogger(DomainAwarePermissionEvaluator.class);
	
	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

		logger.info("check permission '{}' for user '{}' for target '{}'", permission, authentication.getName(),
				targetDomainObject);

		if ("place-order".equals(permission)) {
			Order order = (Order) targetDomainObject;
			if (order.getAmount() > 500) {
				return hasRole("ROLE_ADMIN", authentication);
			}
		}

		return true;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		return hasPermission(authentication, new DomainObjectReference(targetId, targetType), permission);
	}

	private boolean hasRole(String role, Authentication auth) {

		if (auth == null || auth.getPrincipal() == null) {
			return false;
		}

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

		if (CollectionUtils.isEmpty(authorities)) {
			return false;
		}

		return authorities.stream().filter(ga -> role.equals(ga.getAuthority())).findAny().isPresent();
	}

	@Value
	static class DomainObjectReference {
		private final Serializable targetId;
		private final String targetType;
	}
}