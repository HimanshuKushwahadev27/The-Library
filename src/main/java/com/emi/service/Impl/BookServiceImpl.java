package com.emi.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emi.Repo.BookRepo;
import com.emi.dto.requestDto.BookSearchRequestDto;
import com.emi.dto.requestDto.RequestBookDto;
import com.emi.dto.responseDto.ResponseBookDto;
import com.emi.enums.BookStatus;
import com.emi.service.BookService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

	
	private final BookRepo bookRepo;
	
	@Override
	public ResponseBookDto createBook(RequestBookDto requestBookDto) {
		return null;
	}

	@Override
	public ResponseBookDto updateBook(Long bookId, RequestBookDto requestBookDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseBookDto getByBookId(Long bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResponseBookDto> getAllBook() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBook(Long bookId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ResponseBookDto> searchBooks(BookSearchRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseBookDto updateBookStatus(Long bookId, BookStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean bookExists(long bookId) {
		// TODO Auto-generated method stub
		return false;
	}

}
