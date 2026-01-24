package com.emi.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emi.dto.responseDto.ResponseOrderDto;
import com.emi.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	
	@GetMapping("/getOrderbyId/{id}")
	@Operation(
				summary="Get all order information by its id" ,
			    description="orders endpoint"
			)
	public ResponseOrderDto getOrderByItsId(@PathVariable Long id){
		return orderService.getOrderById(id);
	}
	
	@GetMapping("/getAllOrderforuser")
	@Operation(
				summary="Get all orders information's by its user_id" ,
			    description="orders endpoint"
			)
	public List<ResponseOrderDto> getAllForUser(Principal principal){
		return orderService.getOrderByUserId(principal.getName());
	}
	
	@GetMapping("/getAllorderforAdmin")
	@Operation(
				summary="Get all orders informations" ,
			    description="orders endpoint"
			)
	public List<ResponseOrderDto> getAllOrder(){
		return orderService.getAllOrders();
	}
	
	@PostMapping("/cancel/{id}")
	@Operation(
				summary="cancel the order" ,
			    description="orders endpoint"
			)
	public void Ordercancel(@PathVariable Long id , Principal principal){
		orderService.cancelOrder(id , principal.getName());
	}
	
	@PostMapping("/updateStatus/{orderId}")
	@Operation(
			summary="update the status of order" ,
		    description="orders endpoint"
		)
	public ResponseOrderDto updateStatus(Long orderId ) {
		return null;
	}
	
	
	
	@GetMapping("/getAllOrderByBook/{id}")
	@Operation(
				summary="Get all orders informations from bookId" ,
			    description="orders endpoint"
			)
	public List<ResponseOrderDto> getAllOrderForBook(
			Principal principal,
			@PathVariable Long id){
		return orderService.getOrdersByBookId(id , principal.getName());
	}
	
}
