package com.spring.veacy.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.veacy.entity.Scheme;
import com.spring.veacy.model.SchemeModel;
import com.spring.veacy.service.SchemeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/scheme")
@Slf4j
public class SchemeController implements SchemeControllerConfig{

	@Autowired
	SchemeService service;
	
	@GetMapping("/")
	public List<Scheme> getAll(){
		log.info("Entered into the GetAll method in Controller");
		return service.getAll();
	}
	
	@GetMapping("/{id}")
	public String getById(@PathVariable Long id){
		log.info("Entered into Get By Id method in Controller");
		return service.getById(id);
	}
	
	@GetMapping("/name/{name}")
	public String getByName(@PathVariable String name){
		log.info("Entered into Get By Name methodin in Controller");
		return service.findBySchemeName(name);
	}
	
	@PostMapping("/")
	public String save(@RequestBody SchemeModel scheme) {
		log.info("Entered into Save method in Controller");
		return service.save(scheme);
	}
	
	@PutMapping("/{name}")
	public String update(@RequestBody SchemeModel scheme,@PathVariable String name) {
		log.info("Entered into Update method in Controller");
		return service.update(scheme, name);
	}
	
	@PatchMapping("/{name}")
	public String updated(@RequestBody Map<String, Object> updates,@PathVariable String name){
		log.info("Entered into Update method in Controller");
		return service.updated(updates, name);
	}
	
//	@DeleteMapping("/")
//	public String deleteAll()
//	{
//		log.info("All the Scheme Details are deleted");
//		return service.deleteAll();
//	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable Long id) {
		log.info("Entered into Delete method in Controller");
		return service.deleteById(id);
	}
	
	@DeleteMapping("/name/{name}")
	
	public String deleteByName(@PathVariable String name)
	{
		log.info("Entered into Delete method in Controller");
		return service.deleteBySchemeName(name);
	}
	
}
