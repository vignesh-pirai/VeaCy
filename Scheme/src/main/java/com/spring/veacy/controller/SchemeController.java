/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.spring.veacy.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.spring.veacy.apiresponse.SchemeApiResponse;
import com.spring.veacy.entity.Scheme;
import com.spring.veacy.request.SchemeRequest;
import com.spring.veacy.service.SchemeService;

import lombok.extern.slf4j.Slf4j;

/**
 * The SchemeController class is a REST controller that handles HTTP requests
 * related to scheme management in the application.
 */

@RestController
@RequestMapping("/scheme")
@Slf4j

public class SchemeController implements SchemeControllerConfig{

	@Autowired
	SchemeService service;
	
	/**
     * Get all schemes.
     *
     * @return a ResponseEntity containing a list of all Scheme objects.
     */
	@GetMapping("/")
	public ResponseEntity<SchemeApiResponse> getAll(){
		log.info("Entered into the GetAll method in Controller");
		return service.getAll();
	}
	
	 /**
     * Get a scheme by Scheme Name.
     *
     * @param name the unique identifier of the scheme.
     * @return a ResponseEntity containing the Scheme object if found, or not found status if not found.
     */
	@GetMapping("/{name}")
	public ResponseEntity<SchemeApiResponse> getByName(@PathVariable String name){
		log.info("Entered into Get By Name method in Controller");
		return service.getBySchemeName(name);
	}
	
	/**
     * Create a new Scheme.
     *
     * @param Scheme object to be created.
     * @return a ResponseEntity containing the created Scheme object.
     */
	@PostMapping("/")
	public ResponseEntity<ApiResponseMessage> save( @RequestBody SchemeRequest scheme) {
		log.info("Entered into Save method in Controller");
		return service.save(scheme);
	}
	
//	@PutMapping("/{name}")
//	public String update(@RequestBody SchemeModel scheme,@PathVariable String name) {
//		log.info("Entered into Update method in Controller");
//		return service.update(scheme, name);
//	}
	
	/**
     * Update a scheme's properties given its unique identifier.
     *
     * @param id the unique identifier of the scheme.
     * @param updates a map containing the properties to be updated and their new values.
     * @return the updated Scheme object.
     */
	@PatchMapping("/{name}")
	public ResponseEntity<ApiResponseMessage> updated(@RequestBody Map<String, Object> updates,@PathVariable String name){
		log.info("Entered into Update method in Controller");
		return service.updated(updates, name);
	}
	
//	@DeleteMapping("/")
//	public String deleteAll()
//	{
//		log.info("All the Scheme Details are deleted");
//		return service.deleteAll();
//	}
	
	/**
     * Delete a scheme by its unique identifier.
     *
     * @param id the unique identifier of the scheme.
     * @return a ResponseEntity with no content status.
     */
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseMessage> deleteById(@PathVariable Long id) {
		log.info("Entered into Delete method in Controller");
		return service.deleteById(id);
	}
	
	/**
     * Delete a scheme by its unique scheme name.
     *
     * @param scheme_name the unique identifier of the scheme.
     * @return a ResponseEntity with no content status.
     */
	@DeleteMapping("/name/{name}")
	
	public ResponseEntity<ApiResponseMessage> deleteByName(@PathVariable String name)
	{
		log.info("Entered into Delete method in Controller");
		return service.deleteBySchemeName(name);
	}
	
}
