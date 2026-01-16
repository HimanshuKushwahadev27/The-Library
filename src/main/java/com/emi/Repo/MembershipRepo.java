package com.emi.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.entity.Membership;

public interface MembershipRepo extends JpaRepository<Membership, Long> {

}
