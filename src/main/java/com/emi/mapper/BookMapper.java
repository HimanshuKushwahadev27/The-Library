package com.emi.mapper;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.emi.dto.requestDto.RequestBookDto;
import com.emi.dto.responseDto.ResponseBookDto;
import com.emi.entity.Book;
import com.emi.enums.BookStatus;

@Component
public class BookMapper {

	public Book toBookDto(RequestBookDto request ) {
		return Book.builder()
				.bookTitle(request.getBookTitle())
				.description(request.getDescription())
				.isbn(request.getIsbn())
				.bookLanguage(request.getBookLanguage())
				.bookPriceDigital(request.getBookPriceDigital())
				.bookPricePhysical(request.getBookPricePhysical())
				.bookformat(request.getFormat())
				.bookStatus(BookStatus.AVAILABLE)
				.createdAt(LocalDateTime.now())
				.coverImageUrl(request.getUrl())
				.genres(request.getGenre())
				.deletedAt(null)
				.build();
				
	}
	
	public ResponseBookDto toResponseFromBook(Book book) {
		String name=Stream
				.of(book.getBookAuthor().getFirstName()
				  , book.getBookAuthor().getLastName())
				.filter(Objects::nonNull)
				.filter(s -> !s.isBlank())
				.collect(Collectors.joining(" "));
				
		return ResponseBookDto
				.builder()
				.title(book.getBookTitle())
				.description(book.getDescription())
				.authorName(name)
				.format(book.getBookformat())
				.genre(book.getGenres())
				.language(book.getBookLanguage())
				.chapterNumbers(book.getChapterNumber())
				.bookPriceDigital(book.getBookPriceDigital())
				.bookPricePhysical(book.getBookPriceDigital())
				.build();
				
	}
	
	public void transfer(Book book , RequestBookDto request) {
		book.setBookformat(request.getFormat());
		book.setBookTitle(request.getBookTitle());
		book.setDescription(request.getDescription());
		book.setIsbn(request.getIsbn());
		book.setBookLanguage(request.getBookLanguage());
		book.setGenres(request.getGenre());
		book.setBookPriceDigital(request.getBookPriceDigital());
		book.setBookPricePhysical(request.getBookPricePhysical());
		book.setCoverImageUrl(request.getUrl());
		book.setChapterNumber(request.getChapterNumbers());
	}
}
