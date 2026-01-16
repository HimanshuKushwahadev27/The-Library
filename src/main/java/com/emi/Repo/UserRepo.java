package com.emi.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
}
