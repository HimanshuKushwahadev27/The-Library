package com.emi.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
