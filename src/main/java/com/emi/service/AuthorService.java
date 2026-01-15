package com.emi.service;

import java.util.List;
import java.util.Optional;

import com.emi.dto.requestDto.RequestAuthordto;
import com.emi.dto.responseDto.ResponseAuthordto;

public interface AuthorService {

	public ResponseAuthordto becomeAuthor(Long userId, RequestAuthordto dto);
	
	ResponseAuthordto updateAuthorProfile(Long id , RequestAuthordto request);
	
	Optional<ResponseAuthordto> getAuthorById(Long id);
	
	Optional<ResponseAuthordto> getAuthorByUserId(Long userId);
	
	List<ResponseAuthordto> getAllAuthor();
}
