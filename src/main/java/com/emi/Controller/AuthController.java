package com.emi.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emi.dto.requestDto.LoginRequest;
import com.emi.dto.requestDto.UserRegisterRequest;
import com.emi.dto.responseDto.AuthenticateResponse;
import com.emi.service.Impl.AuthService;
import com.emi.service.Impl.UserServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserServiceImpl userService;
	private final AuthService authService;
	
	@PostMapping(
			value="/Register" , 
			produces={"application/json" , "application/xml"}  , 
			consumes={"application/json" , "application/xml"} 
			)
	public ResponseEntity<?> Register(@RequestBody @Valid UserRegisterRequest request){
		userService.createUser(request);
		return ResponseEntity.status(HttpStatus.CREATED).body("You are Registered Please go to login endpoint");
	}
	
	@PostMapping(value="/Login",
			produces={"application/json" , "application/xml"}  , 
			consumes={"application/json" , "application/xml"} 
			)
	public ResponseEntity<AuthenticateResponse> Login(@RequestBody @Valid LoginRequest request){
		return ResponseEntity.ok(authService.authenticateUser(request));
	}
}
