package com.emi.dto.responseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Lob;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@NoArgsConstructor
@Schema(description="Response for chapters")
public class ResponseBookContentDto {

	private String book;
    
	//author 
	private String user;
	
	private BigDecimal price;
	
	@Lob
	private String content;
	
	
	private Integer chapterNumber;
	
	private String cloudUrl;

	
	private String title;
			
	private LocalDateTime createdAt;
	private Boolean draft;
	
}
