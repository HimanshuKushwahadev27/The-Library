package com.emi.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.emi.dto.requestDto.BookSearchRequestDto;
import com.emi.entity.Book;
import com.emi.enums.BookFormat;
import com.emi.enums.BookStatus;
import com.emi.enums.Role;

import jakarta.persistence.criteria.Predicate;

@Component
public class BookSpecification {

	//Specification  , return a “Rules for selecting books from the database”
	 	public static Specification<Book> search(
	 			BookSearchRequestDto req,
	 	        Role context,
	 	        Long authorId)
	 {
	 		
	 		//root - book table 
	 		//query ->query being build
	 		// cb -> criteria builder (helps in creating condition)
	 		//“Using the Book table, build conditions with CriteriaBuilder.”
	 		return (root, query, cb) -> {
	 			
	 			//conditions like 
	 			//status=AVAILABLE
	 			//collecting these conditions in list
	 			
	 			List<Predicate> predicates=new ArrayList<>();
	 			
	 			//only available books
	 			if(context==Role.USER) {
	 			predicates.add(cb.equal(root.get("bookStatus"), BookStatus.AVAILABLE));
	 			}
	 			
	 			if(context==Role.AUTHOR) {
	 				predicates.add(cb.equal(root.get("bookAuthor").get("authorId"), authorId)
	 				);
	 			}
	 			
	 			
	 			
	 			if(req.getQuery()!=null) {
	 				//i get "HELLO"
	 				String like= "%" + req.getQuery().toLowerCase() + "%";
	 			    //build "%hello%"	
	 			
	 				
	 				predicates.add(cb.or(
	                        cb.like(cb.lower(root.get("bookTitle")), like),
	                        cb.like(cb.lower(root.get("bookDescription")) , like)
	 						)
	 				);
	 			}
	 			
	 			if(req.getGenre()!=null) {
	 				predicates.add(cb.equal(root.get("bookGenre"), req.getGenre()));
	 			}
	 			
	            if (req.getBookLanguage() != null) {
	                predicates.add(cb.equal(root.get("bookLanguage"), req.getBookLanguage()));
	            }

	            if (req.getFormat() != null) {
	                predicates.add(cb.equal(root.get("bookFormat"), req.getFormat()));
	            }

	            if (req.getFormat() == BookFormat.DIGITAL) {

	                if (req.getMinPrice() != null) {
	                    predicates.add(cb.greaterThanOrEqualTo(
	                        root.get("bookPriceDigital"), req.getMinPrice()));
	                }

	                if (req.getMaxPrice() != null) {
	                    predicates.add(cb.lessThanOrEqualTo(
	                        root.get("bookPriceDigital"), req.getMaxPrice()));
	                }

	            } else if (req.getFormat() == BookFormat.PHYSICAL) {

	                if (req.getMinPrice() != null) {
	                    predicates.add(cb.greaterThanOrEqualTo(
	                        root.get("bookPricePhysical"), req.getMinPrice()));
	                }

	                if (req.getMaxPrice() != null) {
	                    predicates.add(cb.lessThanOrEqualTo(
	                        root.get("bookPricePhysical"), req.getMaxPrice()));
	                }
	            }

	            
	            return cb.and(predicates.toArray(new Predicate[0]));

	 		};	
	 		
	 	}
}
