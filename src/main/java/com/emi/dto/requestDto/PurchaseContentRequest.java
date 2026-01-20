package com.emi.dto.requestDto;


import java.util.Set;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@NoArgsConstructor
@Schema(description="purchas content multiple")
public class PurchaseContentRequest {
	
    @NotEmpty(message = "Content list cannot be empty")
    private Set<Long> contentIds;

}
