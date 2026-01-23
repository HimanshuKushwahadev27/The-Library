package com.emi.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// for physical books

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
public class BookInventory {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long bookInventory_Id;
	
	
	//one book occupies only one row in the book inventory record 
	
	@OneToOne(cascade = CascadeType.MERGE , fetch=FetchType.LAZY)
	@JoinColumn(name="book_id" , unique=true , nullable=false)
	private Book book;
	
	
	private int totalCopies;
	
	private int availableCopies;
	
}
