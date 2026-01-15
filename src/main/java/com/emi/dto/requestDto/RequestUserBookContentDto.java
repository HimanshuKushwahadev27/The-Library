package com.emi.dto.requestDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.emi.enums.OrderStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement
@Schema(description="Request for user book content")
public class RequestUserBookContentDto {

	@Schema(example="443" , description="Id of the user")
	private Long userId;
	
	@Schema(example="342" , description="Id of the book")
	private Long bookContentId;
	
	@Schema(example="4442" , description="Id of the order")
	private Long orderId;
	
	@Schema(example="443" , description="price paid")
	private BigDecimal itemPrice;
	
	@Schema(example="STATUS" , description="status of the order")
	private OrderStatus status;
	private LocalDateTime purchaseDate;
}
