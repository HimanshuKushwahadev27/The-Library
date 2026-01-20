package com.emi.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emi.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import com.emi.dto.requestDto.PasswordUpdateRequest;
import com.emi.dto.requestDto.UserUpdateRequestDto;
import com.emi.dto.responseDto.ResponseUserDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
    	//create user done in authService
	
	
	//get single user
	
	
	@GetMapping("/me")
	@Operation(
				summary="Get user information" ,
			    description="users endpoint"
			)
	public ResponseUserDto getUser() {
		return userService.getUser();
	}
	
	@Operation(
			summary="update user information" ,
		    description="users endpoint"
		)
	@PutMapping("/updateMe")
	public ResponseUserDto update(@RequestBody @Valid UserUpdateRequestDto request) {
		return userService.updateUser( request);
	}
	
	@Operation(
			summary="delete user " ,
		    description="users endpoint"
		)
	@DeleteMapping("/deleteUser")
	public ResponseEntity<?> delete(){
		userService.deleteUser();
		return ResponseEntity.ok("User Deleted");
	}
	
	@Operation(
			summary="change user password" ,
		    description="users endpoint"
		)
	@PutMapping("/updatePasswordUser/password")
	public ResponseEntity<?> changePasswordUser(
			                    @RequestBody @Valid PasswordUpdateRequest request) {
		userService.changePassword(request);
		return ResponseEntity.ok("Password Changed");
	}

}
