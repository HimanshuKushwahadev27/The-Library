package com.emi.config;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.emi.Repo.TokenRepo;
import com.emi.service.Impl.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final TokenRepo tokenRepo;
	private final JwtService jwtService;
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {

		final String authHeader=request.getHeader("Authentication");
		final String jwt;
		
		if(authHeader==null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		jwt=authHeader.substring(7);
		String userName=jwtService.getUserName(jwt);
		List<String> roles=jwtService.extractRole(jwt);
		
		Collection<? extends GrantedAuthority> authorities=roles.stream().map(SimpleGrantedAuthority::new).toList();
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			var tokenValid=tokenRepo.findByToken(jwt)
					.map(t -> t.isExpired() && t.isRevoked())
					.orElse(false);
			
			if(jwtService.isTokenValid(jwt) && tokenValid ) {
				//token is valid then authenticate the use
				UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(
						userName,
						null,
						authorities);
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		filterChain.doFilter(request, response);
	}

}
