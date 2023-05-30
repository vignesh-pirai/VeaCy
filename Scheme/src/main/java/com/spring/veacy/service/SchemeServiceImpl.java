/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.spring.veacy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.veacy.apiresponse.ApiResponseMessage;
import com.spring.veacy.apiresponse.ErrorConstants;
import com.spring.veacy.apiresponse.SchemeApiResponse;
import com.spring.veacy.entity.AuditingLogger;
import com.spring.veacy.entity.Scheme;
import com.spring.veacy.repos.SchemeRepo;
import com.spring.veacy.request.SchemeRequest;
import com.spring.veacy.response.SchemeResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * The SchemeServiceImpl class provides an implementation of the SchemeService interface,
 * handling CRUD operations and other actions on Scheme entities.
 */
@Service
@Slf4j
public class SchemeServiceImpl implements SchemeService {

	@Autowired
	SchemeRepo repo;
	
	@Autowired
	ErrorConstants message;
	
	/**
     * {@inheritDoc}
     */
	@Override
	public ResponseEntity<SchemeApiResponse> getAll() {
		log.info("Entered into the GetAll method");
		log.debug("Fetching all the scheme details");
		SchemeApiResponse apiResponse = new SchemeApiResponse();
		try {
			List<Scheme> schemeList = repo.findAll();
			if(schemeList.isEmpty()) {
				log.warn("No Schemes are available");
				apiResponse.setMessage("The Scheme table is empty");
				apiResponse.setStatus(Boolean.FALSE);
				apiResponse.setStatusCode(message.getCode200());
				return new ResponseEntity<>(apiResponse,HttpStatus.NOT_ACCEPTABLE);
			}
			else {
				log.debug("The displayed details are: \n"+schemeList);
			}
			List<SchemeResponse> schemeModelList = new ArrayList<>();
			for(Scheme scheme:schemeList) {
				SchemeResponse response = new SchemeResponse();
				BeanUtils.copyProperties(scheme, response);
//				schemeModel.setId(scheme.getId());
//				schemeModel.setSchemeName(scheme.getSchemeName());
//				schemeModel.setSchemeDescription(scheme.getSchemeDescription());
//				schemeModel.setAuditingLogger(scheme.getAuditingLogger());
//				schemeModel.setIsActive(scheme.getIsActive());
//				schemeModel.setIsDeleted(scheme.getIsDeleted());
				schemeModelList.add(response);
			}
			apiResponse.setMessage(message.getSuccess());
			apiResponse.setStatus(Boolean.TRUE);
			apiResponse.setStatusCode(message.getCode200());
			apiResponse.setSchemeModelList(schemeModelList);
			return new ResponseEntity<>(apiResponse,HttpStatus.OK);
		}
		catch(Exception e)
		{
			apiResponse.setMessage(message.getInternalServerError());
			apiResponse.setStatus(Boolean.FALSE);
			apiResponse.setStatusCode(message.getCode500());
			return new ResponseEntity<>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public ResponseEntity<SchemeApiResponse> getBySchemeName(String schemeName) {
		log.info("Entered into the GetByName method");
		log.debug("Fetching scheme with name: {}",schemeName);
			SchemeApiResponse apiResponse = new SchemeApiResponse();
			try {
				Optional<Scheme> optional = repo.findBySchemeNameAndIsDeletedFalse(schemeName);
				Scheme scheme = optional.get();
				if(optional.isEmpty()) {
					log.warn("No Schemes are available");
					apiResponse.setMessage("The Scheme table is empty");
					apiResponse.setStatus(Boolean.FALSE);
					apiResponse.setStatusCode(message.getCode200());
					return new ResponseEntity<>(apiResponse,HttpStatus.NOT_ACCEPTABLE);
				}
				else {
					log.debug("The displayed details are: \n"+scheme);
				}
				List<SchemeResponse> schemeModelList = new ArrayList<>();
				SchemeResponse response = new SchemeResponse();
				BeanUtils.copyProperties(scheme, response);
				schemeModelList.add(response);
				apiResponse.setMessage(message.getSuccess());
				apiResponse.setStatus(Boolean.TRUE);
				apiResponse.setStatusCode(message.getCode200());
				apiResponse.setSchemeModelList(schemeModelList);
				return new ResponseEntity<>(apiResponse,HttpStatus.OK);
			}
			catch(Exception e)
			{
				apiResponse.setMessage(message.getInternalServerError());
				apiResponse.setStatus(Boolean.FALSE);
				apiResponse.setStatusCode(message.getCode500());
				return new ResponseEntity<>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public ResponseEntity<ApiResponseMessage> save(SchemeRequest schemeRequest) {
		log.info("Entered into the Create method");
		ApiResponseMessage apiResponse = new ApiResponseMessage();
		try {
			log.debug("Storing scheme details");
			Scheme scheme = new Scheme();
			AuditingLogger loggerId = new AuditingLogger();
			if(repo.findBySchemeNameAndIsDeletedFalse(schemeRequest.getSchemeName()).isEmpty()) {
//			scheme.setSchemeName(schemeModel.getSchemeName());
//			scheme.setSchemeDescription(schemeModel.getSchemeDescription());
			BeanUtils.copyProperties(schemeRequest, scheme);
			loggerId.setId(schemeRequest.getAuditingId());
			scheme.setAuditingLogger(loggerId);
			repo.save(scheme);
			apiResponse.setMessage(message.getSuccess());
			apiResponse.setStatus(Boolean.TRUE);
			apiResponse.setStatusCode(message.getCode200());
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
			}
			else {
				apiResponse.setMessage(message.getSchemeExists());
				apiResponse.setStatus(Boolean.FALSE);
				apiResponse.setStatusCode(message.getCode500());
				return new ResponseEntity<>(apiResponse, HttpStatus.NOT_ACCEPTABLE);
			}
		}
		catch(Exception e) {
			log.error("The Scheme is not created");
			log.error("Error --> "+e.getMessage());
			apiResponse.setMessage(message.getInternalServerError());
			apiResponse.setStatus(Boolean.FALSE);
			apiResponse.setStatusCode(message.getCode500());
			return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public ResponseEntity<ApiResponseMessage> updated(Map<String, Object> updates, String name) {
		log.info("Entered into the Update method");
		ApiResponseMessage apiResponse = new ApiResponseMessage();
		try {
		Optional<Scheme> optional = repo.findBySchemeNameAndIsDeletedFalse(name);
		if(optional.isPresent())
		{
			Scheme scheme = optional.get();
			log.debug("Updating scheme details");
			updates.forEach(
					(field,value)->{
						switch(field) {
							case "schemeName": 
								if(repo.findBySchemeNameAndIsDeletedFalse((String) value).isEmpty()) {
									scheme.setSchemeName((String) value);
								}
								else {
									String str = "Scheme name already exists.";
									apiResponse.setMessage(str);
									throw new IllegalArgumentException(str);
								}
								break;
							case "schemeDescription":
								scheme.setSchemeDescription((String) value);
								break;
							case "isActive":
								scheme.setIsActive((Boolean) value);
								break;
							default:
								String str = "Invalid field: "+field;
								apiResponse.setMessage(str);
								throw new IllegalArgumentException(str);
						}
					}
			);
			repo.save(scheme);
			log.debug("Updated scheme details: {}",updates);
			apiResponse.setMessage(message.getSuccess());
			apiResponse.setStatus(Boolean.TRUE);
			apiResponse.setStatusCode(message.getCode200());
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		}
		else {
			log.warn("The Scheme is not updated");
			apiResponse.setMessage(message.getSchemeNotFound());
			apiResponse.setStatus(Boolean.FALSE);
			apiResponse.setStatusCode(message.getCode500());
			return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
		}
		}
		catch(IllegalArgumentException e)
		{
			apiResponse.setStatus(Boolean.FALSE);
			apiResponse.setStatusCode(message.getCode200());
			return new ResponseEntity<>(apiResponse, HttpStatus.NOT_ACCEPTABLE);
		}
		catch(Exception e)
		{
			apiResponse.setMessage(message.getInternalServerError());
			apiResponse.setStatus(Boolean.FALSE);
			apiResponse.setStatusCode(message.getCode500());
			return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public ResponseEntity<ApiResponseMessage> deleteById(Long id) {
		log.info("Entered into the Delete by Id method");
		ApiResponseMessage apiResponse = new ApiResponseMessage();
		try {
			log.debug("Deleting scheme details by id: {}",id);
			repo.deleteById(id);
			log.debug("Deleted scheme details by id: {}",id);
			apiResponse.setMessage(message.getSuccess());
			apiResponse.setStatus(Boolean.TRUE);
			apiResponse.setStatusCode(message.getCode200());
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		}
		catch (Exception e) {
			log.error("The Scheme is not deleted by id: {}",id);
			apiResponse.setMessage(message.getInternalServerError());
			apiResponse.setStatus(Boolean.FALSE);
			apiResponse.setStatusCode(message.getCode500());
			return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public ResponseEntity<ApiResponseMessage> deleteBySchemeName(String schemeName) {
		log.info("Entered into the Delete by Name method");
		ApiResponseMessage apiResponse = new ApiResponseMessage();
		try {
			log.debug("Deleting scheme details by name: {}",schemeName);
			Scheme scheme = repo.findBySchemeNameAndIsDeletedFalse(schemeName).get();
			Long id = scheme.getId();
			repo.deleteById(id);
			log.debug("Deleted scheme details by name: {}",schemeName);
			apiResponse.setMessage(message.getSuccess());
			apiResponse.setStatus(Boolean.TRUE);
			apiResponse.setStatusCode(message.getCode200());
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		}
		catch (Exception e) {
			log.error("The Scheme is not deleted by name: {}",schemeName);
			apiResponse.setMessage(message.getInternalServerError());
			apiResponse.setStatus(Boolean.FALSE);
			apiResponse.setStatusCode(message.getCode500());
			return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
//	@Override
//	public String deleteAll() {
//		log.info("Entered into the Delete All");
//		if(!repo.findAll().isEmpty())
//			{
//			repo.deleteAll();
//			return "Deleted all the Scheme details successfully";
//		}
//		else {
//			log.warn("All the Scheme is not deleted");
//			return "Error Message --> The db is empty.";
//		}
//	}

//	String result = status+'\n'+
//	"Id: "+scheme.getId()+'\n'+
//	"Name: "+scheme.getSchemeName()+'\n'+
//	"Description: "+scheme.getSchemeDescription()+'\n'+
//	"Deleted: "+scheme.getIsDeleted()+'\n'+
//	"Active: "+scheme.getIsActive()+'\n'+
//	"Auditing: "+'\n'+'\t'+
//		"Id: "+loggerId.getId()+'\n'+'\t'+
//		"Created By: "+loggerId.getCreatedBy()+'\n'+'\t'+
//		"Created At: "+loggerId.getCreatedAt()+'\n'+'\t'+
//		"Modified By: "+loggerId.getModifiedBy()+'\n'+'\t'+
//		"Modified At: "+loggerId.getModifiedAt();
//log.debug("Stored scheme details: {}",result);
	
//	@Override
//	public String update(SchemeModel schemeModel,String name) {
//		log.info("Entered into the Update method");
//		Optional<Scheme> optional = repo.findBySchemeNameAndIsDeletedFalse(name);
//		if(optional.isPresent())
//		{
//			log.debug("Updating scheme details");
//			Scheme scheme = optional.get();
//			scheme.setSchemeName(schemeModel.getSchemeName());
//			scheme.setSchemeDescription(schemeModel.getSchemeDescription());
//			scheme.setIsActive(schemeModel.getIsActive());
//			repo.saveAndFlush(scheme);
//			log.debug("Updated scheme details");
//			return "The name= "+name+" is updated successfully";
//		}
//		else
//		{
//			log.warn("The Scheme is not updated");
//			return "The name= "+name+" is not found in the db.";
//		}
//	}

//	try {
//	Scheme scheme =optional.get();
//	AuditingLogger loggerId = scheme.getAuditingLogger();
//	String result ='\n'+ "Id: "+scheme.getId()+'\n'+
//			"Name: "+scheme.getSchemeName()+'\n'+
//			"Description: "+scheme.getSchemeDescription()+'\n'+
//			"Deleted: "+scheme.getIsDeleted()+'\n'+
//			"Active: "+scheme.getIsActive()+'\n'+
//			"Auditing: "+'\n'+'\t'+
//				"Id: "+loggerId.getId()+'\n'+'\t'+
//				"Created By: "+loggerId.getCreatedBy()+'\n'+'\t'+
//				"Created At: "+loggerId.getCreatedAt()+'\n'+'\t'+
//				"Modified By: "+loggerId.getModifiedBy()+'\n'+'\t'+
//				"Modified At: "+loggerId.getModifiedAt();
//	log.debug("Fetched scheme with name {}",result);
//	return result;
	
//}
//catch(Exception e)
//{
//	log.warn("The Scheme Name: {} is not found",schemeName);
//	return "Error --> "+e.getMessage();
//}

