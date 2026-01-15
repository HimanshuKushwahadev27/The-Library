package com.emi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class AuditLog {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	private Long userId;
	
	@Column(nullable=false)
	private String userRole;
	
	//what type of action performed create book , buy book ,del book etc
	@Column(nullable=false)
	private String action;
	
	//which entity performs
	@Column(nullable=false)
	private String entity;
	
	//id of the affected entity
	@Column(nullable=false)
	private Long entityId;
	
	//changes made 
	private String description ;
	
    private LocalDateTime timestamp;

}
