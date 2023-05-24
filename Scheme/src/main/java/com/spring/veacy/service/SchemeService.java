package com.spring.veacy.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.spring.veacy.entity.Scheme;
import com.spring.veacy.model.SchemeModel;

public interface SchemeService {

	List<Scheme> getAll();
	String getById(Long id);
	String save(SchemeModel scheme);
	String update(SchemeModel scheme, String name);
	String updated(Map<String, Object> updates, String name);
	String findBySchemeName(String schemeName);
//	String deleteAll();
	String deleteById(Long id);
	String deleteBySchemeName(String schemeName);
	
}
