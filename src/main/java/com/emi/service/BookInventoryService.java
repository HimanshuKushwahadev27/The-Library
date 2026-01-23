	package com.emi.service;

import java.util.List;

import com.emi.dto.requestDto.RequestBookInventoryDto;
import com.emi.dto.responseDto.ResponseBookInventoryDto;
import com.emi.entity.Book;
import com.emi.entity.BookInventory;

public interface BookInventoryService {

	BookInventory createInventory(RequestBookInventoryDto request , Book book);
	ResponseBookInventoryDto updateInventory(Long bookId , int totalCopies);
	ResponseBookInventoryDto getInventoryByBook(Long bookid);
	ResponseBookInventoryDto increaseAvailableCopies(Long bookId , int amt);
    ResponseBookInventoryDto decreaseAvailableCopies(Long bookId, int amount);
    List<ResponseBookInventoryDto> getAllInventories();

}
