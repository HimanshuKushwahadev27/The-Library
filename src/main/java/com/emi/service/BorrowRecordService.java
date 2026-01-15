package com.emi.service;

import java.util.List;

import com.emi.dto.requestDto.AdminRequestBorrowRecordDto;
import com.emi.dto.requestDto.UserRequestBorrowRecord;
import com.emi.dto.responseDto.ResponseBorrowRecordDto;

public interface BorrowRecordService {
	
	List<ResponseBorrowRecordDto> getUserBorrowRecord(
			Long userId,
			UserRequestBorrowRecord filter);
	
	List<ResponseBorrowRecordDto> getBorrowRecordsByBookId(
			Long bookId ,
			AdminRequestBorrowRecordDto filter);

	
}
