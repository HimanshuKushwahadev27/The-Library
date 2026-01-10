package com.emi.dto.responseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description="Response for the admins request to chapter query")
public class ResponseAdminChapterQueryDto {
	
	@Schema(example="14194810" , description="Gives order id to admin")
	private Long orderId;
	
	@Schema(example="23" , description="Gives user id to admin")
	private Long userId;
	
	@Schema(example="434" , description="Gives book id to admin")
	private Long bookId;
	
	@Schema(example="Essence of life" , description="Title of the book")
	private String bookTitle;
	
	@Schema(example="42" , description="Gives chapter id to admin")
	private Long chapterId;
	
	@Schema(example="Introduction" , description="Chapter name of the book")
	private String chapterTitle;
	
	@Schema(example="Rs.343" , description="Price paid by the user")
	private BigDecimal pricePaid;
	
	@Schema(example="Time" , description="Date of purchase")
	private LocalDateTime purchasedAt;
}
