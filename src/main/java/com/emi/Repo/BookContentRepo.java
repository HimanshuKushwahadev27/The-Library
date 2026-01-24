package com.emi.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.emi.entity.BookContent;

public interface BookContentRepo extends JpaRepository<BookContent, Long> {

	@Query("""
			SELECT b FROM BookContent b 
			WHERE b.chapterNumber= :chapterNumber
			""")
	Optional<BookContent> findByChapterNumber(@Param("chapterNumber")Integer chapterNumber);

	@Query("""
			SELECT b FROM BookContent b
			WHERE b.author.authorId = :authorId
			""")
	List<BookContent> findAllByAuthorId(@Param("authorId") Long authorId);

	@Query("""
			SELECT b FROM BookContent b
			WHERE b.book.book_id = :book_id
			""")
	List<BookContent> findAllByBookId(@Param("book_id")Long book_id);

}
