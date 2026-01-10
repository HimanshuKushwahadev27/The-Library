package com.emi.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.emi.enums.OrderStatus;
import com.emi.enums.OrderType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long orderid;
	
	@Enumerated(EnumType.STRING)
	private OrderType ordertype;
	
	//many order for a user
	@ManyToOne
	@JoinColumn(name="user_id"  , nullable=false)
	private User user;
	
    //essentially manytomany relation between user and books as we have additional info so we use order entity

	
	//one order contain multiple book content
	//one book content can be ordered by multiple users
	@OneToMany(mappedBy="order",fetch=FetchType.LAZY , cascade=CascadeType.ALL)
	private Set<UserBookContent> purchasedContent = new HashSet<>();
	
	
	private BigDecimal totalAmt;
	
	@Enumerated(EnumType.STRING)	
	private OrderStatus status;
	
	//one order contain multiple book content
	//one book content can be ordered by multiple users

	
	private LocalDateTime createdAt;
}
