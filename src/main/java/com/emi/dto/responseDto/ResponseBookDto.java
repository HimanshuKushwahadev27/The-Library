package com.emi.dto.responseDto;

import java.math.BigDecimal;
import java.util.Set;

import com.emi.entity.BookGenre;
import com.emi.enums.BookFormat;
import com.emi.enums.BookLanguage;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement
@Builder
@AllArgsConstructor
public class ResponseBookDto {

	private String title;
	private String description;
	private String authorName;
	
	private Set<BookFormat> format;
	private Set<BookGenre> genre;
	private BookLanguage language;
	
	private Integer chapterNumbers;
	
	private BigDecimal bookPriceDigital;
	private BigDecimal bookPricePhysical;

	
}
