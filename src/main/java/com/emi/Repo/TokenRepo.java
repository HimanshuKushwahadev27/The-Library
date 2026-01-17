package com.emi.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.emi.entity.Token;

public interface TokenRepo extends JpaRepository<Token, Long> {

	@Query("""
			select t from Token t where
			t.user.user_id = :user_id and t.expired=false and t.revoked=false
			""")
	List<Token> findAllValidTokenByUser(Long user_id);
	
	Optional<Token> findByToken(String token);
}
