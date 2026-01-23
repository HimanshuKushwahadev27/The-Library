package com.emi.dto.requestDto;

import java.math.BigDecimal;
import java.util.Set;

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
@Schema(description="Request physical Book DTO")
public class RequestPhysicalBookDto {

	
	@Schema(example="100" , description="Max copies u can provide")
	private Integer totalCopies;
	
	@Schema(example="40" , description="copies remaining after few were sold")
	private Integer availableCopies;
	
	@Schema(example="Essential of Life" , description="Title of the book")
	private String bookTitle;
	
	@Schema(example="This book provides the basic morals and lessons of life" , description="Description of the book")
	private String description;
	
	@Schema(example="6821596489325" , description="Unique id of the book(13 digits)")
	private String isbn;
	
	@Schema(example="English" , description="Language in which the book is provided")
	private BookLanguage bookLanguage;
	
	@Schema(example="Romance" , description="Content type of the book")
	private Set<BookGenre> genre;
	
	@Schema(example="Rs. 345" , description="Hard copy of the book")
	private BigDecimal bookPriceDigital;
	
	@Schema(example="Rs. 10" , description="Soft copy of the book")
	private BigDecimal bookPricePhysical;

	@Schema(example="Physical" , description="Is the book is in Physical or Virtual format")
	private Set<BookFormat> format;
 
	@Schema(example="www.myUrl.in" , description="Url of the cover image if available")
	private String url;
	
	@Schema(example="3424" , description="Total chapters in the book")
	private Integer chapterNumbers;
}
