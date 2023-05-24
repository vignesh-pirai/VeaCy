package com.spring.veacy.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSchemeMappingModel {

	private Long userId;
	private Long schemeId;
	private Double commitmentAmount;
	private Long auditingId;
}
