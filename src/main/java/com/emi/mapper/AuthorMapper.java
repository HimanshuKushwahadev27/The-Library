package com.emi.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.emi.dto.requestDto.RequestAuthordto;
import com.emi.dto.responseDto.ResponseAuthordto;
import com.emi.entity.Author;
import com.emi.entity.User;

@Component
public class AuthorMapper {

	public Author fromUserToAuthor(User user , RequestAuthordto dto) {
		return Author
				.builder()
				.user(user)
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.penName(dto.getPenName())
				.bio(dto.getBio())
				.joinedAt(LocalDateTime.now())
				.profileURL(dto.getUrl())
				.build()
				;
	}
	
	public ResponseAuthordto fromAuthorToResponse(Author author) {
		return ResponseAuthordto.builder()
				.penName(author.getPenName())
				.joinedAt(author.getJoinedAt())
				.membership(author.getUser().getMembership())
				.bio(author.getBio())
				.Role(author.getUser().getRole())
				.build()
				;
	}

	public void transfer(Author author, RequestAuthordto request) {
          		author.setPenName(request.getPenName());
          		author.setBio(request.getBio());
          		author.setProfileURL(request.getUrl());
	}
}
