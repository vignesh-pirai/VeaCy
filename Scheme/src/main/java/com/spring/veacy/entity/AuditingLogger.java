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
