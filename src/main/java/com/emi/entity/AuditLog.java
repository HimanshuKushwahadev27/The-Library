package com.emi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@XmlRootElement
@Entity
@NoArgsConstructor
public class AuditLog {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	private Long userId;
	
	//what type of action performed create book , buy book ,del book etc
	@Column(nullable=false)
	private String action;
	
	//which entity performs
	@Column(nullable=false)
	private String entity;
	
    private LocalDateTime timestamp;

}
