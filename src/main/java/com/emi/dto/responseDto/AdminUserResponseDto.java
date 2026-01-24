package com.emi.dto.responseDto;

import java.time.LocalDateTime;
import java.util.Set;

import com.emi.enums.Role;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserResponseDto {
	

	private Long id;
	private String firstname;
	private String lastname;
	
	private String email;
	
	private String membership;
	private Set<Role> role;
	
	private boolean enabled;
	private boolean isAccountNonLocked;
	
	private LocalDateTime joinedAt;
	private LocalDateTime updateAt;
	
}
