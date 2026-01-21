package com.emi.dto.requestDto;



import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Request to purchase a single book content")
public class PurchaseSingleContentRequest {

    @Schema(
        example = "342",
        description = "ID of the book content to purchase",
        required = true
    )
    private Long bookContentId;
}