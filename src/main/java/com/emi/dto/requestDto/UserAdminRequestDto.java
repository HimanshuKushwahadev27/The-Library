package com.emi.dto.requestDto;

import com.emi.entity.Membership;
import com.emi.enums.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

//Handled by admin 

@Data
@NoArgsConstructor
@XmlRootElement
@Schema(description="Admin request DTO")
public class UserAdminRequestDto {

	
	@Schema(example="John" , description="firstname")
	@NotBlank(message="Enter the firstname")
	private String firstName;
	
	@Schema(example="Doe" , description="lastname")
	@NotBlank(message="Enter the lastname")
	private String lastName;
	
   //password auto generated
	
	@Schema(example="User" , description="Specification of the authority")
    @NotNull(message="Specify the role")
	private Role role;
	
	@Schema(example="Premium" , description="Grants different priviliges to client")
    @NotNull(message="Select the membership type")
	private Membership membership;
}
