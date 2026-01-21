package com.emi.service;

import java.util.List;
import java.util.Set;

import com.emi.dto.requestDto.RequestAdminChapterQueryDto;
import com.emi.dto.responseDto.ResponseAdminChapterQueryDto;
import com.emi.dto.responseDto.ResponseOrderDto;
import com.emi.entity.Order;
import com.emi.entity.User;
import com.emi.enums.OrderStatus;
import com.emi.enums.OrderType;

public interface OrderService {

	public Order createOrder(  
			OrderType type,
			OrderStatus status,
            User user,
            Set<Long> chapters);
	public ResponseOrderDto getOrderById(Long id);
	public List<ResponseOrderDto>  getOrderByUserId(String email);
	public List<ResponseOrderDto> getAllOrders();
	public void cancelOrder(Long orderId , String email);
	public ResponseOrderDto updateOrderStatus(Long orderId );
	List<ResponseOrderDto> getOrdersByBookId(Long UserId);
	List<ResponseAdminChapterQueryDto> getChapterPurchaseRecord(RequestAdminChapterQueryDto req);

}
