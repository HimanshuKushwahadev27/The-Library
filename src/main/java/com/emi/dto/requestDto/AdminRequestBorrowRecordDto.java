package com.emi.dto.requestDto;




import com.emi.enums.BookStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@NoArgsConstructor
@Schema(description="Abmin access borrow record")
public class AdminRequestBorrowRecordDto {

	
	
	@Schema(example="424" , description="Search on the basis of Book id")
	private Long bookId;
	
	@Schema(description="Search on the basis of the book status")
	private BookStatus status;
	

}
