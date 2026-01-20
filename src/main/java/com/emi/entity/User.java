package com.emi.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.emi.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long user_id;
	
	private String firstName;
	
	private String lastName;
	
	@Column(unique=true)
	private String email;
	
	//not exposed in API response
	@JsonIgnore
	private String password;
	
	//ensures data is stored as readable string in DB
	@Enumerated(EnumType.STRING)
	private Role role;
	
	//allow for authentication or whether the user is allowed to use the app
	private boolean isEnabled;
	
	private LocalDateTime createdAt;
	
	private boolean isAccountNonLocked;
	
	private boolean deletedUser;
	
	private LocalDateTime passwordExpireDate;
	
	private LocalDateTime deletedDate;

	
	private LocalDateTime updatedAt;
	
	@OneToOne(mappedBy="user" , fetch=FetchType.LAZY)
	private Membership membership;
	
	@OneToMany(mappedBy="user" , fetch=FetchType.LAZY , cascade=CascadeType.ALL)
	private List<Token> token;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<UserBookContent> purchasedContents;
	
}
