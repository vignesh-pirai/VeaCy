/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.spring.veacy.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.veacy.apiresponse.ApiResponseMessage;
import com.spring.veacy.apiresponse.UserSchemeMappingApiResponse;
import com.spring.veacy.entity.UserSchemeMapping;
import com.spring.veacy.request.UserSchemeMappingRequest;
import com.spring.veacy.service.UserSchemeMappingService;

import lombok.extern.slf4j.Slf4j;

/**
 * The UserSchemeMappingController class is a REST controller that handles HTTP requests
 * related to user management in the application.
 */

@RestController
@RequestMapping("/mapping")
@Slf4j
public class UserSchemeMappingController {

	@Autowired
	UserSchemeMappingService mappingService;

	 /**
     * Get a UserSchemeMapping by ID.
     *
     * @param id the unique identifier of the User_Scheme Mapping.
     * @return a ResponseEntity containing the User_Scheme Mapping object if found, or not found status if not found.
     */
	@GetMapping("/{id}")
	public ResponseEntity<UserSchemeMappingApiResponse> getById(@PathVariable Long id) {		
		log.info("Entered into Get By Id methodin in Controller");
		return mappingService.getById(id);
	}
	
	/**
     * Get all UserSchemeMapping.
     *
     * @return a ResponseEntity containing a list of all User_Scheme Mapping objects.
     */
	@GetMapping("/")
	public ResponseEntity<UserSchemeMappingApiResponse> getAll() {
		log.info("Entered into the GetAll method in Controller");		
		return mappingService.getAll();
	}
	
	/**
     * Get a UserSchemeMapping by Scheme ID.
     *
     * @return a ResponseEntity containing the User_Scheme Mapping object if found, or not found status if not found.
     */
	@GetMapping("/scheme/{id}")
	public ResponseEntity<UserSchemeMappingApiResponse> getBySchemeId(@PathVariable Long id) {		
		log.info("Entered into Get By Id methodin in Controller");
		return mappingService.getBySchemeId(id);
	}
	
	/**
     * Get a UserSchemeMapping by User ID.
     *
     * @return a ResponseEntity containing the User_Scheme Mapping object if found, or not found status if not found.
     */
	@GetMapping("/user/{id}")
	public ResponseEntity<UserSchemeMappingApiResponse> getByUserId(@PathVariable Long id) {		
		log.info("Entered into Get By Id methodin in Controller");
		return mappingService.getByUserId(id);
	}
	
	/**
     * Create a new UserSchemeMapping.
     *
     * @param User_Scheme Mapping object to be created.
     * @return a ResponseEntity containing the created User_Scheme Mapping object.
     */
	@PostMapping("/")
	public ResponseEntity<ApiResponseMessage> save(@RequestBody UserSchemeMappingRequest userSchemeMappingModel) {
		log.info("Entered into Save method in Controller");
		return mappingService.save(userSchemeMappingModel);
//		return ResponseEntity.ok(mappingService.save(userSchemeMappingModel));
		
	}

//	@PutMapping("/{id}")
//	public String update(@RequestBody UserSchemeMappingModel userSchemeMappingModel,@PathVariable Long id) {
//		log.info("Entered into Update method in Controller");
//		return mappingService.update(userSchemeMappingModel, id);
//	}

	/**
     * Update a user_scheme mapping's properties given its unique identifier.
     *
     * @param id the unique identifier of the user_scheme mapping.
     * @param updates a map containing the properties to be updated and their new values.
     * @return the updated User_Scheme Mapping object.
     */
	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponseMessage> updated(@RequestBody Map<String, Object> updates,@PathVariable Long id)
	{
		log.info("Entered into Update method in Controller");
		return mappingService.updated(updates, id);
	}

	/**
     * Delete a user_scheme mapping by its unique identifier.
     *
     * @param id the unique identifier of the user_scheme mapping.
     * @return a ResponseEntity with no content status.
     */
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseMessage> deleteById(@PathVariable Long id) {
		log.info("Entered into Delete method in Controller");
		return mappingService.deleteById(id);
	}

//	@DeleteMapping("/")
//	public String deleteAll() {
//		log.info("All the User-Scheme Mapping Details are updated");
//		return mappingService.deleteAll();
//	}
	
}
