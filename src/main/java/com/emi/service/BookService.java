package com.emi.service;

import java.util.List;

import com.emi.dto.requestDto.BookSearchRequestDto;
import com.emi.dto.requestDto.RequestBookDto;
import com.emi.dto.responseDto.ResponseBookDto;
import com.emi.enums.BookStatus;

public interface BookService {

	ResponseBookDto createBook(RequestBookDto requestBookDto);
	
	//admin
	ResponseBookDto updateBook(Long bookId , RequestBookDto requestBookDto);
	
	ResponseBookDto getByBookId(Long bookId);
	
	List<ResponseBookDto> getAllBook();
	
	void deleteBook(Long bookId);
	
	List<ResponseBookDto> searchBooks(BookSearchRequestDto request);
	
	ResponseBookDto updateBookStatus(Long bookId , BookStatus status);
	
	boolean bookExists(long bookId);
	
	
}
