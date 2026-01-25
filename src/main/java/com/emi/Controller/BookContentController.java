package com.emi.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emi.dto.requestDto.RequestBookContentDto;
import com.emi.dto.responseDto.ResponseBookContentDto;
import com.emi.service.BookContentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/Chapters")
@RequiredArgsConstructor
public class BookContentController {

	private final BookContentService service;
	
	@PostMapping("/create")
	ResponseEntity<ResponseBookContentDto> createIt(
			 @RequestBody @Valid RequestBookContentDto request){
		return ResponseEntity.ok(service.createDraft(request));
	}
	
	@PutMapping("/update")
	ResponseEntity<ResponseBookContentDto> updateIt(
			Principal principal,
			@Valid @RequestBody	RequestBookContentDto request){
		return ResponseEntity.ok(service.updateDraft(request, principal.getName()));
	}
	
	@PostMapping("/publish")
	ResponseEntity<ResponseBookContentDto> publishIt(
			Principal principal,
			@Valid @RequestBody RequestBookContentDto request){
		return ResponseEntity.ok(service.updateDraft(request, principal.getName()));
	}
	
	@PostMapping("/getAllDraft")
	ResponseEntity<List<ResponseBookContentDto>> getByAuthor(
			Principal principal){
		return ResponseEntity.ok(service.getDraftsByAuthor( principal.getName()));
	}
	
	@PostMapping("/getAllChapters/{bookId}")
	ResponseEntity<List<ResponseBookContentDto>> getByAuthor(
			@PathVariable Long bookId ){
		return ResponseEntity.ok(service.getContentsByBook( bookId));
	}
	
	@PostMapping("/getAllChapters/{contentID}")
	ResponseEntity<ResponseBookContentDto> getByContentId(
			@PathVariable Long contentID ){
		return ResponseEntity.ok(service.getContentById(contentID));
	}
	
}
