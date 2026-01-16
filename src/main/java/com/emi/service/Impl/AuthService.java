package com.emi.service.Impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emi.Repo.UserRepo;
import com.emi.dto.requestDto.LoginRequest;
import com.emi.dto.responseDto.AuthenticateResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final UserRepo userRepo;
	
	
	public AuthenticateResponse authenticateUser(LoginRequest request) {
		var user=userRepo.findByEmail
				(request.getEmail()).
				orElseThrow(() -> new UsernameNotFoundException("user is not here"));

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
		

		var jwtToken=jwtService.generateToken(user);
		return AuthenticateResponse.builder()
				.Token(jwtToken)
				.build();
	}

	

}
