package com.emi.Repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.entity.BorrowRecord;
import com.emi.enums.BookStatus;

public interface BorrowRecordRepo extends JpaRepository<BorrowRecord, Long> {

	
	Page<BorrowRecord> findByUser_Id(
			Long userId,
			Pageable page);
	
    Page<BorrowRecord> findByUser_IdAndStatus(
    		Long userId,
    		BookStatus status,
    		Pageable pageable);
    
    Page<BorrowRecord> findByBook_Id(
    		Long bookId,
    		Pageable pageable);

	Page<BorrowRecord> findByBook_IdAndStatus(
			Long bookId,
			BookStatus status,
			Pageable pageable);

	Page<BorrowRecord> findByBook_IdAndUser(
			Long bookId,
			Long userId,
			Pageable pageable);
}
