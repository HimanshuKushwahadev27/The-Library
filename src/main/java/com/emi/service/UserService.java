package com.emi.service;


import java.util.List;

import com.emi.dto.requestDto.UserRegisterRequest;
import com.emi.dto.responseDto.AdminUserResponseDto;
import com.emi.entity.User;
import com.emi.enums.Role;

public interface UserService {
	
	public void createUser(UserRegisterRequest user);
	public void createAuthorByUserId(Long id , Role role);
	public List<AdminUserResponseDto> getAllUserForAdmin();
	public User updateUser(Long id , User user);
	public void deleteUSer(Long id);
	public User findByEmail(String email);
	public boolean existByEmail(String email);
	public void changePassword(Long id , String oldPassword , String newPassword);
	
}
