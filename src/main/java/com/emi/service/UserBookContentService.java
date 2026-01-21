package com.emi.service;


import java.util.List;
import java.util.Set;

import com.emi.dto.responseDto.ResponseUserBookContentDto;
public interface UserBookContentService {

	//recording a purchased content
	List<ResponseUserBookContentDto> purchaseSingleContent( String email, Long contentId );

    //get all purchase content for the user
    List<ResponseUserBookContentDto> getPurchasedContentsByUser(String email);

    //user may want to get a specific purchased content  via its id
    ResponseUserBookContentDto getUserBookContent(String email, Long contentId);

	List<ResponseUserBookContentDto> purchaseMultipleContent(String email , Set<Long> contents);
}
