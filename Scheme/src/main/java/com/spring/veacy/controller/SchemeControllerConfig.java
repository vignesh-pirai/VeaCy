package com.spring.veacy.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.veacy.entity.Scheme;
import com.spring.veacy.model.SchemeModel;
import com.spring.veacy.response.ApiResponseMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Scheme Controller", description = "This controller is for Scheme CRUD Operation")
public interface SchemeControllerConfig {

	@Operation(summary = "Show all Scheme Details", 
			   description = "Display all the Scheme Details")
    @ApiResponse(responseCode = "200", content = {
                @Content(schema = @Schema(), mediaType = "application/json") })	
	public List<Scheme> getAll();
	
	@Operation(summary = "Show particular Scheme Details", 
			   description = "Display Scheme Details by its Name")
	@ApiResponse(responseCode = "200", content = {
             @Content(schema = @Schema(), mediaType = "application/json") })
	public ResponseEntity<Scheme> getByName(@PathVariable String name);
	
	@Operation(summary = "Save Scheme Details", 
			   description = "Create and store the Scheme Details")
	@ApiResponse(responseCode = "200", content = {
             @Content(schema = @Schema(), mediaType = "application/json") })
	public ResponseEntity<ApiResponseMessage> save(@RequestBody SchemeModel scheme);
	
//	@Operation(summary = "Update Scheme Details", 
//			   description = "Update the Scheme Details by its Name")
//	@ApiResponse(responseCode = "200", content = {
//             @Content(schema = @Schema(), mediaType = "application/json") })
//	public ResponseEntity<ApiResponse> update(@RequestBody SchemeModel scheme,@PathVariable String name);
	
	@Operation(summary = "Update Scheme Details", 
			   description = "Update the specific Scheme Details by its Name")
	@ApiResponse(responseCode = "200", content = {
             @Content(schema = @Schema(), mediaType = "application/json") })
	public ResponseEntity<ApiResponseMessage> updated(@RequestBody Map<String, Object> updates,@PathVariable String name);
	
	@Operation(summary = "Delete Scheme Details", 
			   description = "Delete the Scheme Details by its Id")
	@ApiResponse(responseCode = "200", content = {
             @Content(schema = @Schema(), mediaType = "application/json") })
	public ResponseEntity<ApiResponseMessage> deleteById(@PathVariable Long id);
	
	@Operation(summary = "Delete the Scheme Details", 
			   description = "Delete the Scheme Details by its name")
	@ApiResponse(responseCode = "200", content = {
       @Content(schema = @Schema(), mediaType = "application/json") })
	public ResponseEntity<ApiResponseMessage> deleteByName(@PathVariable String name);
	
//	@Operation(summary = "Delete the Scheme Details", 
//			   description = "Delete all the Scheme Details")
//	@ApiResponse(responseCode = "200", content = {
//             @Content(schema = @Schema(), mediaType = "application/json") })
//	public String deleteAll();
	
}
