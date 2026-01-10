package com.emi.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class BookContent {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long bookContent_Id;
	
	@ManyToOne(cascade=CascadeType.MERGE , fetch=FetchType.LAZY)
	private Book book;
	
	
	private BigDecimal price;
	
	@Lob
	private String content;
	
	private Integer chapterNumber;
	
	private String title;
	
	private String cloudUrl;
	
	private boolean draft;
	
	private LocalDateTime createdAt;
}
