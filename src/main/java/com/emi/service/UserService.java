package com.emi.service;


import java.util.List;

import com.emi.dto.requestDto.PasswordUpdateRequest;
import com.emi.dto.requestDto.UserRegisterRequest;
import com.emi.dto.requestDto.UserUpdateRequestDto;
import com.emi.dto.responseDto.AdminUserResponseDto;
import com.emi.dto.responseDto.ResponseUserDto;
import com.emi.enums.Role;

public interface UserService {
	
	public void createUser(UserRegisterRequest user);
	public List<AdminUserResponseDto> getAllUserForAdmin();
	public ResponseUserDto updateUser( UserUpdateRequestDto user);
	public void deleteUser();
	public void changePassword( PasswordUpdateRequest req);
	void createAuthorByUserId( Role role);
	public ResponseUserDto getUser();
}
