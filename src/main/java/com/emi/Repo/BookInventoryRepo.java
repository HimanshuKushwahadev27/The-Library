package com.emi.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.emi.entity.BookInventory;

public interface BookInventoryRepo extends JpaRepository<BookInventory, Long> {

	@Query("""
		SELECT b FROM BookInventory b
		WHERE b.book.bookid = :bookId	
			""")
	BookInventory findByBook_Id(@Param("bookId")Long bookId);
	
}
