package com.emi.dto.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@XmlRootElement
@Schema(description="User register request DTO")
public class UserUpdateRequestDto {


	@Schema(example="John" , description="firstname")
	@NotBlank(message="enter the firstname")
	private String firstname;
	
	@Schema(example="Doe" , description="lastname")
	@NotBlank(message="enter the lastname")
	private String lastname;
	
}
