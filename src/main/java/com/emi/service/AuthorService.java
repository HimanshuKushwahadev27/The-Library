package com.emi.service;

import java.util.List;

import com.emi.dto.requestDto.RequestAuthordto;
import com.emi.dto.responseDto.ResponseAuthordto;

public interface AuthorService {

	public ResponseAuthordto becomeAuthor(String email, RequestAuthordto dto);
	
	ResponseAuthordto updateAuthorProfile(String email , RequestAuthordto request);
	
	ResponseAuthordto getAuthorById(Long id);
	
	ResponseAuthordto getAuthorByUserId(Long userId);
	
	List<ResponseAuthordto> getAllAuthor();
	

}
