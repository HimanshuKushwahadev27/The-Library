package com.emi.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.emi.dto.responseDto.ResponseUserBookContentDto;
import com.emi.entity.BookContent;
import com.emi.entity.Order;
import com.emi.entity.UserBookContent;
import com.emi.enums.OrderStatus;

@Component
public class UBCMapper {

	public ResponseUserBookContentDto toUCBDto(UserBookContent ubc) {
		
		return ResponseUserBookContentDto.builder()
				.bookId(ubc.getContent().getBook().getBookid())
				.bookTitle(ubc.getContent().getBook().getBookTitle())
				.chapterId(ubc.getContent().getBookContent_Id())
				.chapterTitle(ubc.getContent().getTitle())
				.content(ubc.getContent().getContent())
				.pricePaid(ubc.getItemPrice())
				.purchasedAt(ubc.getPurchasedDate())
				.status(ubc.getStatus())
				.build();
	}
	
	
	public UserBookContent toUBCfromBC(BookContent content , Order order) {
		return UserBookContent
				.builder()
				.content(content)
				.itemPrice(content.getPrice())
				 .purchasedDate(LocalDateTime.now())
				 .status(OrderStatus.PLACED)
				 .order(order)
				 .build();
	}
}
