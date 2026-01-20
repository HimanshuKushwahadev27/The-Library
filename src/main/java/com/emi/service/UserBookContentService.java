package com.emi.service;

import com.emi.entity.UserBookContent;

import java.util.List;
import java.util.Set;

import com.emi.dto.responseDto.ResponseUserBookContentDto;
public interface UserBookContentService {

	//recording a purchased content
    UserBookContent purchaseSingleContent( String email, Long orderId);

    //get all purchase content for the user
    List<ResponseUserBookContentDto> getPurchasedContentsByUser(String email);

    //user may want to get a specific purchased content  via its id
    ResponseUserBookContentDto getUserBookContent(String email, Long contentId);

	List<ResponseUserBookContentDto> purchaseMultipleContent(String email , Set<Long> contents);
}
