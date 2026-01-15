package com.emi.service;

import java.util.List;

import com.emi.dto.requestDto.RequestAdminChapterQueryDto;
import com.emi.dto.requestDto.RequestOrderDto;
import com.emi.dto.responseDto.ResponseAdminChapterQueryDto;
import com.emi.dto.responseDto.ResponseOrderDto;
import com.emi.enums.OrderStatus;

public interface OrderService {

	public ResponseOrderDto createOrder(RequestOrderDto order);
	public ResponseOrderDto getOrderById(Long id);
	public List<ResponseOrderDto>  getOrderByUserId(Long id);
	public List<ResponseOrderDto> getAllOrders();
	public void cancelOrder(Long orderId);
	public ResponseOrderDto updateOrderStatus(Long orderId , OrderStatus status);
	List<ResponseOrderDto> getOrdersByBookId(Long UserId);
	List<ResponseAdminChapterQueryDto> getChapterPurchaseRecord(RequestAdminChapterQueryDto req);

}
