package com.emi.service.Impl;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.emi.Repo.AuthorRepo;
import com.emi.Repo.UserRepo;
import com.emi.dto.requestDto.RequestAuthordto;
import com.emi.dto.responseDto.ResponseAuthordto;
import com.emi.entity.Author;
import com.emi.entity.User;
import com.emi.enums.Role;
import com.emi.exceptions.UserNotExistException;
import com.emi.mapper.AuthorMapper;
import com.emi.service.AuthorService;
import com.emi.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorServiceImpl implements AuthorService{
	
	private final UserRepo userRepo;
	private final AuthorRepo authorRepo;
    private final UserService userService;
    private final AuthorMapper authorMapper;
	
	@PreAuthorize("hasRole('USER')")
	@Override
	public ResponseAuthordto becomeAuthor(String email, RequestAuthordto dto) {
		
		var user=userRepo
				.findByEmail(email)
				.orElseThrow(() -> new UserNotExistException("Please Register yourself"));
		
		if(user.getRole().contains(Role.AUTHOR)) {
			throw new IllegalStateException("User is already an Author");
		}
		user=userService.createAuthorByUserId(user);
		Author author=authorMapper.fromUserToAuthor(user, dto);
		return authorMapper.fromAuthorToResponse(author);
	}

	@PreAuthorize("hasRole('AUTHOR')")
	@Override
	public ResponseAuthordto updateAuthorProfile(String email, RequestAuthordto request) {
	
		Author author = authorRepo.findAuthorByUserEmail(email)
				.orElseThrow(() -> new  UserNotExistException("Author is not here"));
		
		authorMapper.transfer(author,request);
		authorRepo.save(author);
		return authorMapper.fromAuthorToResponse(author);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public ResponseAuthordto getAuthorById(Long id) {
		Author author=authorRepo.findById(id)
				.orElseThrow(() -> new  UserNotExistException("Author is not here"));
		
		return authorMapper.fromAuthorToResponse(author);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public ResponseAuthordto getAuthorByUserId(Long userId) {
		User user=userRepo.findById(userId)
				.orElseThrow(() -> new  UserNotExistException("Author is not here"));
		
		if(!user.getRole().contains(Role.AUTHOR)) {
			throw new IllegalStateException("User is not an Author");
		}
		
		Author author = authorRepo.findAuthorByUserEmail(user.getEmail())
				.orElseThrow(() -> new  UserNotExistException("Author is not here"));
		
		return authorMapper.fromAuthorToResponse(author);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public List<ResponseAuthordto> getAllAuthor() {
		List<Author> author =authorRepo.findAll();
		return author.stream().map(authorMapper::fromAuthorToResponse).toList();
	}



}
