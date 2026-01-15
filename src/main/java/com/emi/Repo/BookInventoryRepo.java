package com.emi.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.entity.BookInventory;

public interface BookInventoryRepo extends JpaRepository<BookInventory, Long> {

}
