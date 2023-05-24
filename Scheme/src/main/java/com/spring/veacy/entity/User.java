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
