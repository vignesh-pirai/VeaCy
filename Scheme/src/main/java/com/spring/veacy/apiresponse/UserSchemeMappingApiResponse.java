package com.spring.veacy.apiresponse;

import java.io.Serializable;
import java.util.List;

import com.spring.veacy.response.UserSchemeMappingResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSchemeMappingApiResponse extends ApiResponseMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	List<UserSchemeMappingResponse> userSchemeMappingModelList;
}
