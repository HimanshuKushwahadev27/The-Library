package com.emi.service.Impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emi.Repo.BookRepo;
import com.emi.dto.requestDto.BookSearchRequestDto;
import com.emi.dto.requestDto.RequestBookDto;
import com.emi.dto.requestDto.RequestPhysicalBookDto;
import com.emi.dto.responseDto.ResponseBookDto;
import com.emi.entity.Book;
import com.emi.enums.BookStatus;
import com.emi.enums.Role;
import com.emi.exceptions.ContentNotFoundException;
import com.emi.mapper.BookMapper;
import com.emi.service.BookService;

import lombok.RequiredArgsConstructor;
import com.emi.specification.BookSpecification;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService{

	private final BookMapper bookMapper;
	private final BookRepo bookRepo;
	
	@Override
	public Book createBookPhysical(RequestPhysicalBookDto requestBookDto) {
		
		String isbn=requestBookDto.getIsbn();
		
		if(bookRepo.findByIsbnNumber(isbn)) {
			throw new IllegalStateException("Book already exist : ");
		}
		
		Book book=bookMapper.toBookPhysicalDto(requestBookDto);
	    return book;
	}
	
	@Override
	public Book createBookDigital(RequestBookDto requestBookDto) {
		
		String isbn=requestBookDto.getIsbn();
		
		if(bookRepo.findByIsbnNumber(isbn)) {
			throw new IllegalStateException("Book already exist : ");
		}
		
		Book book=bookMapper.toBookDto(requestBookDto);
	    return book;
	}

	@Override
	public void updateBook(Book book, RequestBookDto requestBookDto) {
		bookMapper.transfer(book, requestBookDto);
	}

	@Override
	public ResponseBookDto getByBookId(Long bookId) {
	  Book book = bookRepo.findByIdAndStatus(BookStatus.AVAILABLE , bookId)
			               .orElseThrow(() -> new ContentNotFoundException("Book Not found"));
	  
	  return bookMapper.toResponseFromBook(book);
	}

	@Override
	public List<ResponseBookDto> getAllBook() {
	    return bookRepo.findByStatus(BookStatus.AVAILABLE)
	            .stream()
	            .map(bookMapper::toResponseFromBook)
	            .toList();
	}

	@Override
	public void deleteBook(Long bookId) {
        Book book = bookRepo.findById(bookId).orElseThrow(() -> new ContentNotFoundException("book not found"));

        book.setBookStatus(BookStatus.DELETED);

	}

	@Override
	public Page<ResponseBookDto> searchBooks(
			 BookSearchRequestDto request,
			 Role role,
			 Pageable pageable,
			 Long authorId) {
		
		
		if(role==Role.AUTHOR && authorId==null) {
			
			 throw new IllegalArgumentException(
		                "authorId is required for AUTHOR search"
		     );
		}
		return bookRepo
				.findAll(BookSpecification.search(request ,role,authorId)
						,pageable)
				.map(bookMapper::toResponseFromBook);
	}

	@Override
	public ResponseBookDto updateBookStatus(Long bookId, BookStatus status) {
		
		 Book book = bookRepo.findByIdAndStatus(BookStatus.AVAILABLE , bookId)
	               .orElseThrow(() -> new ContentNotFoundException("Book Not found"));
		 
		 book.setBookStatus(status);
		 bookRepo.save(book);
		 return bookMapper.toResponseFromBook(book);
	}


}
