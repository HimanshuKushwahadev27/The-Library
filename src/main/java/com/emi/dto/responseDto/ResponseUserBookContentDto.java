package com.emi.dto.responseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
	    private String status;
}
