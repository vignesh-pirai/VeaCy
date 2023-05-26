package com.spring.veacy.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.spring.veacy.entity.Scheme;
import com.spring.veacy.entity.UserSchemeMapping;
import com.spring.veacy.model.UserSchemeMappingModel;
import com.spring.veacy.response.ApiResponseMessage;

public interface UserSchemeMappingService {

	List<UserSchemeMapping> getAll();
	
	Optional<UserSchemeMapping> getById(Long id);
	
	List<UserSchemeMapping> getBySchemeId(Long id);

	List<UserSchemeMapping> getByUserId(Long id);
	
	ResponseEntity<ApiResponseMessage> save(UserSchemeMappingModel userSchemeMappingModel);
//	String update(UserSchemeMappingModel userSchemeMappingModel, Long id);
	
//	String updated(Map<String, Object> updates, Long uid, Long sid);
	
	ResponseEntity<ApiResponseMessage> updated(Map<String, Object> updates, Long id);
	
	ResponseEntity<ApiResponseMessage> deleteById(Long id);
}
