package com.emi.service;

import java.util.List;

import com.emi.dto.requestDto.RequestBookContentDto;
import com.emi.dto.responseDto.ResponseBookContentDto;

//author centric
public interface BookContentService {

	ResponseBookContentDto createDraft(RequestBookContentDto request);
	
	ResponseBookContentDto updateDraft(RequestBookContentDto request , String email);
	
	ResponseBookContentDto publishDraft(RequestBookContentDto request  , String email);
	
	//get all drafts of the author
    List<ResponseBookContentDto> getDraftsByAuthor(Long authorId);
    
    //get book contents by book id 
    List<ResponseBookContentDto> getContentsByBook(Long bookId);

    //single content by id 
    ResponseBookContentDto getContentById(Long contentId);


}
