package com.emi.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.entity.Book;

public interface BookRepo extends JpaRepository<Book, Long> {

}
