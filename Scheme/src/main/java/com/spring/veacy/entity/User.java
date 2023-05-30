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

/**
* The User class is a Entity class that replicates the database table
* related to User management in the application.
*/
@Entity
@Getter
@Setter
@Table(name = "user_table")
@SQLDelete(sql = "UPDATE user_table SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class User {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String name;
	    
	    @Column(unique = true)
	    private String email;
	    
	    @Column(name = "mobile_no",unique = true)
	    private String mobileNo;
	    
	    @Column(name = "user_name",unique = true)
	    private String userName;
	    private Long roleId;
	    
	    @OneToOne
	    @JoinColumn(name = "auditing_id",referencedColumnName = "id")
	    private AuditingLogger auditingId;
	    
	    @Column(name = "is_deleted")
	    private Boolean isDeleted = false;
	    
	    @Column(name = "is_active")
	    private Boolean isActive = true;
}
