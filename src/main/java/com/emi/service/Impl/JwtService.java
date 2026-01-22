package com.emi.service.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.emi.config.JwtProperties;
import com.emi.entity.User;
import com.emi.security.UserPrinciple;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

	private final JwtProperties properties;

	public String getUserName(String jwt) {
		return extractClaim(jwt , Claims::getSubject);
	}
	
	public SecretKey getSignInKey() {
		byte[] keyByte=Decoders.BASE64.decode(properties.getToken());
		return Keys.hmacShaKeyFor(keyByte);
	}
	
	public Claims getAllClaims(String jwt) {
		return Jwts.parser()
				.verifyWith(getSignInKey())
				.build()
				.parseSignedClaims(jwt)
				.getPayload();
	}
	
	public <T> T extractClaim(String jwt,
			Function<Claims , T> claimsResolver) {
		Claims claim=getAllClaims(jwt);
		return claimsResolver.apply(claim);
	}
	
	public String generateToken(User user) {
		UserDetails userpr = new UserPrinciple(user);
		
		Map<String ,Object> maps=new HashMap<>();
		
		List<String> roles=userpr
				.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.toList();
		
		maps.put("roles" , roles);
		return generateToken(maps, userpr);
	}
	
	public String generateToken(Map<String ,Object> extraClaims
			, UserDetails userDetail) {
		return Jwts.builder()
				.claims(extraClaims)
				.subject(userDetail.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+properties.getExpiration()))
				.signWith(getSignInKey())
				.compact();
				
	}
	
	public boolean isTokenValid(String jwt ){
		return  isTokenExpire(jwt);
	}
	
	public boolean isTokenExpire(String jwt) {
		return getExpirationDate(jwt).before(new Date(System.currentTimeMillis())); //expiration date was in the past ,token expired
	}
	
	public Date getExpirationDate(String jwt) {
		return extractClaim(jwt , Claims::getExpiration);
	}
	
	public List<String> extractRole(String jwt){
		Claims claim=getAllClaims(jwt);
		
		Object rolesObj=claim.get("roles");
		
		if(rolesObj instanceof List<?>) {
			return ((List<?>) rolesObj)
					.stream()
					.map(Object::toString)
					.toList()
					;
		}
		
		return List.of();
	}
}
