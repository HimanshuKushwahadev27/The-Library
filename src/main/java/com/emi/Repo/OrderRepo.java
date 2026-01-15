package com.emi.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
