package com.emi.entity;

import java.time.LocalDateTime;

import com.emi.enums.PaidAccess;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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

public class Membership {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	
	@Enumerated(EnumType.STRING)
	private PaidAccess accessType;
	
	
	private LocalDateTime startDate;
	
	
	private LocalDateTime endTime;
	
	
	private boolean autoRenew;
	
	//optional ensures that every user must always have membership
	@OneToOne(cascade=CascadeType.MERGE , fetch=FetchType.LAZY , optional = false)
	@JoinColumn(name = "user_id")
	private User user;
	
	
}
