package com.emi.service;

import java.time.LocalDateTime;

public interface AuditLogSerivce {

	void log(
			Long userId,
			String userRole,
			String action,
			String entity,
			Long entityId,
			String description,
			LocalDateTime createdAt)
	;
}
