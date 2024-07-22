package com.deyvidsalvatore.web.expensifyapi.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.deyvidsalvatore.web.expensifyapi.models.ExpensifyUser;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

	private final ObjectMapper objectMapper;

	public WebSecurityConfiguration(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(Customizer.withDefaults())
			.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(requests -> configurePermissions(requests))
				.formLogin(formLogin -> formLogin.successHandler((req, res, auth) -> {
					res.setContentType("application/json");
					res.getWriter()
							.write(objectMapper.writerFor(ExpensifyUser.class).writeValueAsString(auth.getPrincipal()));
				}).failureHandler((req, res, auth) -> {
					res.setStatus(HttpStatus.UNAUTHORIZED.value());
				}));
		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOriginPatterns(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(Collections.singletonList("*"));
		configuration.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	private void configurePermissions(
			AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry requests) {
		requests.requestMatchers(HttpMethod.GET, "/employees")
				.hasRole(ExpensifyUser.Role.ROLE_REVIEWER.withoutRolePrefix())
				.requestMatchers(HttpMethod.GET, "/employees/{employee_id}")
				.hasRole(ExpensifyUser.Role.ROLE_EMPLOYEE.withoutRolePrefix())
				.requestMatchers(HttpMethod.POST, "/employees/{employee_id}/expenses")
				.hasRole(ExpensifyUser.Role.ROLE_EMPLOYEE.withoutRolePrefix())
				.requestMatchers(HttpMethod.DELETE, "/employees/{employee_id}/expenses")
				.hasRole(ExpensifyUser.Role.ROLE_EMPLOYEE.withoutRolePrefix())
				.requestMatchers(HttpMethod.PUT, "/expenses/{expense_id}/status")
				.hasRole(ExpensifyUser.Role.ROLE_REVIEWER.withoutRolePrefix()).anyRequest().authenticated();
	}

}
