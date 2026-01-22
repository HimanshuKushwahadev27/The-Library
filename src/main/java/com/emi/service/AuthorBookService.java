package com.emi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.emi.dto.requestDto.BookSearchRequestDto;
import com.emi.dto.requestDto.RequestBookDto;
import com.emi.dto.responseDto.ResponseBookDto;
import com.emi.enums.BookStatus;

public interface AuthorBookService {

	ResponseBookDto createBookByAuthor(String email,RequestBookDto request);

	ResponseBookDto updateBookByAuthor(Long bookId, RequestBookDto requestBookDto , Long id);
	
	void deleteBookByauthor(Long bookId , String email);

	ResponseBookDto draftedBook(Long bookId, RequestBookDto requestBookDto);
	
    ResponseBookDto updateBookStatusbyAuthor(String email,Long bookId, BookStatus status);
	
	Page<ResponseBookDto> getPublishedBook(String email, Pageable pageable ,  	BookSearchRequestDto request);
}
