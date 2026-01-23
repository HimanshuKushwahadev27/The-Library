package com.emi.Controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emi.dto.requestDto.RequestAuthordto;
import com.emi.dto.responseDto.ResponseAuthordto;
import com.emi.service.AuthorService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Author")
public class AuthorController {

	private final AuthorService authorService;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseAuthordto> beAnAuthor(
			Principal principal
			,@RequestBody @Valid RequestAuthordto request){
		return  ResponseEntity.ok(authorService
				.becomeAuthor(principal.getName(), request));
	}
	
	@PostMapping("/update")
	public ResponseEntity<ResponseAuthordto> updateIt(
			Principal principal
			,@RequestBody @Valid RequestAuthordto request
			){
		return ResponseEntity.ok(
                         authorService
                         .updateAuthorProfile(principal.getName(), request));
	}
	
	
	

}
