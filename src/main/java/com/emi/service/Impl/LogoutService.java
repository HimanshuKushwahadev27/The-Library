package com.emi.service.Impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.emi.Repo.TokenRepo;
import com.emi.entity.Token;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LogoutService implements LogoutHandler {

	private final TokenRepo tokenRepo;
	@Override
	public void logout(
			HttpServletRequest request,
			HttpServletResponse response,
		    Authentication authentication) {

		final  String authHeader=request.getHeader("Authorization");
	    final String jwt;
		
		if(authHeader==null || !authHeader.startsWith("Bearer ")) {
			return;
		}
		
		jwt=authHeader.substring(7);
		
		Token token=tokenRepo.findByToken(jwt).orElse(null);
		if(token!=null) {
			token.setExpired(true);
			token.setRevoked(true);
			tokenRepo.save(token);
		}
	}

}
