package com.emi.service;


import java.util.List;

import com.emi.dto.requestDto.UserRegisterRequest;
import com.emi.entity.User;

public interface UserService {
	
	public User createUSer(UserRegisterRequest user);
	public User createUserById(Long id);
	public List<User> getAllUser();
	public User updateUser(Long id , User user);
	public void deleteUSer(Long id);
	public User findByEmail(String email);
	public boolean existByEmail(String email);
	public void changePassword(Long id , String oldPassword , String newPassword);
	
}
