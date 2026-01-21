package com.emi.dto.requestDto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description="Admin request chapters purchased by the user")
public class RequestAdminChapterQueryDto {

	//all are optional
	@Schema(example="14194810" , description="Order's Id , it is optional")
	private Long orderId;
	
	@Schema(example="23" , description="Id of the user optional ")
	private Long userId;
	
	@Schema(example="434" , description="Id of the book  , it is optional")
	private Long bookId;
	
	@Schema(example="242" , description="Id of the chapter , it is optional")
	private Long chapterId;
	

	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
	
	
}

