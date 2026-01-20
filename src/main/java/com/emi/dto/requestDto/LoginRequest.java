package com.emi.dto.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description="Login atuh")
public class LoginRequest {

	@Schema(example="23434324" , description="Email of the user")
	private String email;
	
	@Schema(example="xyz@4242" , description="password made when registering")
	private String password;
}
