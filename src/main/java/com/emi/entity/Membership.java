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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Membership {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	
	@Enumerated(EnumType.STRING)
	private PaidAccess accessType;
	
	
	private LocalDateTime startDate;
	
	
	private LocalDateTime endTime;
	
	
	private boolean autoRenew;
	
	private boolean isActive;
	
	//optional ensures that every user must always have membership
	@OneToOne(cascade=CascadeType.PERSIST , fetch=FetchType.LAZY , optional = false)
	@JoinColumn(name = "user_id")

	private User user;
	
	
}
