package com.emi.Controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emi.dto.requestDto.AdminRequestBorrowRecordDto;
import com.emi.dto.requestDto.UserRequestBorrowRecord;
import com.emi.dto.responseDto.AdminResponseBorrowRecordDto;
import com.emi.dto.responseDto.ResponseBorrowRecordDto;
import com.emi.service.BorrowRecordService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
public class BorrowRecordController {

	private final BorrowRecordService borrowRecordService;
	
	@GetMapping("/borrow-records")
	@Operation(
			summary="Get Borrow Records for user" ,
		    description="users endpoint"
		)
    public Page<ResponseBorrowRecordDto> getBorrowRecords(
    		Principal principal,
    		@RequestParam(defaultValue="0") int page,
    		@RequestParam(defaultValue="10") int size,
    		@RequestParam(defaultValue="borrowedAt,desc")String[] sort,
    		@RequestBody @Valid UserRequestBorrowRecord status
    		){
		Pageable pageable=PageRequest
				.of(page,
					size,
					Sort.by(parseSort(sort)));
		
		return status==null 
				 ?borrowRecordService.getUserBorrowRecord(principal.getName(), pageable)
			     :borrowRecordService.getUserBorrowRecordWithStatus(principal.getName(), null, pageable)
			    ;
	}
	
	@GetMapping("/borrow-records")
	@Operation(
			summary="Get Borrow Records for user by admin" ,
		    description="Admin endpoint"
		)
    public Page<AdminResponseBorrowRecordDto> getBorrowRecordsByAdmin(
    		Principal principal,
    		@RequestParam(defaultValue="0") int page,
    		@RequestParam(defaultValue="10")int size,
    		@RequestParam(defaultValue="borrowedAt,desc") String [] sort,
    		@RequestBody @Valid AdminRequestBorrowRecordDto request
    		){
		
		Sort sortObj= (sort==null)
				?Sort.unsorted()
			    :Sort.by(parseSort(sort));
		
		Pageable pageable = PageRequest
				.of(page,
				    size,
				    sortObj);
		
		if(request.getBookId()!=null && request.getStatus()==null ) {
			return borrowRecordService.getBorrowRecordsByBookId( request, pageable);
		}
		
		if(request.getBookId()!=null && request.getStatus()!=null) {
			return borrowRecordService.getBorrowRecordsByBookIdAndStatus(request,pageable);
		}
		
		else{
			return borrowRecordService.getBorrowRecordsByUserForAdmin(principal.getName(), request, pageable);
		}
		
		
	}


	private List<Sort.Order> parseSort(String[] sort) {
		return Arrays.stream(sort)
				.map(s -> {
					String[] parts=s.split(",");
					String property=parts[0];
					Sort.Direction direction=
							parts.length > 1
							           ?Sort.Direction.fromString(parts[1])
							           :Sort.Direction.ASC;
							return new Sort.Order(direction , property);
				})
				.toList();
	}
	
}
