package com.leonardo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners; // Required for auditing
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; // CRITICAL: Use jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass;
import java.time.ZonedDateTime;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class Common {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, updatable = false)
	@CreatedDate
	private ZonedDateTime createdDate;

	@Column
	@LastModifiedDate
	private ZonedDateTime updatedDate;

	@Column(nullable = false, updatable = false)
	@CreatedBy
	private String createdBy;

	@Column
	@LastModifiedBy
	private String updatedBy;
}