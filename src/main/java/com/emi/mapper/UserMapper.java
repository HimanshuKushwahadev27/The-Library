package com.emi.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.emi.dto.responseDto.AdminUserResponseDto;
import com.emi.entity.Membership;
import com.emi.entity.User;
import com.emi.enums.PaidAccess;

@Component
public class UserMapper {

	private Membership getDefaultMembership() {
		return Membership.builder()
				.accessType(PaidAccess.FREE)
				.autoRenew(true)
				.startDate(LocalDateTime.now())
				.endTime(LocalDateTime.now().plusMonths(1))
				.isActive(true)
				.build();
	}
	
	private AdminUserResponseDto toAdminDto(User user) {
		return AdminUserResponseDto
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
				.role(user.getRole());
	}
}
