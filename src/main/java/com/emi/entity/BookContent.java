package com.emi.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(
	uniqueConstraints=@UniqueConstraint(
			columnNames= {"book_id","chapterNumber"}
		)
	)
public class BookContent {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long bookContent_Id;
	
	@ManyToOne( fetch=FetchType.LAZY)
	@JoinColumn(name = "book_id")
	private Book book;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Author author;
	
	private BigDecimal price;
	
	@Lob
	private String content;
	
	private Integer chapterNumber;
	
	private String title;
	
	private String cloudUrl;
	
	private boolean draft;
	
	private LocalDateTime createdAt;
}
