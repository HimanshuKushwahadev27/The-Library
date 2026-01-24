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
	boolean existByIsbnNumber(@Param("isbn")String isbn);

	@Query("""
			SELECT b from Book b 
			WHERE b.bookStatus = :status AND b.book_id =:bookId
			""")
	Optional<Book> findByIdAndStatus(BookStatus status , Long bookId);
	
	
	@Query("""
			SELECT b FROM Book b 
			WHERE b.bookStatus = :status
			""")
	List<Book> findByStatus(BookStatus status);

}
