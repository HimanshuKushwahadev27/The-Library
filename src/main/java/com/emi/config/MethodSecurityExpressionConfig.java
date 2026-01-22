package com.emi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;

@Configuration
public class MethodSecurityExpressionConfig {

	@SuppressWarnings("deprecation")
	MethodSecurityExpressionHandler methodSecurityExpressionHandler(RoleHierarchy role) {
		
		DefaultMethodSecurityExpressionHandler def=
				new DefaultMethodSecurityExpressionHandler();
		
		def.setRoleHierarchy(role);
		return def;
	}
}
