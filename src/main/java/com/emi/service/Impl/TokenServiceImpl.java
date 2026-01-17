package com.emi.service.Impl;

import org.springframework.stereotype.Service;

import com.emi.Repo.TokenRepo;
import com.emi.entity.Token;
import com.emi.entity.User;
import com.emi.enums.TokenType;
import com.emi.service.TokenService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

	private final TokenRepo tokenRepo;
	
	public void saveUserToken(User user ,String jwtToken) {
		var token=Token.builder()
				.token(jwtToken)
				.revoked(false)
				.type(TokenType.BEARER)
				.expired(false)
				.user(user)
				.build();
		
		tokenRepo.save(token);	
	}
	
	public void revokeAllUserToken(User user) {
		var token=tokenRepo.findAllValidTokenByUser(user.getUser_id());
		if(token==null) {
			return;
		}
		token.forEach(t-> {
			t.setExpired(true);
			t.setRevoked(true);
		});
	}
	
}
