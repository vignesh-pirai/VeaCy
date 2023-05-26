package com.spring.veacy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.veacy.entity.AuditingLogger;
import com.spring.veacy.entity.Scheme;
import com.spring.veacy.entity.User;
import com.spring.veacy.entity.UserSchemeMapping;
import com.spring.veacy.model.SchemeModel;
import com.spring.veacy.model.UserSchemeMappingModel;
import com.spring.veacy.repos.UserSchemeMappingRepo;
import com.spring.veacy.response.ApiResponseMessage;
import com.spring.veacy.response.ErrorConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserSchemeMappingServiceImpl implements UserSchemeMappingService{

	@Autowired
	UserSchemeMappingRepo repo;
	
	@Autowired
	ErrorConstants message;
	
	@Override
	public List<UserSchemeMapping> getAll() {
		log.info("Entered into the GetAll method");
		log.debug("Fetching all the User-Scheme Mapping details");
		return repo.findAll();
	}

	@Override
	public Optional<UserSchemeMapping> getById(Long id) {
		log.info("Entered into the GetById method");
		log.debug("Fetching User-Scheme Mapping with id {}",id);
		return repo.findById(id);
	}

	@Override
	public List<UserSchemeMapping> getBySchemeId(Long id) {
		
		return repo.findByScheme(id);
	}

	@Override
	public List<UserSchemeMapping> getByUserId(Long id) {
		return repo.findByUser(id);
	}
	
	@Override
	public ResponseEntity<ApiResponseMessage> save(UserSchemeMappingModel userSchemeMappingModel) {
		log.info("Entered into the Create method");
		ApiResponseMessage response = new ApiResponseMessage();
		try {
			log.debug("Storing User-Scheme Mapping details");
			Optional<UserSchemeMapping> list = repo.findBySchemeAndUser(userSchemeMappingModel.getUserId(), userSchemeMappingModel.getSchemeId());
			if(list.isEmpty())
			{
			UserSchemeMapping mapping = new UserSchemeMapping();
			User user = new User();
			user.setId(userSchemeMappingModel.getUserId());
			Scheme scheme = new Scheme();
			scheme.setId(userSchemeMappingModel.getSchemeId());
			AuditingLogger logger = new AuditingLogger();
			logger.setId(userSchemeMappingModel.getAuditingId());
			mapping.setUser(user);
			mapping.setScheme(scheme);
			mapping.setCommitmentAmount(userSchemeMappingModel.getCommitmentAmount());
			mapping.setAuditingId(logger);
			repo.save(mapping);
			log.debug("Stored User-Scheme Mapping details");
			response.setMessage(message.getSuccess());
			response.setStatus(Boolean.TRUE);
			response.setStatusCode(message.getCode200());
//			return "Created Successfully";
			return new ResponseEntity<>(response, HttpStatus.OK);
			}
			else
			{
				response.setMessage(message.getUserSchemeExists());
				response.setStatus(Boolean.FALSE);
				response.setStatusCode(message.getCode500());
//				return "Already Exists";
				response.setMessage(message.getSchemeExists());
				response.setStatus(Boolean.FALSE);
				response.setStatusCode(message.getCode500());
				return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
			}
		}
		catch(Exception e)
		{
			log.warn("The User-Scheme Mapping is not created");
			response.setMessage(message.getSchemeExists());
			response.setStatus(Boolean.FALSE);
			response.setStatusCode(message.getCode500());
//			return "Error --> "+e.getMessage();
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

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

	@Override
//	public String updated(Map<String, Object> updates, Long uid, Long sid) {
	public ResponseEntity<ApiResponseMessage> updated(Map<String, Object> updates, Long id) {
		log.info("Entered into the Update method");
		ApiResponseMessage response = new ApiResponseMessage();
		Optional<UserSchemeMapping> optional = repo.findById(id);
//		final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
//		final UserSchemeMappingModel pojo = mapper.convertValue(updates, UserSchemeMappingModel.class);
//		Optional<UserSchemeMapping> optional = repo.findBySchemeAndUser(pojo.getUserId(),pojo.getSchemeId());
		try{
			if(optional.isPresent())
//		if(optional.isEmpty())
			{
				UserSchemeMapping userSchemeMapping = optional.get();
				log.debug("Updating User-Scheme Mapping details");
				updates.forEach(
						(field,value)->{
							switch(field)
							{
								case "userId": 
									User user = new User();
									user.setId(Long.valueOf((int) value));
									userSchemeMapping.setUser(user);
									break;
								case "schemeId":
									Scheme scheme = new Scheme();
									scheme.setId(Long.valueOf((int) value));
									userSchemeMapping.setScheme(scheme);
									break;
								case "commitmentAmount":
	//								userSchemeMapping.setCommitmentAmount((Double) value);
									if (value instanceof Integer) {
				                        userSchemeMapping.setCommitmentAmount(((Integer) value).doubleValue());
				                    } else {
				                        userSchemeMapping.setCommitmentAmount((Double) value);
				                    }
									break;
								default: throw new IllegalArgumentException("Invalid field: "+field); 
							}
						}
				);
				repo.save(userSchemeMapping);
				log.debug("Updated User-Scheme Mapping details: {}",updates);
				response.setMessage(message.getSuccess());
				response.setStatus(Boolean.TRUE);
				response.setStatusCode(message.getCode200());
//				return "Updated Successfully";
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			else
			{
				log.warn("The User-Scheme Mapping is not updated");
	//			return "The User id = "+uid+", Scheme id = "+sid+"is not found.";
//				return "The id = "+id+"is not found.";
				response.setMessage(message.getSchemeNotFound());
				response.setStatus(Boolean.FALSE);
				response.setStatusCode(message.getCode500());
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			response.setMessage(message.getSchemeExists());
			response.setStatus(Boolean.FALSE);
			response.setStatusCode(message.getCode500());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ApiResponseMessage> deleteById(Long id) {
		log.info("Entered into the Delete by Id method");
		ApiResponseMessage response = new ApiResponseMessage();
		try {
			log.debug("Deleting User-Scheme Mapping details by id: {}",id);
			repo.deleteById(id);
			log.debug("Deleted User-Scheme Mapping details by id: {}",id);
			response.setMessage(message.getSuccess());
			response.setStatus(Boolean.TRUE);
			response.setStatusCode(message.getCode200());
//			return "Deleted Successfully";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch (Exception e) {
			log.warn("The User-Scheme Mapping is not deleted by id: {}",id);
			response.setMessage(message.getSchemeExists());
			response.setStatus(Boolean.FALSE);
			response.setStatusCode(message.getCode500());
//			return "Error Message --> "+e.getMessage();
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
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



}
