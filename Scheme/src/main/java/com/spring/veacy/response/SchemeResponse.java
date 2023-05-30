package com.spring.veacy.response;

import com.spring.veacy.entity.AuditingLogger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchemeResponse {

	
	private String schemeName;
	private String schemeDescription;
	
	private Boolean isActive = true;
}
