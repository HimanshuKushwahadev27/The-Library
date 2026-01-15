package com.emi.dto.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@NoArgsConstructor
@Schema(description = "Request DTO for creating/updating Book Inventory")
public class RequestBookInventoryDto {
	
	   @Schema(example = "101", description = "Id of the book")
	    private Long bookId;

	    @Schema(example = "100", description = "Total copies of the book")
	    private int totalCopies;

	    @Schema(example = "100", description = "Available copies of the book")
	    private int availableCopies;

}
