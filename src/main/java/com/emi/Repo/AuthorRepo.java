package com.emi.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.entity.Author;

public interface AuthorRepo extends JpaRepository<Author,Long> {

}
