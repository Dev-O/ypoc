package com.poc;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.savedrequest.NullRequestCache;
//import org.springframework.session.ExpiringSession;
//import org.springframework.session.MapSessionRepository;
//import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//import org.springframework.session.web.http.HeaderHttpSessionStrategy;
//import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * {@code
 *  curl --noproxy localhost -u user:password -v http://localhost:8080/api/auth
 *  export AUTH_TOKEN=...
 *  curl --noproxy localhost -H "x-auth-token: $AUTH_TOKEN" -v http://localhost:8080/api/greet
 *  curl --noproxy localhost -H "x-auth-token: $AUTH_TOKEN" -v -d "amount=42.0"  http://localhost:8080/api/order
 *  curl --noproxy localhost -H "x-auth-token: $AUTH_TOKEN" -v -d "amount=1000.0"  http://localhost:8080/api/order
 *  
 *  curl --noproxy localhost -u admin:password -v http://localhost:8080/api/auth
 *  export AUTH_TOKEN=...
 *  curl --noproxy localhost -H "x-auth-token: $AUTH_TOKEN" -v -d "amount=1000.0"  http://localhost:8080/api/order
 *  }
 * </pre>
 *
 */
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

