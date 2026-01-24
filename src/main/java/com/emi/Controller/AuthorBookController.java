package com.emi.Controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emi.dto.requestDto.BookSearchRequestDto;
import com.emi.dto.requestDto.RequestBookDto;
import com.emi.dto.requestDto.RequestPhysicalBookDto;
import com.emi.dto.responseDto.ResponseBookDto;
import com.emi.dto.responseDto.ResponsePhysicalBookDto;
import com.emi.service.AuthorBookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/AuthorBook")
public class AuthorBookController {

	private final AuthorBookService service;

	@PostMapping("/book")
	public ResponseEntity<ResponsePhysicalBookDto> createP(
			@RequestBody @Valid RequestPhysicalBookDto req 
			,Principal principal){
		
		return ResponseEntity.ok(service
				.createPhysicalBookByAuthor(principal.getName(), req));
	}
	
	@PostMapping("/bookD")
	public ResponseEntity<ResponseBookDto> createD(
			@RequestBody @Valid RequestBookDto req 
			,Principal principal){
		
		return ResponseEntity.ok(service
				.createDigitalBookByAuthor(principal.getName(), req));
	}
	
	@PutMapping("/bookU")
	public ResponseEntity<ResponseBookDto> updateBook(
			@RequestBody @Valid RequestBookDto req 
			,Long id
			,Principal principal){
		
		return ResponseEntity.ok(service
				.updateBookByAuthor(principal.getName(), req , id));
	}
	
	@DeleteMapping("/bookDel/{id}")
	public ResponseEntity<?> DeleteBook(
			 @PathVariable Long id
			,Principal principal){
		
		service.deleteBookByauthor(id,principal.getName());
		return ResponseEntity.ok("Book with id : " +id+ " is removed");
	}
	
	@GetMapping("/bookDel/{id}")
	public ResponseEntity<Page<ResponseBookDto>> getBook(
			@RequestParam(defaultValue="0")int page,
			@RequestParam(defaultValue="10")int size,
			@RequestParam(defaultValue="createdAt,asc")String [] sort,
			@RequestBody @Valid BookSearchRequestDto req 
			,Principal principal){
		
		Sort sortObj=(sort==null)
				?Sort.unsorted()
				:Sort.by(parseSort(sort));
		
		Pageable pageable=PageRequest
				.of(
					page,
					size,
					sortObj);
		
		return ResponseEntity.ok(service.getPublishedBook(principal.getName(), pageable , req));
	}
	
	

	private List<Sort.Order> parseSort(String[] sort) {
		return Arrays.stream(sort)
				.map(s ->
				 {
					 String[] parts=s.split(",");
					 String property=parts[0];
					 Sort.Direction direction=
							 parts.length>1
							       ?Sort.Direction.fromString(parts[1])
							       :Sort.Direction.ASC;
							 return new Sort.Order(direction,property);
				 })
				.toList();
	}
}
