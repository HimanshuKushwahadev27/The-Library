	package com.emi.service;

import java.util.List;

import com.emi.dto.requestDto.RequestBookInventoryDto;
import com.emi.dto.responseDto.ResponseBookInventoryDto;

public interface BookInventoryService {

	ResponseBookInventoryDto createInventory(RequestBookInventoryDto request);
	ResponseBookInventoryDto updateInventory(Long bookId , int totalCopies);
	ResponseBookInventoryDto getInventoryByBook(Long bookid);
	ResponseBookInventoryDto increaseAvailableCopies(Long bookId , int amt);
    ResponseBookInventoryDto decreaseAvailableCopies(Long bookId, int amount, boolean isDigital);
    List<ResponseBookInventoryDto> getAllInventories();

}
