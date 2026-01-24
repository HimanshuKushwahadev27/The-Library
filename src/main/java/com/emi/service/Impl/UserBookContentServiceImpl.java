package com.emi.service.Impl;

import java.util.List;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emi.Repo.OrderRepo;
import com.emi.Repo.UserRepo;
import com.emi.dto.responseDto.ResponseUserBookContentDto;
import com.emi.entity.User;
import com.emi.entity.UserBookContent;
import com.emi.enums.OrderStatus;
import com.emi.enums.OrderType;
import com.emi.exceptions.ContentNotFoundException;
import com.emi.exceptions.UserNotExistException;
import com.emi.mapper.UBCMapper;
import com.emi.service.OrderService;
import com.emi.service.UserBookContentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserBookContentServiceImpl implements UserBookContentService {

	private final OrderService orderService;
	private final UBCMapper ubcMapper;
	private final UserRepo userRepo;
	private final OrderRepo orderRepo;
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	@Override
	public List<ResponseUserBookContentDto> purchaseSingleContent( String email, Long contentId ) {
		
		User user=userRepo.
				findByEmail(email)
				.orElseThrow(() -> new UserNotExistException("Looks like you are not here") );
		
		var order=orderService.createOrder(
				               OrderType.Virtual,
                               OrderStatus.PLACED,
                               user,
                               Set.of(contentId)
                               );
                               
        return order.getPurchasedContent().stream().map(ubcMapper::toUCBDto).toList();
	}

	@PreAuthorize("hasRole('USER')")
	@Transactional
	@Override
	public List<ResponseUserBookContentDto> getPurchasedContentsByUser(String email) {
	
		User user=userRepo.findByEmail(email)
				          .orElseThrow(() -> new UserNotExistException("Looks like you are not here"));
		
		Set<UserBookContent> ubc=orderRepo.findAllPurchaseContentViaOrder(user.getUser_id());
		
		if(ubc==null) {
			throw new ContentNotFoundException("No chapters purchased");
		}
		return ubc.stream().map(ubcMapper::toUCBDto).toList();
		
	}

	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	@Override
	public ResponseUserBookContentDto getUserBookContent(String email, Long contentId) {
		
		User user=userRepo.findByEmail(email)
		          .orElseThrow(() -> new UserNotExistException("Looks like you are not here"));
		
		UserBookContent ubc=orderRepo.findByUserIdAndContentId(user.getUser_id(), contentId);
		if(ubc==null) {
			throw new ContentNotFoundException("No chapters purchased yet");
		}
		
		return ubcMapper.toUCBDto(ubc);
	}

	@PreAuthorize("hasRole('USER')")
	@Transactional
	@Override
	public List<ResponseUserBookContentDto> purchaseMultipleContent(String email , Set<Long> request) {
		
		User user=userRepo.findByEmail(email)
		          .orElseThrow(() -> new UserNotExistException("Looks like you are not here"));
		
		var order=orderService.createOrder(OrderType.Virtual,
				                            OrderStatus.PLACED,
				                            user,
				                            request);
		return order.getPurchasedContent().stream().map(ubcMapper::toUCBDto).toList();
	}

}
