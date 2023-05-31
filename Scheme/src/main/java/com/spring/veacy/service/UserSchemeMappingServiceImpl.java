/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.spring.veacy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.veacy.apiresponse.ApiResponseMessage;
import com.spring.veacy.apiresponse.ErrorConstants;
import com.spring.veacy.apiresponse.UserSchemeMappingApiResponse;
import com.spring.veacy.entity.AuditingLogger;
import com.spring.veacy.entity.Scheme;
import com.spring.veacy.entity.User;
import com.spring.veacy.entity.UserSchemeMapping;
import com.spring.veacy.exception.InvalidArgumentException;
import com.spring.veacy.repos.SchemeRepo;
import com.spring.veacy.repos.UserRepo;
import com.spring.veacy.repos.UserSchemeMappingRepo;
import com.spring.veacy.request.SchemeRequest;
import com.spring.veacy.request.UserSchemeMappingRequest;
import com.spring.veacy.response.UserSchemeMappingResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * The UserSchemeMappingServiceImpl class provides an implementation of the
 * UserSchemeMappingService interface, handling CRUD operations and other
 * actions on UserSchemeMapping entities.
 */
@Service
@Slf4j
public class UserSchemeMappingServiceImpl implements UserSchemeMappingService {

	@Autowired
	UserSchemeMappingRepo repo;

	@Autowired
	SchemeRepo srepo;

	@Autowired
	UserRepo urepo;

	@Autowired
	ErrorConstants message;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<UserSchemeMappingApiResponse> getAll() {
		log.info("Entered into the GetAll method");
		log.debug("Fetching all the User-Scheme Mapping details");
		UserSchemeMappingApiResponse apiResponse = new UserSchemeMappingApiResponse();
		try {
			List<UserSchemeMapping> userSchemeMappingList = repo.findAll();
			if (userSchemeMappingList.isEmpty()) {
				log.warn("No User Scheme Mapping Details are available");
				apiResponse.setMessage("The User-Scheme Mapping table is empty");
				apiResponse.setStatus(Boolean.FALSE);
				apiResponse.setStatusCode(message.getCode200());
				return new ResponseEntity<>(apiResponse, HttpStatus.NOT_ACCEPTABLE);
			} else {
				log.debug("The displayed details are: \n" + userSchemeMappingList);
			}
			List<UserSchemeMappingResponse> list = new ArrayList<>();
			for (UserSchemeMapping userSchemeMapping : userSchemeMappingList) {
				UserSchemeMappingResponse response = new UserSchemeMappingResponse();
				response.setUserName(userSchemeMapping.getUser().getName());
				response.setSchemeName(userSchemeMapping.getScheme().getSchemeName());
//				response.setCommitmentAmount(userSchemeMapping.getCommitmentAmount());
				BeanUtils.copyProperties(userSchemeMapping, response);
				list.add(response);
			}
			apiResponse.setMessage(message.getSuccess());
			apiResponse.setStatus(Boolean.TRUE);
			apiResponse.setStatusCode(message.getCode200());
			apiResponse.setUserSchemeMappingModelList(list);
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
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
	public ResponseEntity<UserSchemeMappingApiResponse> getById(Long id) {
		log.info("Entered into the GetById method");
		log.debug("Fetching User-Scheme Mapping with id {}", id);
		UserSchemeMappingApiResponse apiResponse = new UserSchemeMappingApiResponse();
		try {
			Optional<UserSchemeMapping> optional = repo.findById(id);
			UserSchemeMapping userSchemeMapping = optional.get();
			if (optional.isEmpty()) {
				log.warn("No User Scheme Mapping Details are available");
				apiResponse.setMessage("The User-Scheme Mapping table is empty");
				apiResponse.setStatus(Boolean.FALSE);
				apiResponse.setStatusCode(message.getCode200());
				return new ResponseEntity<>(apiResponse, HttpStatus.NOT_ACCEPTABLE);
			} else {
				log.debug("The displayed details are: \n" + userSchemeMapping);
			}
			List<UserSchemeMappingResponse> list = new ArrayList<>();
			UserSchemeMappingResponse response = new UserSchemeMappingResponse();
			response.setUserName(userSchemeMapping.getUser().getName());
			response.setSchemeName(userSchemeMapping.getScheme().getSchemeName());
//			response.setCommitmentAmount(userSchemeMapping.getCommitmentAmount());
			BeanUtils.copyProperties(userSchemeMapping, response);
			list.add(response);
			apiResponse.setMessage(message.getSuccess());
			apiResponse.setStatus(Boolean.TRUE);
			apiResponse.setStatusCode(message.getCode200());
			apiResponse.setUserSchemeMappingModelList(list);
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
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
	public ResponseEntity<UserSchemeMappingApiResponse> getBySchemeId(Long id) {

		UserSchemeMappingApiResponse apiResponse = new UserSchemeMappingApiResponse();
		try {
			List<UserSchemeMapping> userSchemeMappingList = repo.findByScheme(id);
			if (userSchemeMappingList.isEmpty()) {
				log.warn("No User Scheme Mapping Details are available");
				apiResponse.setMessage("The scheme id in User-Scheme Mapping table is not found");
				apiResponse.setStatus(Boolean.FALSE);
				apiResponse.setStatusCode(message.getCode200());
				return new ResponseEntity<>(apiResponse, HttpStatus.NOT_ACCEPTABLE);
			} else {
				log.debug("The displayed details are: \n" + userSchemeMappingList);
			}
			List<UserSchemeMappingResponse> list = new ArrayList<>();
			for (UserSchemeMapping userSchemeMapping : userSchemeMappingList) {
				UserSchemeMappingResponse response = new UserSchemeMappingResponse();
				response.setUserName(userSchemeMapping.getUser().getName());
				response.setSchemeName(userSchemeMapping.getScheme().getSchemeName());
//				response.setCommitmentAmount(userSchemeMapping.getCommitmentAmount());
				BeanUtils.copyProperties(userSchemeMapping, response);
				list.add(response);
			}
			apiResponse.setMessage(message.getSuccess());
			apiResponse.setStatus(Boolean.TRUE);
			apiResponse.setStatusCode(message.getCode200());
			apiResponse.setUserSchemeMappingModelList(list);
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
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
	public ResponseEntity<UserSchemeMappingApiResponse> getByUserId(Long id) {
		UserSchemeMappingApiResponse apiResponse = new UserSchemeMappingApiResponse();
		try {
			List<UserSchemeMapping> userSchemeMappingList = repo.findByUser(id);
			if (userSchemeMappingList.isEmpty()) {
				log.warn("No User Scheme Mapping Details are available");
				apiResponse.setMessage("The user id in User-Scheme Mapping table is not found");
				apiResponse.setStatus(Boolean.FALSE);
				apiResponse.setStatusCode(message.getCode200());
				return new ResponseEntity<>(apiResponse, HttpStatus.NOT_ACCEPTABLE);
			} else {
				log.debug("The displayed details are: \n" + userSchemeMappingList);
			}
			List<UserSchemeMappingResponse> list = new ArrayList<>();
			for (UserSchemeMapping userSchemeMapping : userSchemeMappingList) {
				UserSchemeMappingResponse response = new UserSchemeMappingResponse();
				response.setUserName(userSchemeMapping.getUser().getName());
				response.setSchemeName(userSchemeMapping.getScheme().getSchemeName());
//				response.setCommitmentAmount(userSchemeMapping.getCommitmentAmount());
				BeanUtils.copyProperties(userSchemeMapping, response);
				list.add(response);
			}
			apiResponse.setMessage(message.getSuccess());
			apiResponse.setStatus(Boolean.TRUE);
			apiResponse.setStatusCode(message.getCode200());
			apiResponse.setUserSchemeMappingModelList(list);
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
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
	public ResponseEntity<ApiResponseMessage> save(UserSchemeMappingRequest request) {
		log.info("Entered into the Create method");
		ApiResponseMessage apiResponse = new ApiResponseMessage();
		if (!(request.getUserId().toString().isBlank() || request.getSchemeId().toString().isBlank()
				|| request.getCommitmentAmount().toString().isBlank()
				|| request.getAuditingId().toString().isBlank())) {
			try {
				log.debug("Storing User-Scheme Mapping details");
//			String schemeName = srepo.findById(request.getSchemeId()).get().getSchemeName();
//			if(srepo.findBySchemeNameAndIsDeletedFalse(schemeName).isPresent()) {
				if (srepo.findById(request.getSchemeId()).isEmpty() && urepo.findById(request.getUserId()).isEmpty()) {
					apiResponse.setMessage(message.getUserSchemeNotFound());
					apiResponse.setStatus(Boolean.FALSE);
					apiResponse.setStatusCode(message.getCode500());
					return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
				} else if (urepo.findById(request.getUserId()).isEmpty()) {
					apiResponse.setMessage(message.getUserNotFound());
					apiResponse.setStatus(Boolean.FALSE);
					apiResponse.setStatusCode(message.getCode500());
					return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
				} else if (srepo.findById(request.getSchemeId()).isEmpty()) {
					apiResponse.setMessage(message.getSchemeNotFound());
					apiResponse.setStatus(Boolean.FALSE);
					apiResponse.setStatusCode(message.getCode500());
					return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
				}
				Optional<UserSchemeMapping> list = repo.findBySchemeAndUser(request.getUserId(), request.getSchemeId());
				if (list.isEmpty()) {
					UserSchemeMapping mapping = new UserSchemeMapping();
					User user = new User();
					user.setId(request.getUserId());
					Scheme scheme = new Scheme();
					scheme.setId(request.getSchemeId());
					AuditingLogger logger = new AuditingLogger();
					logger.setId(request.getAuditingId());
					mapping.setUser(user);
					mapping.setScheme(scheme);
					mapping.setCommitmentAmount(request.getCommitmentAmount());
					mapping.setAuditingId(logger);
					repo.save(mapping);
					log.debug("Stored User-Scheme Mapping details");
					apiResponse.setMessage(message.getSuccess());
					apiResponse.setStatus(Boolean.TRUE);
					apiResponse.setStatusCode(message.getCode200());
					return new ResponseEntity<>(apiResponse, HttpStatus.OK);
				} else {
					apiResponse.setMessage(message.getSchemeExists());
					apiResponse.setStatus(Boolean.FALSE);
					apiResponse.setStatusCode(message.getCode500());
					return new ResponseEntity<>(apiResponse, HttpStatus.NOT_ACCEPTABLE);
				}
			} catch (Exception e) {
				log.warn("The User-Scheme Mapping is not created");
				apiResponse.setMessage(message.getInternalServerError());
				apiResponse.setStatus(Boolean.FALSE);
				apiResponse.setStatusCode(message.getCode500());
				return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			throw new InvalidArgumentException("Invalid input");
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<ApiResponseMessage> updated(Map<String, Object> updates, Long id) {
		log.info("Entered into the Update method");
		ApiResponseMessage apiResponse = new ApiResponseMessage();
		Optional<UserSchemeMapping> optional = repo.findById(id);
		try {
			if (optional.isPresent()) {
				UserSchemeMapping userSchemeMapping = optional.get();
				log.debug("Updating User-Scheme Mapping details");
				updates.forEach((field, value) -> {
					switch (field) {
					case "userId":
						User user = new User();
						user.setId(Long.valueOf((int) value));
						if (urepo.findById(user.getId()).isPresent()) {
							if (repo.findBySchemeAndUser(user.getId(), userSchemeMapping.getScheme().getId())
									.isEmpty()) {
								userSchemeMapping.setUser(user);
							} else {
								throw new EntityExistsException();
							}
						} else {
							apiResponse.setMessage(message.getUserNotFound());
							throw new EntityNotFoundException();
						}
						break;
					case "schemeId":
						Scheme scheme = new Scheme();
						scheme.setId(Long.valueOf((int) value));
						if (srepo.findById(scheme.getId()).isPresent()) {
							if (repo.findBySchemeAndUser(userSchemeMapping.getUser().getId(), scheme.getId())
									.isEmpty()) {
								userSchemeMapping.setScheme(scheme);
							} else {
								throw new EntityExistsException();
							}
						} else {
							apiResponse.setMessage(message.getSchemeNotFound());
							throw new EntityNotFoundException();
						}

						break;
					case "commitmentAmount":
						if (value instanceof Integer) {
							userSchemeMapping.setCommitmentAmount(((Integer) value).doubleValue());
						} else {
							userSchemeMapping.setCommitmentAmount((Double) value);
						}
						break;
					default:
						throw new IllegalArgumentException("Invalid field: " + field);
					}
				});
				repo.save(userSchemeMapping);
				log.debug("Updated User-Scheme Mapping details: {}", updates);
				apiResponse.setMessage(message.getSuccess());
				apiResponse.setStatus(Boolean.TRUE);
				apiResponse.setStatusCode(message.getCode200());
				return new ResponseEntity<>(apiResponse, HttpStatus.OK);
			} else {
				log.warn("The User-Scheme Mapping is not updated");
				apiResponse.setMessage(message.getSchemeNotFound());
				apiResponse.setStatus(Boolean.FALSE);
				apiResponse.setStatusCode(message.getCode500());
				return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
			}
		} catch (EntityExistsException e) {
			apiResponse.setMessage(message.getUserSchemeExists());
			apiResponse.setStatus(Boolean.FALSE);
			apiResponse.setStatusCode(message.getCode500());
			return new ResponseEntity<>(apiResponse, HttpStatus.NOT_ACCEPTABLE);
		} catch (EntityNotFoundException e) {
			apiResponse.setStatus(Boolean.FALSE);
			apiResponse.setStatusCode(message.getCode500());
			return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			apiResponse.setMessage(message.getInternalServerError());
			apiResponse.setStatus(Boolean.FALSE);
			apiResponse.setStatusCode(message.getCode500());
			return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ApiResponseMessage> deleteById(Long id) {
		log.info("Entered into the Delete by Id method");
		ApiResponseMessage apiResponse = new ApiResponseMessage();
		try {
			log.debug("Deleting User-Scheme Mapping details by id: {}", id);
			repo.deleteById(id);
			log.debug("Deleted User-Scheme Mapping details by id: {}", id);
			apiResponse.setMessage(message.getSuccess());
			apiResponse.setStatus(Boolean.TRUE);
			apiResponse.setStatusCode(message.getCode200());
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			log.warn("The User-Scheme Mapping is not deleted by id: {}", id);
			apiResponse.setMessage(message.getSchemeExists());
			apiResponse.setStatus(Boolean.FALSE);
			apiResponse.setStatusCode(message.getCode500());
			return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

//	@Override
//	public String deleteAll() {
//		if(!repo.findAll().isEmpty())
//		{
//		repo.deleteAll();
//		return "Deleted all the Scheme details successfully";
//	}
//		else {
//			return "Error Message --> The db is empty.";
//		}
//	}

//	@Override
//	public String update(UserSchemeMappingModel userSchemeMappingModel, Long id) {
//		log.info("Entered into the Update method");
//		Optional<UserSchemeMapping> optional = repo.findById(id);
//		if(optional.isPresent())
//		{
//			log.debug("Updating User-Scheme Mapping details");
//			UserSchemeMapping mapping = optional.get();
//			User user = new User();
//			user.setId(userSchemeMappingModel.getUserId());
//			Scheme scheme = new Scheme();
//			scheme.setId(userSchemeMappingModel.getSchemeId());
//			mapping.setUser(user);
//			mapping.setScheme(scheme);
//			mapping.setCommitmentAmount(userSchemeMappingModel.getCommitmentAmount());
//			repo.saveAndFlush(mapping);
//			log.debug("Updated User-Scheme Mapping details");
//			return "The id= "+id+" is updated successfully";
//		}
//		else
//		{
//			log.warn("The User-Scheme Mapping is not updated");
//			return "The id= "+id+" is not found";
//		}
//	}
