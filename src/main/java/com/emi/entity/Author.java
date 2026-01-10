package com.emi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long authorId;
	
	@OneToOne
	@JoinColumn(name="user_id" , nullable=false , unique=true)
	private User user;
	
	private String firstName;
	
	private String lastName;
	
	private String bio;
	
	private String profileURL;
	
	private LocalDateTime 	joinedAt;
}

