package com.emi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final LogoutHandler logoutService;
	private final JwtAuthenticationFilter jwtAuthProvider;
	private final AuthenticationProvider authProvider;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		     .csrf(csrf -> csrf.disable())
		     
		     .authorizeHttpRequests(auth -> auth
		    		 .requestMatchers( 
		    				    "/api/v1/auth/**",
		    			        "/swagger-ui/**",
		    			        "/swagger-ui.html",
		    			        "/v3/api-docs/**")
		    		 .permitAll()
		    		 .anyRequest()
		    		 .authenticated())
		     
		     .sessionManagement(Session ->Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		     .authenticationProvider(authProvider)
		     .addFilterBefore(jwtAuthProvider, UsernamePasswordAuthenticationFilter.class)
		     
		     .logout(logout -> logout
		    		 .logoutUrl("/api/v1/auth/logout")
		    		 .addLogoutHandler(logoutService)
		    		 .logoutSuccessHandler((request , response ,authentication) -> SecurityContextHolder.clearContext())
		    		 )
		     ;
		return http.build();
	}
}
