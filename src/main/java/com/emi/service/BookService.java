package com.emi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.emi.dto.requestDto.BookSearchRequestDto;
import com.emi.dto.requestDto.RequestBookDto;
import com.emi.dto.requestDto.RequestPhysicalBookDto;
import com.emi.dto.requestDto.UpdateRequestBookDto;
import com.emi.dto.responseDto.ResponseBookDto;
import com.emi.entity.Book;
import com.emi.enums.BookStatus;
import com.emi.enums.Role;

public interface BookService {

	Book createBookPhysical(RequestPhysicalBookDto requestBookDto);
	
	//admin
	void updateBook(Book book , UpdateRequestBookDto requestBookDto);
	
	ResponseBookDto getByBookId(Long bookId);
	
	List<ResponseBookDto> getAllBook();
	
	void deleteBook(Long bookId);
	
	Page<ResponseBookDto> searchBooks(BookSearchRequestDto request , Role role, Pageable pageable , Long authorId);
	
	ResponseBookDto updateBookStatus(Long bookId , BookStatus status);

	Book createBookDigital(RequestBookDto requestBookDto);
	
}
