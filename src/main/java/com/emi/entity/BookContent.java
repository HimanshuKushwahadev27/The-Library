package com.emi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@XmlRootElement
public class BookContent {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long bookContent_Id;
	
	@ManyToOne(cascade=CascadeType.MERGE , fetch=FetchType.LAZY)
	private Book book;
	
	private int chapterNumber;
	
	private String title;
	
	private String cloudUrl;
	
	private boolean draft;
	
	private LocalDateTime createdAt;
}
