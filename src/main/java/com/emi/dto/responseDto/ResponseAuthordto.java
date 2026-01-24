package com.emi.dto.responseDto;

import java.time.LocalDateTime;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@XmlRootElement
public class ResponseAuthordto {
	
	private String penName;
		
    private LocalDateTime joinedAt;
    
    private String membership;
    
    private String bio;

}
