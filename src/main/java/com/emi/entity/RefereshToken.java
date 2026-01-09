package com.emi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement
@Entity
public class RefereshToken {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long token_Id;
	
	//one user can have many token
	
	@ManyToOne
	@JoinColumn(name="user_Id")
	private User user;
	
	@Column(nullable=false , unique=true)
	private String token;
	
	private LocalDateTime expiryDate;
	
}
