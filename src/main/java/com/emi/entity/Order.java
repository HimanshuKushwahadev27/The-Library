package com.emi.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.emi.enums.OrderStatus;
import com.emi.enums.OrderType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@XmlRootElement
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long oreder_Id;
	
	@Enumerated(EnumType.STRING)
	private OrderType ordertype;
	
	//many order for a user
	@ManyToOne
	@JoinColumn(name="user_Id" , unique=true , nullable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="book_Id" , unique=true , nullable=false)
	private Book book;
	
	private BigDecimal pricePaid;
	
	@Enumerated(EnumType.STRING)	
	private OrderStatus status;
	
	
	
	private LocalDateTime createdAt;
}
