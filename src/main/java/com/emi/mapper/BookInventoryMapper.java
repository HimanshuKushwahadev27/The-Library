package com.emi.mapper;

import org.springframework.stereotype.Component;

import com.emi.dto.requestDto.RequestBookInventoryDto;
import com.emi.dto.responseDto.ResponseBookInventoryDto;
import com.emi.entity.Book;
import com.emi.entity.BookInventory;

@Component
public class BookInventoryMapper {

	public RequestBookInventoryDto getBasicInventory() {
		
		return RequestBookInventoryDto.builder()
				.availableCopies(1)
				.totalCopies(1)
				.build()
				;
	}
	
	public ResponseBookInventoryDto toResponseDto(BookInventory req) {
		
		return ResponseBookInventoryDto
				.builder()
				.bookId(req.getBook().getBookid())
				.bookTitle(req.getBook().getBookTitle())
				.totalCopies(req.getTotalCopies())
				.availableCopies(req.getAvailableCopies())
				.build()
				;
	}
	
	public BookInventory crateSpecifiedInventory(RequestBookInventoryDto dto, Book book) {
		return BookInventory.builder()
				.availableCopies(dto.getAvailableCopies())
				.totalCopies(dto.getTotalCopies())
				.book(book)
				.build()
				;
				
	}
}
