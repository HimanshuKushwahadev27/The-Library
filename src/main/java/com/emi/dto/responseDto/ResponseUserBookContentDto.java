package com.emi.dto.responseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.emi.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ResponseUserBookContentDto {

	    private Long userBookContentId;
	    private Long bookId;
	    private String bookTitle;

	    private Long chapterId;
	    private String chapterTitle;
	    private Integer chapterNumber;
	    private String content;    
	    private BigDecimal pricePaid;

	    private LocalDateTime purchasedAt;
	    private OrderStatus status;
}
