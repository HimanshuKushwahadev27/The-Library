package com.emi.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.emi.entity.BookGenre;

public interface BookGenreRepo extends JpaRepository<BookGenre , Long> {

	@Query("""
			SELECT g from BookGenre g 
			WHERE g.name =:name
			""")
	Optional<BookGenre> findByName(String name);

}
