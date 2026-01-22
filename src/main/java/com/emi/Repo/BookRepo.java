package com.emi.Repo;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.emi.entity.Book;
import com.emi.enums.BookStatus;

public interface BookRepo extends JpaRepository<Book, Long>  , JpaSpecificationExecutor<Book>{

	@Query("""
			SELECT b FROM Book b
			WHERE b.isbn = :isbn
			""")
	boolean findByIsbnNumber(@Param("isbn")String isbn);

	Optional<Book> findByIdAndStatus(BookStatus status , Long bookId);
	
	List<Book> findByStatus(BookStatus status);

}
