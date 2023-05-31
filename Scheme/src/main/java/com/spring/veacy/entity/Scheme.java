/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.spring.veacy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

/**
* The Scheme class is a Entity class that replicates the database table
* related to Scheme management in the application.
*/
@Entity
@Getter
@Setter
@Table(name = "scheme_table")
@SQLDelete(sql = "UPDATE scheme_table SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class Scheme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Column(unique = true)
	@NotNull()
	private String schemeName;
	@NotNull
	private String schemeDescription;
	
	@OneToOne
	@JoinColumn(name = "audit_id", referencedColumnName = "id")
	@NotNull
	private AuditingLogger auditingLogger;
	private Boolean isDeleted = false;
	
	private Boolean isActive = true;
}
