/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.spring.veacy.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.spring.veacy.apiresponse.ApiResponseMessage;
import com.spring.veacy.apiresponse.UserSchemeMappingApiResponse;
import com.spring.veacy.entity.Scheme;
import com.spring.veacy.entity.UserSchemeMapping;
import com.spring.veacy.request.UserSchemeMappingRequest;

/**
 * The UserSchemeMappingService interface provides methods for performing CRUD operations
 * and other actions on User_Scheme Mapping entities.
 */
public interface UserSchemeMappingService {

	/**
     * Retrieve all user_scheme from the data source.
     *
     * @return a list of UserSchemeMapping objects.
     */
	ResponseEntity<UserSchemeMappingApiResponse> getAll();
	
	/**
     * Retrieve a user_scheme by its unique identifier.
     *
     * @param id the unique identifier of the user_scheme.
     * @return an Optional object containing the UserSchemeMapping if found, or empty if not found.
     */
	ResponseEntity<UserSchemeMappingApiResponse> getById(Long id);
	
	/**
     * Retrieve a user_scheme by its unique identifier.
     *
     * @param scheme_id the unique identifier of the scheme.
     * @return an Optional object containing the UserSchemeMapping if found, or empty if not found.
     */
	ResponseEntity<UserSchemeMappingApiResponse> getBySchemeId(Long id);

	/**
     * Retrieve a user_scheme by its unique identifier.
     *
     * @param user_id the unique identifier of the user.
     * @return an Optional object containing the UserSchemeMapping if found, or empty if not found.
     */
	ResponseEntity<UserSchemeMappingApiResponse> getByUserId(Long id);
	
	 /**
     * Save a new user_scheme or update an existing one in the data source.
     *
     * @param user_scheme the user_scheme object to be saved or updated.
     * @return the saved or updated user_scheme object.
     */
	ResponseEntity<ApiResponseMessage> save(UserSchemeMappingRequest userSchemeMappingModel);
//	String update(UserSchemeMappingModel userSchemeMappingModel, Long id);
	
//	String updated(Map<String, Object> updates, Long uid, Long sid);
	
	 /**
     * Update a user_scheme's properties given its unique identifier and a map of the updates.
     *
     * @param id the unique identifier of the user_scheme.
     * @param updates a map containing the properties to be updated and their new values.
     * @return the updated user_scheme object.
     */
	ResponseEntity<ApiResponseMessage> updated(Map<String, Object> updates, Long id);
	
	 /**
     * Delete a user_scheme from the data source by its unique identifier.
     *
     * @param id the unique identifier of the user_scheme.
     */
	ResponseEntity<ApiResponseMessage> deleteById(Long id);
}
