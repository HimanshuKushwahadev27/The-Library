package com.emi.service.Impl;

import java.util.List;

import com.emi.dto.requestDto.RequestBookInventoryDto;
import com.emi.dto.responseDto.ResponseBookInventoryDto;
import com.emi.service.BookInventoryService;

public class BookInventoryServiceImpl implements BookInventoryService {

	@Override
	public ResponseBookInventoryDto createInventory(RequestBookInventoryDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseBookInventoryDto updateInventory(Long bookId, int totalCopies) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseBookInventoryDto getInventoryByBook(Long bookid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseBookInventoryDto increaseAvailableCopies(Long bookId, int amt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseBookInventoryDto decreaseAvailableCopies(Long bookId, int amount, boolean isDigital) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResponseBookInventoryDto> getAllInventories() {
		// TODO Auto-generated method stub
		return null;
	}

}
