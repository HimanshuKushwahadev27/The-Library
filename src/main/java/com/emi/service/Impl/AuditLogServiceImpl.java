package com.emi.service.Impl;

import java.time.LocalDateTime;

import com.emi.service.AuditLogSerivce;

public class AuditLogServiceImpl implements AuditLogSerivce {

	@Override
	public void log(
			Long userId, 
			String userRole,
			String action,
			String entity,
			Long entityId,
			String description,
			LocalDateTime createdAt) {

	}

}
