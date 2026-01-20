package com.emi.advice;

import javax.security.auth.login.AccountExpiredException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.emi.exceptions.InvalidRegistrationDataException;
import com.emi.exceptions.RoleNotAllowedException;
import com.emi.exceptions.UserAlreadyExistsException;
import com.emi.exceptions.UserNotExistException;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> handleBadCredentials(BadCredentialsException ex){
		return  ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<?> handleDiasbled(DisabledException ex){
		return  ResponseEntity
				.status(HttpStatus.FORBIDDEN)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(LockedException.class)
	public ResponseEntity<?> handleLocked(LockedException ex){
		return  ResponseEntity
				.status(HttpStatus.LOCKED)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(CredentialsExpiredException.class)
	public ResponseEntity<?> handleAccountExpired(CredentialsExpiredException ex){
		return  ResponseEntity
				.status(HttpStatus.FORBIDDEN)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<?> handleUserExists(UserAlreadyExistsException ex){
		return  ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(InvalidRegistrationDataException.class)
	public ResponseEntity<?> handleInvalidRegisterationData(InvalidRegistrationDataException ex){
		return  ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(AccountExpiredException.class)
	public ResponseEntity<?> handleBadCredentials(AccountExpiredException ex){
		return  ResponseEntity
				.status(HttpStatus.FORBIDDEN)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(RoleNotAllowedException .class)
	public ResponseEntity<?> handleRoleNotAllowed(RoleNotAllowedException  ex){
		return  ResponseEntity
				.status(HttpStatus.FORBIDDEN)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(UserNotExistException.class)
	public ResponseEntity<?> handleUserNotExistException(UserNotExistException  ex){
		return  ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<?> handleIllegalState(IllegalStateException  ex){
		return  ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(ex.getMessage());
	}
	
	
	
}
