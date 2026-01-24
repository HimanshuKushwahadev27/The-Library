package com.emi.dto.responseDto;

import java.time.LocalDateTime;

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

	
	private String name;
	
	private String email;
		
	private LocalDateTime joinedAt;
	
	private String membership;
	
	
}
