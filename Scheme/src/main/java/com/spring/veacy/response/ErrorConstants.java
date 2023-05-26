package com.spring.veacy.response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
@PropertySource("classpath:message.properties")
public class ErrorConstants {

	@Value("${ERROR_CODE_200}")
	private String code200;
	
	@Value("${ERROR_CODE_500}")
	private String code500;
	
	@Value("${SUCCESS}")
	private String success;
	
	@Value("${INTERNAL_SERVER_ERROR}")
	private String internalServerError;
	
	@Value("${INVALID_REQUEST}")
	private String invlaidRequest;
	
	@Value("${SCHEME_EXISTS}")
	private String schemeExists;
	
	@Value("${USER_SCHEME_EXISTS}")
	private String userSchemeExists;
	
	@Value("${SCHEME_NOT_FOUND}")
	private String schemeNotFound;
	
	@Value("${SCHEME_NAME_NOT_FOUND}")
	private String schemeNameNotFound;
	
	@Value("${SCHEME_NOT_DELETED}")
	private String schemeNotDeleted;
	
//	@Value("${}")
}
