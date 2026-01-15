package com.emi.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.entity.BorrowRecord;

public interface BorrowRecordRepo extends JpaRepository<BorrowRecord, Long> {

}
