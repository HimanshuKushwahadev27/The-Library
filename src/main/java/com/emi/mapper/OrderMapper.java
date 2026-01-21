package com.emi.mapper;

import org.springframework.stereotype.Component;

import com.emi.dto.responseDto.ResponseOrderDto;
import com.emi.entity.Order;
import com.emi.enums.OrderStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrderMapper {

	private final UBCMapper ubcMapper;
	
	public ResponseOrderDto orderToResponseDto(Order order) {
		
		
		String title=order.getPurchasedContent()
				          .stream()
				          .map(ubc -> ubc.getContent().getTitle())
				          .findFirst()
				          .orElse(null);
		
		
		return ResponseOrderDto.builder()
				.pricePaid(order.getTotalAmt())
				.status(order.getStatus())
				.type(order.getOrdertype())
				.orderId(order.getOrderid())
				.bookTitle(title)
				.orderDate(order.getCreatedAt())
		     	.purchasedChapter(
						order.getPurchasedContent().stream()
						.map(ubcMapper::toUCBDto)
						.toList()
						)
				.build();
				
	}

	public boolean canCancel(OrderStatus status) {
		// TODO Auto-generated method stub
		return status==OrderStatus.PLACED ;
	}
}
