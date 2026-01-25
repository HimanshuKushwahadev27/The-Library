package com.emi.Repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.emi.entity.BorrowRecord;
import com.emi.enums.BookStatus;

public interface BorrowRecordRepo extends JpaRepository<BorrowRecord, Long> {

	@Query("""
			SELECT b FROM BorrowRecord b
			WHERE b.user.user_id= :userId
			""")
	Page<BorrowRecord> findByUser_Id(
			Long userId,
			Pageable page);
	
	@Query("""
			SELECT b FROM BorrowRecord b
			WHERE b.user.user_id= :userId AND b.status=:status
			""")
    Page<BorrowRecord> findByUser_IdAndStatus(
    		Long userId,
    		BookStatus status,
    		Pageable pageable);
    
	@Query("""
			SELECT b FROM BorrowRecord b
			WHERE b.book.bookid= :bookId
			""")
    Page<BorrowRecord> findByBook_Id(
    		Long bookId,
    		Pageable pageable);

	@Query("""
			SELECT b FROM BorrowRecord b
			WHERE b.book.bookid= :bookId AND b.status = :status
			""")
	Page<BorrowRecord> findByBook_IdAndStatus(
			Long bookId,
			BookStatus status,
			Pageable pageable);

	@Query("""
			SELECT b FROM BorrowRecord b
			WHERE b.user.user_id= :userId AND b.book.bookid= :bookId
			""")
	Page<BorrowRecord> findByBook_IdAndUser(
			Long bookId,
			Long userId,
			Pageable pageable);
	
	@Query("""
			SELECT b FROM BorrowRecord b
			WHERE b.status = :status
			""")
	Page<BorrowRecord> findByStatus(
			BookStatus status,
			Pageable pageable);
	
	
}
