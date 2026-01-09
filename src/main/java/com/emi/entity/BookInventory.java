package com.emi.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

// for physical books

@Data
@NoArgsConstructor
@XmlRootElement
@Entity
public class BookInventory {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int BI_Id;
	
	
	//one book occupies only one row in the book inventory record 
	
	@OneToOne(cascade = CascadeType.MERGE , fetch=FetchType.LAZY)
	@JoinColumn(name="book_Id" , unique=true , nullable=false)
	private Book book;
	
	
	private int totalCopies;
	
	private int availableCopies;
	
}
