package com.emi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.emi.Repo.UserRepo;
import com.emi.security.UserPrinciple;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

	private final UserRepo userRepo;
	
	@Bean
	UserDetailsService userDetailsService() {
		return username -> new UserPrinciple(
				userRepo.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"))
				);
	}
	
	@Bean
	AuthenticationProvider AuthenticationProvider() {
		DaoAuthenticationProvider daoAuth=new DaoAuthenticationProvider(userDetailsService());
		daoAuth.setPasswordEncoder(passwordEncoder());
		return daoAuth;
	}
	
	@Bean
	 PasswordEncoder passwordEncoder() {		
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
	}
}
