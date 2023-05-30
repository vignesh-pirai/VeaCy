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

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_scheme_mapping_table")
@SQLDelete(sql = "UPDATE user_scheme_mapping_table SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class UserSchemeMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "scheme_id", referencedColumnName = "id")
	private Scheme scheme;
	
	private Double commitmentAmount;
	
	@OneToOne
    @JoinColumn(name = "auditing_id",referencedColumnName = "id")
    private AuditingLogger auditingId;
    
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
    
}
