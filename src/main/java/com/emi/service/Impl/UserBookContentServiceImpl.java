package com.emi.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.emi.Repo.OrderRepo;
import com.emi.dto.responseDto.ResponseUserBookContentDto;
import com.emi.entity.UserBookContent;
import com.emi.service.UserBookContentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserBookContentServiceImpl implements UserBookContentService {

	private final OrderRepo orderRepo;
	@Override
	public UserBookContent purchaseContent(String email, Long contentOrderId) {
		
	}

	@Override
	public List<ResponseUserBookContentDto> getPurchasedContentsByUser(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ResponseUserBookContentDto> getUserBookContent(String email, Long contentId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<UserBookContent> purchaseMultipleContent(String email, Set<Long> contents) {
		// TODO Auto-generated method stub
		return null;
	}

}
