package com.spring.veacy.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.spring.veacy.entity.Scheme;
import com.spring.veacy.entity.UserSchemeMapping;
import com.spring.veacy.model.UserSchemeMappingModel;

public interface UserSchemeMappingService {

	String save(UserSchemeMappingModel userSchemeMappingModel);
	String update(UserSchemeMappingModel userSchemeMappingModel, Long id);
	List<UserSchemeMapping> getAll();
	Optional<UserSchemeMapping> getById(Long id);
//	UserSchemeMapping getBySchemeName(Scheme scheme);
	String deleteById(Long id);
//	String deleteAll();
	String updated(Map<String, Object> updates, Long id);
}
