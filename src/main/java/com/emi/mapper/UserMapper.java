package com.emi.mapper;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.emi.dto.responseDto.AdminUserResponseDto;
import com.emi.dto.responseDto.ResponseUserDto;
import com.emi.entity.Membership;
import com.emi.entity.User;
import com.emi.enums.PaidAccess;
import com.emi.enums.Role;


@Component
public class UserMapper {

	public Membership getDefaultMembership() {
		return Membership.builder()
				.accessType(PaidAccess.FREE)
				.autoRenew(true)
				.startDate(LocalDateTime.now())
				.endTime(LocalDateTime.now().plusMonths(1))
				.isActive(true)
				.build();
	}
	
	public AdminUserResponseDto toAdminDto(User user) {
		
		Set<Role> role=user.getRole();
		var response = AdminUserResponseDto
				.builder()
				.email(user.getEmail())
				.enabled(user.isEnabled())
				.firstname(user.getFirstName())
				.lastname(user.getLastName())
				.isAccountNonLocked(user.isAccountNonLocked())
				.id(user.getUser_id())
				.membership(user.getMembership())
				.updateAt(user.getUpdatedAt())
				.joinedAt(user.getCreatedAt())
				.build();
		
		response.setRole(role);
		return response;
	}

	public ResponseUserDto toUserProfile(User user) {
		// TODO Auto-generated method stub
		
		Set<Role> role=user.getRole();
		var response= ResponseUserDto
				.builder()
				.email(user.getEmail())
				.name(Stream.of(user.getFirstName() , user.getLastName())
						.filter(Objects::nonNull)
						.collect(Collectors.joining(" ")))
				.joinedAt(user.getCreatedAt())
				.membership(user.getMembership())
				.build()
				;
		response.setRole(role);
		return response;
	}
	
}
