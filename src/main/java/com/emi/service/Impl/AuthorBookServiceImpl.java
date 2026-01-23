package com.emi.service.Impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.emi.entity.BookInventory;
import com.emi.Repo.AuthorRepo;
import com.emi.Repo.BookInventoryRepo;
import com.emi.Repo.BookRepo;
import com.emi.dto.requestDto.BookSearchRequestDto;
import com.emi.dto.requestDto.RequestBookDto;
import com.emi.dto.requestDto.RequestPhysicalBookDto;
import com.emi.dto.responseDto.ResponseBookDto;
import com.emi.dto.responseDto.ResponsePhysicalBookDto;
import com.emi.entity.Author;
import com.emi.entity.Book;
import com.emi.enums.BookStatus;
import com.emi.enums.Role;
import com.emi.exceptions.ContentNotFoundException;
import com.emi.exceptions.UserNotExistException;
import com.emi.mapper.BookInventoryMapper;
import com.emi.mapper.BookMapper;
import com.emi.service.AuthorBookService;
import com.emi.service.BookInventoryService;
import com.emi.service.BookService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class AuthorBookServiceImpl implements AuthorBookService {

	
	private final AuthorRepo authorRepo;
	private final BookService bookService;
	private final BookRepo bookRepo;
	private final BookMapper bookMapper;
	private final BookInventoryService bookInventoryService;
	private final BookInventoryMapper bookInventoryMapper;
	private final BookInventoryRepo bookInventoryRepo;
	
	@PreAuthorize("hasRole('AUTHOR')")
	@Override
	public ResponsePhysicalBookDto createPhysicalBookByAuthor(String email,RequestPhysicalBookDto request) {
		
		var author=authorRepo.findAuthorByUserEmail(email)
				          .orElseThrow(() -> new UserNotExistException("Author not exist"));
		
		Book book=bookService.createBookPhysical(request);
		
		
		book.setBookAuthor(author);
		bookRepo.save(book);
		BookInventory bookInv=bookInventoryService
				.createInventory(bookInventoryMapper
						.getBasicInventory(), book);
		
		bookInv.setAvailableCopies(request.getAvailableCopies());
		bookInv.setTotalCopies(request.getTotalCopies());
		bookInventoryRepo.save(bookInv);
		
		return bookMapper.toResponsePhysicalFromBook(book , request);
	}
	
	@PreAuthorize("hasRole('AUTHOR')")
	@Override
	public ResponseBookDto createDigitalBookByAuthor(String email,RequestBookDto request) {
		
		var author=authorRepo.findAuthorByUserEmail(email)
				          .orElseThrow(() -> new UserNotExistException("Author not exist"));
		
		Book book=bookService.createBookDigital(request);
		
		
		book.setBookAuthor(author);
		bookRepo.save(book);
		
		return bookMapper.toResponseFromBook(book);
	}
	
	@PreAuthorize("hasRole('AUTHOR')")
	@Override
	public ResponseBookDto updateBookByAuthor(Long bookId, RequestBookDto requestBookDto, Long authorId) {
		
		Book book = bookRepo.findById(bookId).orElseThrow(() -> new ContentNotFoundException("book not found"));
		
		if(book.getBookStatus()==BookStatus.DELETED) {
			throw new IllegalStateException("book deleted");
		}
		
		if(book.getBookAuthor().getAuthorId()!=authorId) {
			throw new AccessDeniedException("book not yours");
		}
			
		bookService.updateBook(book, requestBookDto);
		
		return bookMapper.toResponseFromBook(book);
	}

	@PreAuthorize("hasRole('AUTHOR')")
	@Override
	public void deleteBookByauthor(Long bookId , String email) {

        Book book = bookRepo.findById(bookId).orElseThrow(() -> new ContentNotFoundException("book not found"));
		
        var author=authorRepo.findAuthorByUserEmail(email)
		          .orElseThrow(() -> new UserNotExistException("Author not exist"));

		if(book.getBookAuthor().getAuthorId()!=author.getAuthorId()) {
			throw new AccessDeniedException("book not yours");
		}
		
		bookService.deleteBook(bookId);
	}

	
	@PreAuthorize("hasRole('AUTHOR')")
	@Override
	public ResponseBookDto draftedBook(Long bookId, RequestBookDto requestBookDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@PreAuthorize("hasRole('AUTHOR')")
	@Override
	public Page<ResponseBookDto> getPublishedBook(String email,Pageable pageable, BookSearchRequestDto request) {
		
		Author author = authorRepo.findAuthorByUserEmail(email)
				                  .orElseThrow(() -> new UserNotExistException("You Are Not a Author"));   
		
		return bookService.searchBooks(request, Role.AUTHOR, pageable, author.getAuthorId());
	}

	@PreAuthorize("hasRole('AUTHOR')")
	@Override
	public ResponseBookDto updateBookStatusbyAuthor(String email,Long bookId, BookStatus status) {
	      Book book = bookRepo.findById(bookId).orElseThrow(() -> new ContentNotFoundException("book not found"));
		  var author=authorRepo.findAuthorByUserEmail(email)
			          .orElseThrow(() -> new UserNotExistException("Author not exist"));
	      
			if(book.getBookAuthor().getAuthorId()!=author.getAuthorId()) {
				throw new AccessDeniedException("book not yours");
			}
		return bookService.updateBookStatus(bookId, status);
	}

}
