package com.emi.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

public class BookGenre {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(unique=true)
	private String name;
	
	
	//@JsonIgnore prevents cycling during  conversion into JSON by spring(jackson)
	//owner of this relationship is book
	@ManyToMany(mappedBy="genres" , cascade=CascadeType.MERGE , fetch=FetchType.LAZY)
	private Set<Book> books = new HashSet<>();
	
}
