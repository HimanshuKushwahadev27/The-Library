package com.emi.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@XmlRootElement
public class BookGenre {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(unique=true)
	private String name;
	
	
	//@JsonIgnore prevents cycling during  conversion into JSON by spring(jackson)
	//owner of this relationship is book
	@ManyToMany(mappedBy="genres")
	@JsonIgnore
	private Set<Book> books;
	
}
