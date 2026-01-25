package com.emi.Repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.emi.entity.UserBookContent;
import com.emi.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {

	@Query("""
			SELECT ubc FROM Order o JOIN o.purchasedContent ubc 
			WHERE ubc.user.user_id=:userId
			""")
	Set<UserBookContent> findAllPurchaseContentViaOrder(@Param("userId") Long userId);
	
	
	@Query("""
		   SELECT ubc FROM Order o JOIN o.purchasedContent ubc 
			WHERE ubc.user.user_id=:userId
			AND ubc.content.bookContent_Id=:contentId
		""")
	UserBookContent findByUserIdAndContentId(
		        @Param("userId") Long userId,
		        @Param("contentId") Long contentId
		);
	
	@Query("""
			SELECT o FROM Order o
			WHERE o.user.user_id = :userId
			""")
	List<Order> findAllOrderByUserId(@Param("userId") Long userId);
		
	
	@Query("""
			SELECT DISTINCT o FROM Order o
			JOIN o.purchasedContent ubc
			JOIN ubc.content bc
			WHERE bc.book.bookid = :bookId
			""")
	List<Order> findAllOrderByBookId(@Param("bookId") Long bookId);
}