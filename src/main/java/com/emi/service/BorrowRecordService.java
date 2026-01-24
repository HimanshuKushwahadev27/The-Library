package com.emi.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.emi.dto.requestDto.AdminRequestBorrowRecordDto;
import com.emi.dto.requestDto.UserRequestBorrowRecord;
import com.emi.dto.responseDto.AdminResponseBorrowRecordDto;
import com.emi.dto.responseDto.ResponseBorrowRecordDto;

public interface BorrowRecordService {
	
	Page<ResponseBorrowRecordDto> getUserBorrowRecord(
			String email,
			Pageable pageable);
	
	Page<AdminResponseBorrowRecordDto> getBorrowRecordsByBookId(
			AdminRequestBorrowRecordDto filter,
			Pageable pageable);

	Page<ResponseBorrowRecordDto> getUserBorrowRecordWithStatus(
			String email,
			UserRequestBorrowRecord filter,
			Pageable pageable);

	Page<AdminResponseBorrowRecordDto> getBorrowRecordsByBookIdAndStatus(
			AdminRequestBorrowRecordDto filter,
			Pageable pageable);

	Page<AdminResponseBorrowRecordDto> getBorrowRecordsByUserForAdmin(
			String email,
			AdminRequestBorrowRecordDto filter,
			Pageable pageable);
	
	Page<AdminResponseBorrowRecordDto> getBorrowRecordsByStatusForAdmin(
			AdminRequestBorrowRecordDto filter,
			Pageable pageable);

	
}
