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
import com.spring.veacy.apiresponse.SchemeApiResponse;
import com.spring.veacy.entity.Scheme;
import com.spring.veacy.entity.UserSchemeMapping;
import com.spring.veacy.request.SchemeRequest;

/**
 * The SchemeService interface provides methods for performing CRUD operations
 * and other actions on Scheme entities.
 */
public interface SchemeService {

	 /**
     * Retrieve all schemes from the data source.
     *
     * @return a list of Scheme objects.
     */
	ResponseEntity<SchemeApiResponse> getAll();
	
	 /**
     * Retrieve a scheme by its unique identifier.
     *
     * @param scheme_name the unique identifier of the scheme.
     * @return an Optional object containing the Scheme if found, or empty if not found.
     */
	ResponseEntity<SchemeApiResponse> getBySchemeName(String schemeName);
	
	/**
     * Save a new scheme or update an existing one in the data source.
     *
     * @param scheme the scheme object to be saved or updated.
     * @return the saved or updated scheme object.
     */
	ResponseEntity<ApiResponseMessage> save(SchemeRequest scheme);
//	String update(SchemeModel scheme, String name);
	
	/**
     * Update a scheme's properties given its unique identifier and a map of the updates.
     *
     * @param name the unique identifier of the scheme.
     * @param updates a map containing the properties to be updated and their new values.
     * @return the updated scheme object.
     */
	ResponseEntity<ApiResponseMessage> updated(Map<String, Object> updates, String name);
	
//	String deleteAll();
	
	 /**
     * Delete a scheme from the data source by its unique identifier.
     *
     * @param id the unique identifier of the scheme.
     */
	ResponseEntity<ApiResponseMessage> deleteById(Long id);
	
	 /**
     * Delete a scheme from the data source by its unique identifier.
     *
     * @param scheme_name the unique identifier of the scheme.
     */
	ResponseEntity<ApiResponseMessage> deleteBySchemeName(String schemeName);
	
}
