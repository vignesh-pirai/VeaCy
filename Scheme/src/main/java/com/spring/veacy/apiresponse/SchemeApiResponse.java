/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.spring.veacy.apiresponse;

import java.io.Serializable;
import java.util.List;

import com.spring.veacy.request.SchemeRequest;
import com.spring.veacy.response.SchemeResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SchemeApiResponse extends ApiResponseMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	List<SchemeResponse> schemeModelList;
}
