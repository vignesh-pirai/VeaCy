package com.spring.veacy.response;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.spring.veacy.entity.AuditingLogger;
import com.spring.veacy.entity.Scheme;
import com.spring.veacy.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSchemeMappingResponse {

	
	private String userName;
	
	private String schemeName;
	
	private Double commitmentAmount;
}
