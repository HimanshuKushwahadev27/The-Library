package com.emi.service.Impl;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.emi.Repo.AuthorRepo;
import com.emi.Repo.BookContentRepo;
import com.emi.Repo.BookRepo;
import com.emi.dto.requestDto.RequestBookContentDto;
import com.emi.dto.responseDto.ResponseBookContentDto;
import com.emi.entity.Author;
import com.emi.entity.Book;
import com.emi.entity.BookContent;
import com.emi.enums.BookFormat;
import com.emi.exceptions.ContentNotFoundException;
import com.emi.exceptions.UserNotExistException;
import com.emi.mapper.BookContentMapper;
import com.emi.service.BookContentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookContentServiceImpl implements BookContentService {

	private final AuthorRepo authorRepo;
	private final BookContentMapper bookContentMapper;
	private final BookRepo bookRepo;
	private final BookContentRepo bookContentRepo;
	
	
	@PreAuthorize("hasRole('AUTHOR')")
	@Override
	public ResponseBookContentDto createDraft(RequestBookContentDto request) {
		
		Book book = bookRepo.findById(request.getBookId())
				            .orElseThrow(() -> new ContentNotFoundException("Please register the book first"));
		
		if(book.getBookformat().contains(BookFormat.PHYSICAL)) {
			throw new IllegalStateException("U cant make drafts for physical book");
		}
		BookContent bookContent=bookContentMapper.getBookContent(book, request);
		bookContentRepo.save(bookContent);
		book.setChapterNumber(book.getChapterNumber()+1);
		bookRepo.save(book);

		return bookContentMapper.toResponseBook(bookContent);
		
	}
	

	@PreAuthorize("hasRole('AUTHOR')")
	@Override
	public ResponseBookContentDto updateDraft(RequestBookContentDto request , String email) {
		
		Book book = bookRepo.findById(request.getBookId())
	            .orElseThrow(() -> new ContentNotFoundException("Please register the book first"));

		if(book.getBookformat().contains(BookFormat.PHYSICAL)) {
			throw new IllegalStateException("U cant make drafts for physical book");
		}
		
		Author author = authorRepo.findAuthorByUserEmail(email)
				.orElseThrow(() -> new UserNotExistException("You are not registered as an author"));
		
		if(author.getAuthorId()!=book.getBookAuthor().getAuthorId()) {
			throw new IllegalStateException("You cant make changes in this draft");
		}
		
		BookContent bookContent=bookContentRepo
				.findByChapterNumber(request.getChapterNumber())
				.orElseThrow(() -> new ContentNotFoundException("Draft not found for the given chapter number"));
		
		bookContentMapper.transfer(bookContent , request);
		bookContentRepo.save(bookContent);
		
		return bookContentMapper.toResponseBook(bookContent);
	}

	@PreAuthorize("hasRole('AUTHOR')")
	@Override
	public ResponseBookContentDto publishDraft(RequestBookContentDto request , String email) {
		
		Book book = bookRepo.findById(request.getBookId())
	            .orElseThrow(() -> new ContentNotFoundException("Please register the book first"));

		if(book.getBookformat().contains(BookFormat.PHYSICAL)) {
			throw new IllegalStateException("U cant make drafts for physical book");
		}
		
		Author author = authorRepo.findAuthorByUserEmail(email)
				.orElseThrow(() -> new UserNotExistException("You are not registered as an author"));
		
		if(author.getAuthorId()!=book.getBookAuthor().getAuthorId()) {
			throw new IllegalStateException("You cant make changes in this draft");
		}
		
		BookContent bookContent=bookContentRepo
				.findByChapterNumber(request.getChapterNumber())
				.orElseThrow(() -> new ContentNotFoundException("Draft not found for the given chapter number"));
		
		bookContent.setDraft(false);
		return bookContentMapper.toResponseBook(bookContent);
		
	}

	
	@PreAuthorize("hasRole('AUTHOR')")
	@Override
	public List<ResponseBookContentDto> getDraftsByAuthor(String email) {
		Author author=authorRepo.findAuthorByUserEmail(email)
				.orElseThrow(() ->  new UserNotExistException("You are Not registered"));
		
		List<BookContent> info=bookContentRepo
				                     .findAllByAuthorId(author.getAuthorId());
	    if(info==null) {
	    	throw new ContentNotFoundException("No drafts created by the author");
	    }
	    
	    return info.stream().map(bookContentMapper::toResponseBook).toList();
	}

	@PreAuthorize("hasRole('USER')")
	@Override
	public List<ResponseBookContentDto> getContentsByBook(Long bookId) {
		List<BookContent> info=bookContentRepo
                .findAllByBookId(bookId);
		if(info==null) {
		throw new ContentNotFoundException("No drafts created by the author");
		}
		
		return info.stream().map(bookContentMapper::toResponseBook).toList();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public ResponseBookContentDto getContentById(Long contentId) {
		BookContent bookContent=bookContentRepo
				.findById(contentId)
				.orElseThrow(() -> new ContentNotFoundException("Draft not found for the given chapter number"));
		 
		return bookContentMapper.toResponseBook(bookContent);
	}

}
