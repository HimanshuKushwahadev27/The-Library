package com.emi.service;

import java.util.List;
import java.util.Optional;

import com.emi.dto.requestDto.RequestBookContentDto;
import com.emi.dto.responseDto.ResponseBookContentDto;

//author centric
public interface BookContentService {

	ResponseBookContentDto createDraft(RequestBookContentDto request);
	
	ResponseBookContentDto updateDraft(RequestBookContentDto request);
	
	ResponseBookContentDto publishDraft(RequestBookContentDto request);
	
	//get all drafts of the author
    List<ResponseBookContentDto> getDraftsByAuthor(Long authorId);
    
    //get book contents by book id 
    List<ResponseBookContentDto> getContentsByBook(Long bookId);

    //single content by id 
    Optional<ResponseBookContentDto> getContentById(Long contentId);


}
