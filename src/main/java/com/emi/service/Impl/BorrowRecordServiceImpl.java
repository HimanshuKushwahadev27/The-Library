package com.emi.service.Impl;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emi.Repo.BookRepo;
import com.emi.Repo.BorrowRecordRepo;
import com.emi.Repo.UserRepo;
import com.emi.dto.requestDto.AdminRequestBorrowRecordDto;
import com.emi.dto.requestDto.UserRequestBorrowRecord;
import com.emi.dto.responseDto.AdminResponseBorrowRecordDto;
import com.emi.dto.responseDto.ResponseBorrowRecordDto;
import com.emi.entity.BorrowRecord;
import com.emi.entity.User;
import com.emi.exceptions.ContentNotFoundException;
import com.emi.mapper.BorrowRecordMapper;
import com.emi.service.BorrowRecordService;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class BorrowRecordServiceImpl implements BorrowRecordService {

	private final UserRepo userRepo;
	private final BorrowRecordMapper BRMapper;
	private final BorrowRecordRepo BRRepo;
	private final BookRepo bookRepo;
	
	@PreAuthorize("hasRole('USER')")
	@Override
	public Page<ResponseBorrowRecordDto> getUserBorrowRecordWithStatus(String email, UserRequestBorrowRecord filter , Pageable pageable) {
			
		User user=userRepo.findByEmail(email)
				           .orElseThrow(() -> new ContentNotFoundException("User not found"));
		
		Page<BorrowRecord> records=BRRepo.findByUser_IdAndStatus(user.getUser_id(), filter.getStatus(), pageable);
		
		if(records==null) {
			throw new ContentNotFoundException("No Records for the user");
		}
		
		return records.map(BRMapper::borrowToDto);
	}

	
	@PreAuthorize("hasRole('AUTHOR')")
	@Override
	public Page<AdminResponseBorrowRecordDto> getBorrowRecordsByBookId( AdminRequestBorrowRecordDto filter , Pageable pageable) {
		
	    bookRepo.findById(filter.getBookId())
				          .orElseThrow(() -> new ContentNotFoundException("Book Not Found"));
		
		Page<BorrowRecord> records=BRRepo.findByBook_Id(filter.getBookId(), pageable);
		
		if(records==null) {
			throw new ContentNotFoundException("No Records for the book");
		}
		
		return records.map(BRMapper::adminDto);

	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public Page<AdminResponseBorrowRecordDto> getBorrowRecordsByBookIdAndStatus( AdminRequestBorrowRecordDto filter , Pageable pageable) {
		
	    bookRepo.findById(filter.getBookId())
				          .orElseThrow(() -> new ContentNotFoundException("Book Not Found"));
		
		Page<BorrowRecord> records=BRRepo.findByBook_IdAndStatus(filter.getBookId(),filter.getStatus() ,pageable);
		
		if(records==null) {
			throw new ContentNotFoundException("No Records for the book");
		}
		
		return records.map(BRMapper::adminDto);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public Page<AdminResponseBorrowRecordDto> getBorrowRecordsByUserForAdmin(String email, AdminRequestBorrowRecordDto filter , Pageable pageable) {
		
		User user=userRepo.findByEmail(email)
				          .orElseThrow(() -> new ContentNotFoundException("User Not Found"));
		
	    bookRepo.findById(filter.getBookId())
				          .orElseThrow(() -> new ContentNotFoundException("Book Not Found"));
		
		Page<BorrowRecord> records=BRRepo.findByBook_IdAndUser(user.getUser_id(),filter.getBookId(), pageable);
		
		if(records==null) {
			throw new ContentNotFoundException("No Records for the user");
		}
		
		return records.map(BRMapper::adminDto);

	}
	
	@PreAuthorize("hasRole('USER')")
	@Override
	public Page<ResponseBorrowRecordDto> getUserBorrowRecord(String email , Pageable pageable) {
		User user=userRepo.findByEmail(email)
		           .orElseThrow(() -> new ContentNotFoundException("User not found"));

		Page<BorrowRecord> records=BRRepo.findByUser_Id(user.getUser_id(), pageable);
		
		if(records==null) {
			throw new ContentNotFoundException("No Records for the user");
		}
		
		return records.map(BRMapper::borrowToDto);
	}


	@Override
	public Page<AdminResponseBorrowRecordDto> getBorrowRecordsByStatusForAdmin(
			AdminRequestBorrowRecordDto filter,
			Pageable pageable) {
		
		if(filter.getStatus()==null) {
			throw new IllegalArgumentException("Status required foe thie query");
		}
		Page<BorrowRecord> records=BRRepo.findByStatus(filter.getStatus(), pageable);

		return records.map(BRMapper::adminDto);
	}

}
