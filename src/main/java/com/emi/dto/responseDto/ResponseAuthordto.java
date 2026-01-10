package com.emi.dto.responseDto;

import java.time.LocalDateTime;

import com.emi.entity.Membership;
import com.emi.enums.Role;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement
public class ResponseAuthordto {
	
	private String Name;
	
	private Role Role;
	
    private LocalDateTime joinedAt;
    
    private Membership membership;
    
    private String bio;

}
