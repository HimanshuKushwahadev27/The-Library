package com.emi.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.entity.AuditLog;

public interface AuditLogRepo extends JpaRepository<AuditLog, Long> {

}
