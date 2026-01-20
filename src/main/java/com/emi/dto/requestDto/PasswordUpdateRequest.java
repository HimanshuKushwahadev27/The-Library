package com.emi.dto.requestDto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description="Update password")
public class PasswordUpdateRequest {

	@Schema(example="14194810" , description="old password")
    @NotBlank(message="enter password")
	private String  oldPassword;
	
	@Schema(example="243434" , description="new password")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @NotBlank(message="enter password")
	private String newPassword;
	
}
