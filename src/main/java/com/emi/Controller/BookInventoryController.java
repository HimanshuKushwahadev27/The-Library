package com.emi.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emi.dto.responseDto.ResponseBookInventoryDto;
import com.emi.service.BookInventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/Inventory")
@RequiredArgsConstructor
public class BookInventoryController {

	private final BookInventoryService bookInventoryService;
	
	
	@PutMapping("/update/{bookId}/{total}")
	public ResponseEntity<ResponseBookInventoryDto> updateIt(
			@PathVariable Long bookId,
			@PathVariable int total){
		return ResponseEntity.ok(bookInventoryService.updateInventory(bookId, total));
	}
	
	@GetMapping("/getBookInv/{bookId}")
	public ResponseEntity<ResponseBookInventoryDto> getBookInvById(
			@PathVariable Long bookId
			){
		return ResponseEntity.ok(bookInventoryService.getInventoryByBook(bookId));
	}
	
	@PutMapping("/inc/{bookId}/{total}")
	public ResponseEntity<ResponseBookInventoryDto> increaseIt(
			@PathVariable Long bookId,
			@PathVariable int total){
		return ResponseEntity.ok(bookInventoryService.increaseAvailableCopies(bookId, total));
	}
	
	@PutMapping("/dec/{bookId}/{total}")
	public ResponseEntity<ResponseBookInventoryDto> decreaseIt(
			@PathVariable Long bookId,
			@PathVariable int total){
		return ResponseEntity.ok(bookInventoryService.decreaseAvailableCopies(bookId, total));
	}
	
	
	@PutMapping("/get")
	public ResponseEntity<List<ResponseBookInventoryDto>> getAllByAdmin(){
		return ResponseEntity.ok(bookInventoryService.getAllInventories());
	}
	
}
