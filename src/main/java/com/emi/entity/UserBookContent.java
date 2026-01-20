package com.emi.entity;

import java.math.BigDecimal;


import java.time.LocalDateTime;

import com.emi.enums.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

//joined entity telling which user own which chapters

@Entity
@Data
@NoArgsConstructor
public class UserBookContent {

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
    
		    
     //one bookcontent can have many userbookcontent
     @ManyToOne(fetch=FetchType.LAZY)
     @JoinColumn(name="content_id")
     private BookContent content;
     
     @ManyToOne(fetch=FetchType.LAZY)
     @JoinColumn(name="order_id")
     private Order order;
     
     
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "user_id", nullable = false)
     private User user;

     //so essentially manytomany mapping between user and bookcontent as the relationship contains extra information
     private BigDecimal itemPrice;
     
 	@Enumerated(EnumType.STRING)	
 	private OrderStatus status;
    
    private LocalDateTime purchasedDate;
    
    
}
