package com.emi.service;


import java.util.List;

import com.emi.dto.requestDto.PasswordUpdateRequest;
import com.emi.dto.requestDto.UserRegisterRequest;
import com.emi.dto.requestDto.UserUpdateRequestDto;
import com.emi.dto.responseDto.AdminUserResponseDto;
import com.emi.dto.responseDto.ResponseUserDto;
import com.emi.entity.User;

public interface UserService {
	
	public void createUser(UserRegisterRequest user);
	public List<AdminUserResponseDto> getAllUserForAdmin();
	public ResponseUserDto updateUser( UserUpdateRequestDto user);
	public void deleteUser();
	public void changePassword( PasswordUpdateRequest req);
	User createAuthorByUserId(  User user1);
	public ResponseUserDto getUser();
}
