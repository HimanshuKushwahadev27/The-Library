package com.emi.dto.responseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@NoArgsConstructor
@Schema(description = "Response DTO for Book Inventory")
public class ResponseBookInventoryDto {

	   @Schema(example = "101", description = "Inventory Id")
	    private Long bookInventoryId;

	    @Schema(example = "202", description = "Id of the book")
	    private Long bookId;

	    @Schema(example = "Effective Java", description = "Title of the book")
	    private String bookTitle;

	    @Schema(example = "100", description = "Total copies of the book")
	    private int totalCopies;

	    @Schema(example = "50", description = "Available copies of the book")
	    private int availableCopies;
}
