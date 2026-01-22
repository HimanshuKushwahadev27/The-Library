package com.emi.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
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
	
	private String penName;
	
	private String lastName;
	
	private String bio;
	
	private String profileURL;
	
	private LocalDateTime 	joinedAt;
	
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<BookContent> authoredContents = new HashSet<>();
}

