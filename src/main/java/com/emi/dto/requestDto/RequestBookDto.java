package com.emi.dto.requestDto;

import java.math.BigDecimal;

import com.emi.entity.BookGenre;
import com.emi.enums.BookFormat;
import com.emi.enums.BookLanguage;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@NoArgsConstructor
@Schema(description="Request Book DTO")
public class RequestBookDto {

	@Schema(example="Essential of Life" , description="Title of the book")
	@NotBlank(message="Please provide the title for book")
	private String bookTitle;
	
	@Schema(example="This book provides the basic morals and lessons of life" , description="Description of the book")
	private String description;
	
	@Schema(example="6821596489325" , description="Unique id of the book(13 digits)")
	@NotBlank(message="Please provide the isbn for book")
	private String isbn;
	
	@Schema(example="English" , description="Language in which the book is provided")
	@NotNull(message="please provide the language fror the book")
	private BookLanguage bookLanguage;
	
	@Schema(example="Romance" , description="Content type of the book")
	@NotNull(message="provide the genre for book")
	private BookGenre genre;
	
	@Schema(example="Rs. 345" , description="Hard copy of the book")
	private BigDecimal bookPriceDigital;
	
	@Schema(example="Rs. 10" , description="Soft copy of the book")
	private BigDecimal bookPricePhysical;

	@Schema(example="Physical" , description="Is the book is in Physical or Virtual format")
	@NotNull(message="provide the format for book")
	private BookFormat format;
 
}
