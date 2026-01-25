package com.emi.dto.requestDto;

import java.math.BigDecimal;
import java.util.Set;


import com.emi.enums.BookFormat;
import com.emi.enums.BookLanguage;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;


@XmlRootElement
@Data
@NoArgsConstructor
@Schema(description="Request Book DTO")
public class UpdateRequestBookDto {

	@Schema(example="Essential of Life" , description="Title of the book")
	private String bookTitle;
	
	@Schema(example="This book provides the basic morals and lessons of life" , description="Description of the book")
	private String description;
	
	
	@Schema(example="English" , description="Language in which the book is provided")
	private BookLanguage bookLanguage;
	
	@Schema(example="Romance" , description="Content type of the book")
	private Set<String> genre;
	
	@Schema(example="Rs. 345" , description="Hard copy of the book")
	private BigDecimal bookPriceDigital;
	
	@Schema(example="Rs. 10" , description="Soft copy of the book")
	private BigDecimal bookPricePhysical;
 
	@Schema(example="www.myUrl.in" , description="Url of the cover image if available")
	private String url;
	
	@Schema(example="3424" , description="Total chapters in the book")
	private Integer chapterNumbers;
}
