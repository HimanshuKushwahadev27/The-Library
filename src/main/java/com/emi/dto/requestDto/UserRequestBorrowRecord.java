package com.emi.dto.requestDto;

import java.time.LocalDateTime;

import com.emi.enums.BookStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement
@Schema(description="User access borrow record")
public class UserRequestBorrowRecord {

	@Schema(example="Borrowed" , description="Status of the book")
	private BookStatus status;
	
	@Schema(description="Start date")
	private LocalDateTime fromDate;
	
	@Schema(description="End date")
	private LocalDateTime toDate;
}
