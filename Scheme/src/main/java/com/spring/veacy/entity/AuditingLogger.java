/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.spring.veacy.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

/**
* The AuditingLogger class is a Entity class that replicates the database table
* related to auditing management in the application.
*/
@Entity
@Getter
@Setter
@Table(name = "audit_table")
@SQLDelete(sql = "UPDATE audit_table SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class AuditingLogger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long createdBy;
	private String createdAt;
	private Long modifiedBy;
	private String modifiedAt;
	private Boolean isDeleted = false;
}
