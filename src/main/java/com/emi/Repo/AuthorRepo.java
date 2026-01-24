package com.emi.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.emi.entity.Author;

public interface AuthorRepo extends JpaRepository<Author,Long> {

	@Query("""
			SELECT a FROM Author a 
			WHERE a.user.email=:email
			""")
	Optional<Author> findAuthorByUserEmail(@Param("email") String email);
	
}

