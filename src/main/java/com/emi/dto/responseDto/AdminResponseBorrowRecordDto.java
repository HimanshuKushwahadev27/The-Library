package com.emi.dto.responseDto;

import java.time.LocalDateTime;

import com.emi.enums.BookStatus;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AdminResponseBorrowRecordDto {

	private String bookTitle;
	
	private LocalDateTime borrowDate;
	
	private LocalDateTime dueDate;
	
	private LocalDateTime returnDate;
	
	private BookStatus status;
	
	private Long userId;
	
	private Long bookId;
	
	
}
