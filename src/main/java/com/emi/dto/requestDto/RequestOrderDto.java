package com.emi.dto.requestDto;


import com.emi.enums.OrderType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@XmlRootElement
@NoArgsConstructor
@Schema(description="Order request DTO")
public class RequestOrderDto {
	
	@Schema(example="453" , description="Id of the book")
	@NotNull
	private Long book_Id;
	
		
	@Schema(example="Virtual" , description="Type of order physical or read online")
	@NotNull
	private OrderType type;

}
