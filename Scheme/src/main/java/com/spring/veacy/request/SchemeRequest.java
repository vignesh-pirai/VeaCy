/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.spring.veacy.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchemeRequest {

	private String schemeName;
	private String schemeDescription;
	private Long auditingId;
	private Boolean isActive = true;
}
