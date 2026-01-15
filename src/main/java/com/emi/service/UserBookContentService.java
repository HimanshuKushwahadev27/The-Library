package com.emi.service;

import com.emi.entity.UserBookContent;

import java.util.List;
import java.util.Optional;

import com.emi.dto.responseDto.ResponseUserBookContentDto;
public interface UserBookContentService {

	//recording a purchased content
    UserBookContent purchaseContent(Long userId, Long bookContentId, Long orderId);

    //get all purchase content for the user
    List<ResponseUserBookContentDto> getPurchasedContentsByUser(Long userId);

    //user may want to get a specific purchased content  via its id
    Optional<ResponseUserBookContentDto> getUserBookContent(Long userId, Long contentId);

	
}
