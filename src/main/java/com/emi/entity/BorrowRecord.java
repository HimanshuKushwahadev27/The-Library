package com.emi.entity;

import java.time.LocalDateTime;

import com.emi.enums.BookStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@XmlRootElement
@NoArgsConstructor
@Entity
public class BorrowRecord {

	@Id
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Book book;
	
	private LocalDateTime borrowDate;
	
	private LocalDateTime dueDate;
	
	private LocalDateTime returnDate;
	
	@Enumerated(EnumType.STRING)
	private BookStatus status;
}
