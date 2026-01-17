package com.emi.service;

import com.emi.entity.User;

public interface TokenService {

	
	public void saveUserToken(User user ,String jwtToken);
	public void revokeAllUserToken(User user);
}
