package com.emi.service.Impl;

import java.util.List;
import java.util.Optional;

import com.emi.dto.requestDto.RequestBookContentDto;
import com.emi.dto.responseDto.ResponseBookContentDto;
import com.emi.service.BookContentService;

public class BookContentServiceImpl implements BookContentService {

	@Override
	public ResponseBookContentDto createDraft(RequestBookContentDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseBookContentDto updateDraft(RequestBookContentDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseBookContentDto publishDraft(RequestBookContentDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResponseBookContentDto> getDraftsByAuthor(Long authorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResponseBookContentDto> getContentsByBook(Long bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ResponseBookContentDto> getContentById(Long contentId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
