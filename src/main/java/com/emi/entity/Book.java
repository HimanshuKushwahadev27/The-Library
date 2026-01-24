package com.emi.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.emi.enums.BookFormat;
import com.emi.enums.BookLanguage;
import com.emi.enums.BookStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(
	indexes= {
			 @Index(name="idx_book_status" , columnList="bookStatus"),
			 @Index(name = "idx_book_language", columnList = "bookLanguage"),
			 @Index(name = "idx_book_digital_price", columnList = "bookPriceDigital"),
			 @Index(name = "idx_book_physical_price", columnList = "bookPricePhysical")	}
		)
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long book_id;
	
	private String bookTitle;
	
	//description can be long 
	@Lob
	private String description;
	
	@Column(unique=true)
	private String isbn;
	
	@Enumerated(EnumType.STRING)
	private BookLanguage bookLanguage;
	
	//making a join table where the owner is book 
	//with join columns and inverse join columns
	@ManyToMany(cascade=CascadeType.MERGE , fetch=FetchType.LAZY)
	@JoinTable(
		name="bookgenre" ,
		joinColumns=@JoinColumn(name ="book_id"),
		inverseJoinColumns=@JoinColumn(name="genre_id"),
		indexes= {
				@Index(name="idx_book_book_id" , columnList="book_id"),
				@Index(name="idx_book_genre_id" , columnList="genre_id")
		}
	)
	@Builder.Default
	private Set<BookGenre> genres = new HashSet<>();
	
	private BigDecimal bookPriceDigital;
	
	private BigDecimal bookPricePhysical;
	
	@Enumerated(EnumType.STRING)
	private Set<BookFormat> bookformat;
	
	@Enumerated(EnumType.STRING)
	private BookStatus bookStatus;
		
	@ManyToOne(cascade=CascadeType.MERGE , fetch=FetchType.LAZY)
	@JoinColumn(name = "author_id" , unique =true , nullable =false)
	private Author bookAuthor;
	
	//from cloud
	private String coverImageUrl;
	
	private Integer chapterNumber;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime deletedAt;
	
}
