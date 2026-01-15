package com.emi.dto.requestDto;



import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@NoArgsConstructor
@Schema(description="Request Book DTO")
public class RequestBookContentDto {

	@Schema(example="765" , description="Id of the book content")
	private Long bookContent_Id;
	
	@Schema(example="7635" , description="bookId of the book content")
	private Long bookId;
	
	@Schema(example="75" , description="userId of the book content")
	private Long userId;
	
	@Schema(example="765" , description="chapter number of the book content")
	private Integer chapterNumber;
	
	@Schema(example="Welcome" , description="Title of the book content")
	private String title;
	
	
	private String content;
	
	private BigDecimal price;
	
	private Boolean draft;
	
	private String cloudUrl; 
		
	private LocalDateTime createdAt;
}
