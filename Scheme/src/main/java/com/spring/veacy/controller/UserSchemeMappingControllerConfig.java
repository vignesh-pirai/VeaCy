/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.spring.veacy.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.veacy.apiresponse.UserSchemeMappingApiResponse;
import com.spring.veacy.entity.UserSchemeMapping;
import com.spring.veacy.request.UserSchemeMappingRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User Scheme Mapping Controller", description = "This controller is for CRUD operations for User and Scheme Mapping")
public interface UserSchemeMappingControllerConfig {

	
	@Operation(summary = "Show all User-Scheme Mapping Details", 
			   description = "Display all the User-Scheme Mapping Details")
	@ApiResponse(responseCode = "200", content = {
             @Content(schema = @Schema(), mediaType = "application/json") })
	public ResponseEntity<UserSchemeMappingApiResponse> getAll();
	
	@Operation(summary = "Show particular User-Scheme Mapping Details", 
			   description = "Display User-Scheme Mapping Details by its Id")
	@ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(), mediaType = "application/json") })
	public ResponseEntity<UserSchemeMappingApiResponse> getById(@PathVariable Long id);
	
	@Operation(summary = "Save User-Scheme Mapping Details", 
			   description = "Create and store the User-Scheme Mapping Details")
	@ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(), mediaType = "application/json") })
	public String save(@RequestBody UserSchemeMappingRequest userSchemeMappingModel);
	
//	@Operation(summary = "Update User-Scheme Mapping Details", 
//			   description = "Update the User-Scheme Mapping Details by its Name")
//	@ApiResponse(responseCode = "200", content = {
//          @Content(schema = @Schema(), mediaType = "application/json") })
//	public String update(@RequestBody UserSchemeMappingModel userSchemeMappingModel,@PathVariable Long id);
	
	@Operation(summary = "Update User-Scheme Mapping Details", 
			   description = "Update the specific User-Scheme Mapping Details by its Id")
	@ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(), mediaType = "application/json") })
	public String updated(@RequestBody Map<String, Object> updates,@PathVariable Long id);
	
//	@Operation(summary = "Delete User-Scheme Mapping Details", 
//			   description = "Delete the User-Scheme Mapping Details by its Id")
//	@ApiResponse(responseCode = "200", content = {
//          @Content(schema = @Schema(), mediaType = "application/json") })
//	public String deleteAll();
	
	@Operation(summary = "Delete the User-Scheme Mapping Details", 
			   description = "Delete all the User-Scheme Mapping Details")
	@ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(), mediaType = "application/json") })
	public String deleteById(@PathVariable Long id);
}
