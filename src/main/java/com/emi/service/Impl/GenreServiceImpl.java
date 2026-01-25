package com.emi.service.Impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.emi.Repo.BookGenreRepo;
import com.emi.entity.Book;
import com.emi.entity.BookGenre;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class GenreServiceImpl {

	private final BookGenreRepo bookGenreRepo;
	
	public Set<BookGenre> assignGenre(Set<String> types, Book book) {

	    Set<BookGenre> genres = new HashSet<>();


	    
	    
	    for (String t : types) {
	        BookGenre genre = bookGenreRepo.findByName(t)
	                .orElseThrow(() ->
	                        new IllegalArgumentException("Genre not found: " + t)
	                );
	        genres.add(genre);

	    }

	    book.getGenres().clear();
	    book.getGenres().addAll(genres);
	    
	    
	    for (BookGenre g : genres) {
	        g.getBooks().add(book);
	    }
	    
	    return genres;
	}

}
