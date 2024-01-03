package com.daiana.backendportfolio.auth;

import com.daiana.backendportfolio.auth.filters.JwtAuthenticationFilter;
import com.daiana.backendportfolio.auth.filters.JwtValidationFilter;
import jakarta.servlet.FilterRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class SpringSecurityConfig {

	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	AuthenticationManager authenticationManager() throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.authorizeHttpRequests(req -> req.requestMatchers(HttpMethod.POST, "/users").permitAll()
				.requestMatchers(HttpMethod.GET, "/users").permitAll()
								//.requestMatchers(HttpMethod.GET, "/users").hasAuthority("ROLE_ADMIN")
								.requestMatchers(HttpMethod.GET, "/profile").hasAnyAuthority("ROLE_ADMIN")
								.requestMatchers(HttpMethod.POST, "/profile").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
								.requestMatchers(HttpMethod.PUT, "/profile/{username}").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
				.anyRequest().permitAll()
				).addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
				.addFilter(new JwtValidationFilter(authenticationConfiguration.getAuthenticationManager()))
				.csrf(config -> config.disable())
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.cors(corsConfigure -> corsConfigure.configurationSource(corsConfigurationSource()));
	return httpSecurity.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource(){
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

	@Bean
	FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
				new CorsFilter(corsConfigurationSource())
		);
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
