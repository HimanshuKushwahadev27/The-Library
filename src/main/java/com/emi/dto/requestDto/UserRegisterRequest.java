package com.emi.dto.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement
@Schema(description="User register request DTO")
public class UserRegisterRequest {

	@Schema(example="John" , description="firstname")
	@NotBlank(message="enter the firstname")
	private String firstname;
	
	@Schema(example="Doe" , description="lastname")
	@NotBlank(message="enter the lastname")
	private String lastname;
	
	@Schema(example="xyz354@gmail.com" , description="Email id")
    @Email(message = "Invalid email format")
	@NotBlank(message="enter the email")
	private String email;
	
	
	@Schema(example="iwucg@976DTY" , description="A strong encription")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @NotBlank(message="enter passowrd")
	private String password;

	
}
