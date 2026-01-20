package com.emi.dto.responseDto;

import java.time.LocalDateTime;

import com.emi.entity.Membership;
import com.emi.enums.Role;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@XmlRootElement
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ResponseUserDto {

	private Long id ;
	
	private String name;
	
	private String email;
	
	private Role role ;
	
	private LocalDateTime joinedAt;
	
	private Membership membership;
	
	
}
