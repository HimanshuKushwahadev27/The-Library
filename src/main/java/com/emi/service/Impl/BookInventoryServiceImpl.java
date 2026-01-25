package com.emi.service.Impl;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emi.entity.BookInventory;
import com.emi.enums.BookFormat;
import com.emi.exceptions.ContentNotFoundException;
import com.emi.Repo.BookInventoryRepo;
import com.emi.Repo.BookRepo;
import com.emi.dto.requestDto.RequestBookInventoryDto;
import com.emi.dto.responseDto.ResponseBookInventoryDto;
import com.emi.entity.Book;
import com.emi.mapper.BookInventoryMapper;
import com.emi.service.BookInventoryService;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BookInventoryServiceImpl implements BookInventoryService {

	private final BookRepo bookRepo;
	private final BookInventoryRepo bookInventoryRepo;
	private final BookInventoryMapper bookInventoryMapper;
	
	@Override
	public BookInventory createInventory(RequestBookInventoryDto request , Book book) {
		
		BookInventory bookInv=bookInventoryMapper.crateSpecifiedInventory(request,book);
		
		return bookInv;	
	}

	@PreAuthorize("hasRole('AUTHOR')")
	@Override
	public ResponseBookInventoryDto updateInventory(Long bookId, int totalCopies) {
		
		Book book=bookRepo.findById(bookId)
				          .orElseThrow(() -> new ContentNotFoundException("Book Not found"));
		
		if(!book.getBookformat().contains(BookFormat.PHYSICAL)) {
			throw new IllegalStateException("Book is ony available in digital format");
		}
		
		BookInventory bookInv=bookInventoryRepo.findByBook_Id(bookId);
		
		bookInv.setTotalCopies(bookInv.getTotalCopies()+totalCopies);
		bookInventoryRepo.save(bookInv);
		return bookInventoryMapper.toResponseDto(bookInv);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public ResponseBookInventoryDto getInventoryByBook(Long bookId) {
		Book book=bookRepo.findById(bookId)
		          .orElseThrow(() -> new ContentNotFoundException("Book Not found"));

		if(!book.getBookformat().contains(BookFormat.PHYSICAL)) {
			throw new IllegalStateException("Book is ony available in digital format");
		}
		
		BookInventory bookInv=bookInventoryRepo.findByBook_Id(bookId);
		return bookInventoryMapper.toResponseDto(bookInv);	
	}

	@PreAuthorize("hasRole('AUTHOR')")
	@Override
	public ResponseBookInventoryDto increaseAvailableCopies(Long bookId, int amt) {
		Book book=bookRepo.findById(bookId)
		          .orElseThrow(() -> new ContentNotFoundException("Book Not found"));

		if(!book.getBookformat().contains(BookFormat.PHYSICAL)) {
			throw new IllegalStateException("Book is ony available in digital format");
		}
		
		BookInventory bookInv=bookInventoryRepo.findByBook_Id(bookId);
		
		bookInv.setAvailableCopies(bookInv.getAvailableCopies()+amt);
		return bookInventoryMapper.toResponseDto(bookInv);
	}

	@PreAuthorize("hasRole('USER')")
	@Override
	public ResponseBookInventoryDto decreaseAvailableCopies(Long bookId, int amt) {
		Book book=bookRepo.findById(bookId)
		          .orElseThrow(() -> new ContentNotFoundException("Book Not found"));

		if(!book.getBookformat().contains(BookFormat.PHYSICAL)) {
			throw new IllegalStateException("Book is ony available in digital format");
		}
		
		BookInventory bookInv=bookInventoryRepo.findByBook_Id(bookId);
		if(amt>bookInv.getAvailableCopies()) {
			throw new IllegalStateException("The selected amount is not available please decrease it ");
		}
		bookInv.setAvailableCopies(bookInv.getAvailableCopies()-amt);
		return bookInventoryMapper.toResponseDto(bookInv);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public List<ResponseBookInventoryDto> getAllInventories() {
		List<BookInventory> all=bookInventoryRepo.findAll();
		
		return all.stream().map(bookInventoryMapper::toResponseDto).toList();
	}

}
