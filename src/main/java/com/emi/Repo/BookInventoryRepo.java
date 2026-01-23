package com.emi.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.emi.entity.BookInventory;

public interface BookInventoryRepo extends JpaRepository<BookInventory, Long> {

	@Query("""
		SELECT b FROM BookInventory b
		WHERE b.book.book_id = :bookId	
			""")
	BookInventory findByBookId(Long bookId);
	
}
