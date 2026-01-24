package com.emi.service.Impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emi.entity.UserBookContent;
import com.emi.Repo.AuthorRepo;
import com.emi.Repo.BookContentRepo;
import com.emi.Repo.BookRepo;
import com.emi.Repo.OrderRepo;
import com.emi.Repo.UserRepo;

import com.emi.dto.responseDto.ResponseOrderDto;
import com.emi.entity.Book;
import com.emi.entity.BookContent;
import com.emi.entity.Order;
import com.emi.entity.User;
import com.emi.enums.OrderStatus;
import com.emi.enums.OrderType;
import com.emi.enums.Role;
import com.emi.exceptions.ContentNotFoundException;
import com.emi.exceptions.UserNotExistException;
import com.emi.mapper.OrderMapper;
import com.emi.mapper.UBCMapper;
import com.emi.service.OrderService;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
	
	private final AuthorRepo authorRepo;
	private final BookRepo bookRepo;
	private final OrderMapper orderMapper;
	private final OrderRepo orderRepo;
	private final BookContentRepo bookContentRepo;
	private final UBCMapper ubcMapper;
	private final UserRepo userRepo;
	
	@Override
	public Order createOrder(
			            OrderType type,
			            OrderStatus status,
			            User user,
			            Set<Long> chapters) {
		
		var order=new Order();
		order.setCreatedAt(LocalDateTime.now());
		order.setStatus(status);
	    order.setOrdertype(type);
	    order.setUser(user);
	    orderRepo.save(order);
	    
	   
		
	    BigDecimal price=new BigDecimal(0.00);
	    Set<UserBookContent> store=new HashSet<>();
		for(Long id : chapters) {
			 BookContent  con=bookContentRepo.findById(id)
					       .orElseThrow(() -> new ContentNotFoundException("Chapter not found"));
			 
			 var ubc=ubcMapper.toUBCfromBC(con, order);
			 ubc.setUser(user);
			 price=price.add(ubc.getItemPrice());
			 order.addPurchasedContent(ubc);
			 store.add(ubc);
		}
		order.setTotalAmt(price);
		return order;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public ResponseOrderDto getOrderById(Long id) {
		
		Order order=orderRepo.findById(id).orElseThrow(() -> new ContentNotFoundException("Order not found by the given id"));
		
		return orderMapper.orderToResponseDto(order);
	}

	@PreAuthorize("hasRole('USER')")
	@Override
	public List<ResponseOrderDto> getOrderByUserId(String email) {
		User user=userRepo.findByEmail(email)
				          .orElseThrow(() -> new UserNotExistException("looks like u are not registered"));
		
		List<Order> orderInfo=orderRepo.findAllOrderByUserId(user.getUser_id());
		
		
		
		if(orderInfo==null) {
			throw new ContentNotFoundException("No Orders for you , make purchase");
		}
		
		return orderInfo.stream().map(orderMapper::orderToResponseDto).toList();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public List<ResponseOrderDto> getAllOrders() {
		List<Order> info=orderRepo.findAll();
		return info.stream().map(orderMapper::orderToResponseDto).toList();
	}

	@PreAuthorize("hasRole('USER')")
	@Override
	public void cancelOrder(Long orderId , String email) {
		
		Order order=orderRepo.findById(orderId).orElseThrow(() -> new ContentNotFoundException("Order id invalid"));
		
		User user=userRepo.findByEmail(email).orElseThrow(() -> new ContentNotFoundException("user not registered"));
		
		if(!order.getUser().getUser_id().equals(user.getUser_id())) {
			throw new AccessDeniedException("not your order");
		}
		
		if(!orderMapper.canCancel(order.getStatus())) {
			throw new IllegalStateException(
					"Virtual orders can't be canceled : " + order.getStatus()
					);
		}
		
		order.setStatus(OrderStatus.CANCELLED);
		
	}

	@Override
	public ResponseOrderDto updateOrderStatus(Long orderId) {
		return null;
	}

	@PreAuthorize("hasRole('AUTHOR')")
	@Override
	public List<ResponseOrderDto> getOrdersByBookId(Long bookId, String email) {
		
		Book book = bookRepo.findById(bookId)
				            .orElseThrow(() -> new ContentNotFoundException("Book is not available"));
		
		var user=userRepo
				.findByEmail(email)
				.orElseThrow(() -> new UserNotExistException("You are not registered as aithor"));
	
		if(user.getRole().contains(Role.AUTHOR) && !user.getRole().contains(Role.ADMIN)  ) {
			var author=authorRepo
					.findAuthorByUserEmail(email)
					.orElseThrow(() -> new UserNotExistException("You are not registered as aithor"));
             if(book.getBookAuthor().getAuthorId()!=author.getAuthorId()) {
            	 throw new AccessDeniedException("Its not your book");
             }
		}
		 
		List<Order> info= orderRepo.findAllOrderByBookId(bookId);
		
		return info.stream().map(orderMapper::orderToResponseDto).toList();
	}



}
