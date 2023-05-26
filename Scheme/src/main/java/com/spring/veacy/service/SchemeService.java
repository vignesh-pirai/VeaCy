package com.spring.veacy.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.spring.veacy.entity.Scheme;
import com.spring.veacy.entity.UserSchemeMapping;
import com.spring.veacy.model.SchemeModel;
import com.spring.veacy.response.ApiResponseMessage;

public interface SchemeService {

	List<Scheme> getAll();
	ResponseEntity<ApiResponseMessage> save(SchemeModel scheme);
//	String update(SchemeModel scheme, String name);
	ResponseEntity<ApiResponseMessage> updated(Map<String, Object> updates, String name);
	ResponseEntity<Scheme> getBySchemeName(String schemeName);
//	String deleteAll();
	ResponseEntity<ApiResponseMessage> deleteById(Long id);
	ResponseEntity<ApiResponseMessage> deleteBySchemeName(String schemeName);
	
}
