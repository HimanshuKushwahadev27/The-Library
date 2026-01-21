package com.emi.dto.responseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.emi.enums.OrderStatus;
import com.emi.enums.OrderType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@XmlRootElement
@Schema(description="Response for the order placed by user")
public class ResponseOrderDto {
   
	@Schema(example="323" , description="Id of the order")
	private Long orderId;

	@Schema(example="Enumeration" , description="Name of the book")
	private String bookTitle;
	
	@Schema(example="Virtual" , description="Hard copy or Soft copy")
	private OrderType type;
	
	@Schema(example="Rs. 24.90" , description="Price paid during placing order")
	private BigDecimal pricePaid;
	
	@Schema(example="Time" , description="Date at which order was placed")
	private LocalDateTime orderDate;
	
	@Schema(example="Completed" , description="Status of the order")
	private OrderStatus status;
	
	@Schema(description="Chapters purchased by the user")
	private List<ResponseUserBookContentDto> purchasedChapter;

}
