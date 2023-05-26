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

import com.spring.veacy.entity.UserSchemeMapping;
import com.spring.veacy.model.UserSchemeMappingModel;
import com.spring.veacy.response.ApiResponseMessage;
import com.spring.veacy.service.UserSchemeMappingService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/mapping")
@Slf4j
public class UserSchemeMappingController {

	@Autowired
	UserSchemeMappingService mappingService;

	@GetMapping("/{id}")
	public Optional<UserSchemeMapping> getById(@PathVariable Long id) {		
		log.info("Entered into Get By Id methodin in Controller");
		return mappingService.getById(id);
	}
	
	@GetMapping("/")
	public List<UserSchemeMapping> getAll() {
		log.info("Entered into the GetAll method in Controller");		
		return mappingService.getAll();
	}
	
	@GetMapping("/scheme/{id}")
	public List<UserSchemeMapping> getBySchemeId(@PathVariable Long id) {		
		log.info("Entered into Get By Id methodin in Controller");
		return mappingService.getBySchemeId(id);
	}
	
	@GetMapping("/user/{id}")
	public List<UserSchemeMapping> getByUserId(@PathVariable Long id) {		
		log.info("Entered into Get By Id methodin in Controller");
		return mappingService.getByUserId(id);
	}
	
	@PostMapping("/")
	public ResponseEntity<ApiResponseMessage> save(@RequestBody UserSchemeMappingModel userSchemeMappingModel) {
		log.info("Entered into Save method in Controller");
		return mappingService.save(userSchemeMappingModel);
//		return ResponseEntity.ok(mappingService.save(userSchemeMappingModel));
		
	}

//	@PutMapping("/{id}")
//	public String update(@RequestBody UserSchemeMappingModel userSchemeMappingModel,@PathVariable Long id) {
//		log.info("Entered into Update method in Controller");
//		return mappingService.update(userSchemeMappingModel, id);
//	}

	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponseMessage> updated(@RequestBody Map<String, Object> updates,@PathVariable Long id)
	{
		log.info("Entered into Update method in Controller");
		return mappingService.updated(updates, id);
	}

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