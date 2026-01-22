package com.emi.dto.responseDto;

import java.time.LocalDateTime;
import java.util.Set;

import com.emi.entity.Membership;
import com.emi.enums.Role;
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
	
	private Set<Role> Role;
	
    private LocalDateTime joinedAt;
    
    private Membership membership;
    
    private String bio;

}
