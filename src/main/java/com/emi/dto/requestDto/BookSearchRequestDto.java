package com.emi.dto.requestDto;

import java.math.BigDecimal;

import com.emi.entity.BookGenre;
import com.emi.enums.BookFormat;
import com.emi.enums.BookLanguage;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@NoArgsConstructor
@Schema(description="Search Book DTO")
public class BookSearchRequestDto {
	
	@Schema(example="title , desc , authName" , description="Description of the book")
	private String query;
	
	@Schema(example="English" , description="Language in which the book is provided")
	private BookLanguage bookLanguage;
	
	@Schema(example="Romance" , description="Content type of the book")
	private BookGenre genre;

	@Schema(example="0" , description="min price range")
	private BigDecimal minPrice;
	
	@Schema(example="1000" , description="max price range")
	private BigDecimal maxPrice;
	
	@Schema(example="Physical" , description="Is the book is in Physical or Virtual format")
	private BookFormat format;
 

}
