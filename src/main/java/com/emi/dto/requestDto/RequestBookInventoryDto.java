package com.emi.dto.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@XmlRootElement
@Data
@NoArgsConstructor
@Schema(description = "Request DTO for creating/updating Book Inventory")
public class RequestBookInventoryDto {
	   
	    @Schema(example = "100", description = "Total copies of the book")
	    private int totalCopies;

	    @Schema(example = "100", description = "Available copies of the book")
	    private int availableCopies;

}
