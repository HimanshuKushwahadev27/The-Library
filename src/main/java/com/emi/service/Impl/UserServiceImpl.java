package com.emi.service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.emi.Repo.MembershipRepo;
import com.emi.dto.requestDto.UserRegisterRequest;
import com.emi.entity.Membership;
import com.emi.entity.User;
import com.emi.enums.PaidAccess;
import com.emi.enums.Role;
import com.emi.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final PasswordEncoder passwordEncoder;
	private final MembershipRepo membershipRepo;
		
	@Override
	public void createUser(UserRegisterRequest request) {
		var user=new User();
		  user.setEmail(request.getEmail());
		  user.setFirstName(request.getFirstname());
		  user.setLastName(request.getLastname());
		  user.setPassword(passwordEncoder.encode(request.getPassword()));
		  user.setRole(Role.USER);
		  user.setUpdatedAt(LocalDateTime.now());
		  user.setCreatedAt(LocalDateTime.now());
		  user.setPasswordExpireDate(LocalDateTime.now().plusMonths(3));
		  user.setAccountNonLocked(true);
		  user.setEnabled(true);
		  
		  Membership membership=getDefaultMembership();
		  
		  membership.setUser(user);
		  //saving the owning side
		membershipRepo.save(membership);	 
	}

	@Override
	public void createAuthorByUserId(Long id , Role role) {
		// TODO Auto-generated method stub
		return ;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(Long id, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUSer(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void changePassword(Long id, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub

	}
	
	
	//A DefaultMembership not recommended this way
	private Membership getDefaultMembership() {
		return Membership.builder()
				.accessType(PaidAccess.FREE)
				.autoRenew(true)
				.startDate(LocalDateTime.now())
				.endTime(LocalDateTime.now().plusMonths(1))
				.isActive(true)
				.build();
	}
	

}
