package com.emi.mapper;

import org.springframework.stereotype.Component;

import com.emi.dto.responseDto.AdminResponseBorrowRecordDto;
import com.emi.dto.responseDto.ResponseBorrowRecordDto;
import com.emi.entity.BorrowRecord;

@Component
public class BorrowRecordMapper {

	public ResponseBorrowRecordDto borrowToDto(BorrowRecord record) {
		return ResponseBorrowRecordDto
				.builder()
				.bookTitle(record.getBook().getBookTitle())
				.borrowDate(record.getBorrowDate())
				.dueDate(record.getDueDate())
				.returnDate(record.getReturnDate())
				.status(record.getStatus())
				.build()
				;
	}
	
	public AdminResponseBorrowRecordDto adminDto(BorrowRecord record) {
		return AdminResponseBorrowRecordDto.builder()
				.bookTitle(record.getBook().getBookTitle())
				.borrowDate(record.getBorrowDate())
				.dueDate(record.getDueDate())
				.returnDate(record.getReturnDate())
				.status(record.getStatus())
				.bookId(record.getBook().getBookid())
				.userId(record.getUser().getUser_id())
				.build()
				;
	}
}

