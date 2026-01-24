package com.emi.service.Impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.emi.Repo.MembershipRepo;
import com.emi.Repo.UserRepo;
import com.emi.dto.requestDto.PasswordUpdateRequest;
import com.emi.dto.requestDto.UserRegisterRequest;
import com.emi.dto.requestDto.UserUpdateRequestDto;
import com.emi.dto.responseDto.AdminUserResponseDto;
import com.emi.dto.responseDto.ResponseUserDto;
import com.emi.entity.Membership;
import com.emi.entity.User;
import com.emi.enums.Role;
import com.emi.exceptions.UserNotExistException;
import com.emi.mapper.UserMapper;
import com.emi.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final MembershipRepo membershipRepo;
	private final UserRepo userRepo;
		
	@Override
	public void createUser(UserRegisterRequest request) {
		
		userRepo.findByEmail(request.getEmail()).ifPresent(u -> {
			throw new IllegalArgumentException("User already exist");
		});
		System.out.print("Register");
		
		Set<Role> role= new HashSet<>();
		role.add(Role.USER);
		var user=new User();
		  user.setEmail(request.getEmail());
		  user.setFirstName(request.getFirstname());
		  user.setLastName(request.getLastname());
		  user.setPassword(passwordEncoder.encode(request.getPassword()));
		  user.setRole(role);
		  user.setUpdatedAt(LocalDateTime.now());
		  user.setCreatedAt(LocalDateTime.now());
		  user.setPasswordExpireDate(LocalDateTime.now().plusMonths(3));
		  user.setAccountNonLocked(true);
		  user.setEnabled(true);
		  user.setDeletedUser(false);
		  
		  Membership membership=userMapper.getDefaultMembership();
		  
		  membership.setUser(user);
		  user.setMembership(membership);
		  
		  //saving the owning side
		membershipRepo.save(membership);	 
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Transactional
	@Override
	public List<AdminUserResponseDto> getAllUserForAdmin() {
		return userRepo.findAll()
				.stream()
				.map(userMapper::toAdminDto)
				.toList();	         
	}
	
	@PreAuthorize("hasRole('USER')")
	@Override
	public ResponseUserDto getUser() {
		
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String email=auth.getName();
		User user= userRepo.findByEmail(email)
				.orElseThrow(() -> new UserNotExistException("USer not found"));		
		return userMapper.toUserProfile(user);
	}

	@PreAuthorize("hasRole('USER')")
	@Transactional
	@Override
	public ResponseUserDto updateUser( UserUpdateRequestDto request) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String email=auth.getName();
			User user=userRepo.findByEmail(email)
					.orElseThrow(() -> new UserNotExistException("Looks like you are not here please register"));
			if(user.getFirstName()!=null) {
			user.setFirstName(request.getFirstname());
			}
			if(user.getLastName()!=null) {
			user.setLastName(request.getLastname());
			}
		return userMapper.toUserProfile(user);
	}

	@PreAuthorize("hasRole('USER')")
	@Transactional
	@Override
	public void deleteUser() {
		System.out.print("hello");
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String email=auth.getName();
         User user=userRepo.findByEmail(email)
        		 .orElseThrow(() -> new UserNotExistException("you do not exist"));
         
         if(user.isDeletedUser()) {
        	 throw new IllegalStateException("User already deleted");
         }
         
         user.setEnabled(false);
         user.setDeletedUser(true);
         user.setPasswordExpireDate(LocalDateTime.now());
         
	}

	@PreAuthorize("hasRole('USER')")
	@Transactional
	@Override
	public void changePassword( PasswordUpdateRequest request) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String email=auth.getName();
		User user=userRepo.findByEmail(email)
				.orElseThrow(() -> new UserNotExistException("User do not Exist"));
		
		if(!passwordEncoder.matches(request.getOldPassword(),user.getPassword())){
			throw new BadCredentialsException("Please provide the correct password");
		}
		if(request.getNewPassword()!=null) {
			user.setPassword(passwordEncoder.encode(request.getNewPassword()));
			user.setPasswordExpireDate(LocalDateTime.now().plusMonths(3));
			user.setUpdatedAt(LocalDateTime.now());
		}
	}

	@Transactional
	@Override
	public User createAuthorByUserId( User user) {
		
		Set<Role> role=user.getRole();
		role.add(Role.AUTHOR);
		user.setRole(role);
		 userRepo.save(user);
		return user;
	}


}
