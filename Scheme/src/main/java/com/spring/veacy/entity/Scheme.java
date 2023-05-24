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
@Table(name = "scheme_table")
@SQLDelete(sql = "UPDATE scheme_table SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class Scheme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String schemeName;
	private String schemeDescription;
	
	@OneToOne
	@JoinColumn(name = "audit_id", referencedColumnName = "id")
	private AuditingLogger auditingLogger;
	private Boolean isDeleted = false;
}
