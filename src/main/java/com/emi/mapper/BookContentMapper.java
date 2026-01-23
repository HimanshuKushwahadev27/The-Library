package com.emi.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.emi.dto.requestDto.RequestBookContentDto;
import com.emi.dto.responseDto.ResponseBookContentDto;
import com.emi.entity.Book;
import com.emi.entity.BookContent;

@Component
public class BookContentMapper {

	public BookContent getBookContent(Book book , RequestBookContentDto req) {
		return BookContent.builder()
				.author(book.getBookAuthor())
				.book(book)
				.chapterNumber(req.getChapterNumber())
				.cloudUrl("Not Assigned")
				.content(req.getContent())
				.createdAt(LocalDateTime.now())
				.draft(true)
				.price(req.getPrice())
				.title(req.getTitle())
				.build()
				;
				
	}
	
	public ResponseBookContentDto toResponseBook(BookContent req) {
		return ResponseBookContentDto.builder()
				.book(req.getBook().getBookTitle())
				.user(req.getAuthor().getPenName())
				.content(req.getContent())
				.chapterNumber(req.getChapterNumber())
				.cloudUrl("Not assigned")
				.title(req.getTitle())
				.createdAt(req.getCreatedAt())
				.draft(req.isDraft())
				.build()
				;
	}

	public void transfer(BookContent bookContent, RequestBookContentDto request) {

		bookContent.setContent(request.getContent());
		bookContent.setTitle(request.getTitle());
	}
}
