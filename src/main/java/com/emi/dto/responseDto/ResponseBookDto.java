package com.emi.dto.responseDto;

import java.math.BigDecimal;

import com.emi.entity.BookGenre;
import com.emi.enums.BookFormat;
import com.emi.enums.BookLanguage;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@NoArgsConstructor
public class ResponseBookDto {

	private String title;
	private String description;
	private String authorName;
	
	private BookFormat format;
	private BookGenre genre;
	private BookLanguage language;
	
	private BigDecimal price;
}
