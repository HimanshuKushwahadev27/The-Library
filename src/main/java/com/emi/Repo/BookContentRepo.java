package com.emi.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.entity.BookContent;

public interface BookContentRepo extends JpaRepository<BookContent, Long> {

}
