package com.emi.dto.responseDto;

import java.time.LocalDateTime;

import com.emi.entity.Membership;
import com.emi.enums.Role;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@XmlRootElement
@NoArgsConstructor
public class AdminResponseDto {

	private Long id;
	private String firstname;
	private String lastname;
	
	private String email;
	
	private Membership membership;
	private Role role;
	
	private boolean enabled;
	
	private LocalDateTime joinedAt;
	private LocalDateTime updateAt;
	
}
