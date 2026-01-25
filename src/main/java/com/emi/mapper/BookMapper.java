package com.emi.mapper;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.emi.dto.requestDto.RequestBookDto;
import com.emi.dto.requestDto.RequestPhysicalBookDto;
import com.emi.dto.requestDto.UpdateRequestBookDto;
import com.emi.dto.responseDto.ResponseBookDto;
import com.emi.dto.responseDto.ResponsePhysicalBookDto;
import com.emi.dto.responseDto.UpdateBookResponseDto;
import com.emi.entity.Book;
import com.emi.entity.BookGenre;
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
				.chapterNumber(request.getChapterNumbers())
				.coverImageUrl(request.getUrl())
				.deletedAt(null)
				.build();
				
	}
	
	public Book toBookPhysicalDto(RequestPhysicalBookDto request) {
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
				.chapterNumber(request.getChapterNumbers())
				.deletedAt(null)
				.build();
	}
	
	public ResponseBookDto toResponseFromBook(Book book , RequestBookDto request) {
		String name=Stream
				.of(book.getBookAuthor().getFirstName()
				  , book.getBookAuthor().getLastName())
				.filter(Objects::nonNull)
				.filter(s -> !s.isBlank())
				.collect(Collectors.joining(" "));
		
		Set<String> genre=request.getGenre();
		
		return ResponseBookDto
				.builder()
				.title(book.getBookTitle())
				.description(book.getDescription())
				.authorName(name)
				.format(book.getBookformat())
				.genre(genre)
				.language(book.getBookLanguage())
				.chapterNumbers(book.getChapterNumber())
				.bookPricePhysical(book.getBookPriceDigital())
				.build();
				
	}
	
	public ResponseBookDto toResponseFromBook(Book book ) {
		String name=Stream
				.of(book.getBookAuthor().getFirstName()
				  , book.getBookAuthor().getLastName())
				.filter(Objects::nonNull)
				.filter(s -> !s.isBlank())
				.collect(Collectors.joining(" "));
		
		Set<String> genre = book.getGenres().stream()
	            .map(BookGenre::getName)
	            .collect(Collectors.toSet());
		
		return ResponseBookDto
				.builder()
				.title(book.getBookTitle())
				.description(book.getDescription())
				.authorName(name)
				.format(book.getBookformat())
				.genre(genre)
				.language(book.getBookLanguage())
				.chapterNumbers(book.getChapterNumber())
				.bookPricePhysical(book.getBookPriceDigital())
				.build();
				
	}
	
	
	
	public ResponsePhysicalBookDto toResponsePhysicalFromBook(Book book ,RequestPhysicalBookDto req) {
		String name=Stream
				.of(book.getBookAuthor().getFirstName()
				  , book.getBookAuthor().getLastName())
				.filter(Objects::nonNull)
				.filter(s -> !s.isBlank())
				.collect(Collectors.joining(" "));
				
		Set<String> genre=req.getGenre();
		
		return ResponsePhysicalBookDto
				.builder()
				.title(book.getBookTitle())
				.description(book.getDescription())
				.authorName(name)
				.format(book.getBookformat())
				.genre(genre)
				.language(book.getBookLanguage())
				.chapterNumbers(book.getChapterNumber())
				.bookPricePhysical(book.getBookPricePhysical())
				.totalCopies(req.getTotalCopies())
				.availableCopies(req.getAvailableCopies())
				.build();
				
	}
	
	public void transfer(Book book , UpdateRequestBookDto request) {
		
		book.setBookTitle(request.getBookTitle());
		book.setDescription(request.getDescription());
		book.setBookLanguage(request.getBookLanguage());
		book.setBookPriceDigital(request.getBookPriceDigital());
		book.setBookPricePhysical(request.getBookPricePhysical());
		book.setCoverImageUrl(request.getUrl());
		book.setChapterNumber(request.getChapterNumbers());
	}

	 public UpdateBookResponseDto toResponseUpdateFromBook(Book book, UpdateRequestBookDto requestBookDto) {
			String name=Stream
					.of(book.getBookAuthor().getFirstName()
					  , book.getBookAuthor().getLastName())
					.filter(Objects::nonNull)
					.filter(s -> !s.isBlank())
					.collect(Collectors.joining(" "));
			
			Set<String> genre = book.getGenres().stream()
		            .map(BookGenre::getName)
		            .collect(Collectors.toSet());
			
			return UpdateBookResponseDto
					.builder()
					.title(book.getBookTitle())
					.description(book.getDescription())
					.authorName(name)
					.format(book.getBookformat())
					.genre(genre)
					.language(book.getBookLanguage())
					.chapterNumbers(book.getChapterNumber())
					.bookPricePhysical(book.getBookPriceDigital())
					.build();
	}
}
