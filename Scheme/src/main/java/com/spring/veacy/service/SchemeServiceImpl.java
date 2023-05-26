package com.spring.veacy.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.veacy.entity.AuditingLogger;
import com.spring.veacy.entity.Scheme;
import com.spring.veacy.model.SchemeModel;
import com.spring.veacy.repos.SchemeRepo;
import com.spring.veacy.response.ApiResponseMessage;
import com.spring.veacy.response.ErrorConstants;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class SchemeServiceImpl implements SchemeService {

	@Autowired
	SchemeRepo repo;
	
	@Autowired
	ErrorConstants message;
	
	private SchemeModel mapPersistenceModelToRestModel(Scheme scheme){
		SchemeModel schemeModel = new SchemeModel();
		schemeModel.setSchemeName(scheme.getSchemeName());
		schemeModel.setSchemeDescription(scheme.getSchemeDescription());
        return schemeModel;
    }
	
	private void mapRestModelToPersistenceModel(SchemeModel schemeModel, Scheme scheme){
		scheme.setSchemeName(schemeModel.getSchemeName());
		scheme.setSchemeDescription(schemeModel.getSchemeDescription());
	}
	
	@Override
	public List<Scheme> getAll() {
		log.info("Entered into the GetAll method");
		log.debug("Fetching all the scheme details");
		return repo.findAll();
	}

	
	@Override
	public ResponseEntity<Scheme> getBySchemeName(String schemeName) {
		log.info("Entered into the GetByName method");

			log.debug("Fetching scheme with name: {}",schemeName);
			Optional<Scheme> scheme = repo.findBySchemeNameAndIsDeletedFalse(schemeName);
			if(scheme.isEmpty()) {
				return null;
			}
			return ResponseEntity.ok(repo.findBySchemeNameAndIsDeletedFalse(schemeName).get()) ;

	}

	@Override
	public ResponseEntity<ApiResponseMessage> save(SchemeModel schemeModel) {
		log.info("Entered into the Create method");
		ApiResponseMessage response = new ApiResponseMessage();
		try {
			log.debug("Storing scheme details");
			Scheme scheme = new Scheme();
			AuditingLogger loggerId = new AuditingLogger();
			if(repo.findBySchemeNameAndIsDeletedFalse(schemeModel.getSchemeName()).isEmpty()) {
//			scheme.setSchemeName(schemeModel.getSchemeName());
//			scheme.setSchemeDescription(schemeModel.getSchemeDescription());
			BeanUtils.copyProperties(schemeModel, scheme);
//			BeanUtils.copyProperties(loggerId.getId(), scheme);
			loggerId.setId(schemeModel.getAuditingId());
			scheme.setAuditingLogger(loggerId);
			repo.save(scheme);
			response.setMessage(message.getSuccess());
			response.setStatus(Boolean.TRUE);
			response.setStatusCode(message.getCode200());
			return new ResponseEntity<>(response, HttpStatus.OK);
			}
			else {
				response.setMessage(message.getSchemeExists());
				response.setStatus(Boolean.FALSE);
				response.setStatusCode(message.getCode500());
				return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
			}
		}
		catch(Exception e) {
			log.error("The Scheme is not created");
			log.error("Error --> "+e.getMessage());
			response.setMessage(message.getInternalServerError());
			response.setStatus(Boolean.FALSE);
			response.setStatusCode(message.getCode500());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@Override
	public ResponseEntity<ApiResponseMessage> updated(Map<String, Object> updates, String name) {
		log.info("Entered into the Update method");
		ApiResponseMessage response = new ApiResponseMessage();
		try {
		Optional<Scheme> optional = repo.findBySchemeNameAndIsDeletedFalse(name);
		if(optional.isPresent())
		{
			Scheme scheme = optional.get();
			SchemeModel schemeModel = mapPersistenceModelToRestModel(scheme);
			log.debug("Updating scheme details");
			updates.forEach(
					(field,value)->{
						switch(field) {
							case "schemeName": 
								schemeModel.setSchemeName((String) value);
								break;
							case "schemeDescription":
								schemeModel.setSchemeDescription((String) value);
								break;
							case "isActive":
								schemeModel.setIsActive((Boolean) value);
								break;
							default:
								throw new IllegalArgumentException("Invalid field: "+field);
						}
					}
			);
			mapRestModelToPersistenceModel(schemeModel, scheme);
			repo.save(scheme);
			log.debug("Updated scheme details: {}",updates);
			response.setMessage(message.getSuccess());
			response.setStatus(Boolean.TRUE);
			response.setStatusCode(message.getCode200());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			log.warn("The Scheme is not updated");
			response.setMessage(message.getSchemeNotFound());
			response.setStatus(Boolean.FALSE);
			response.setStatusCode(message.getCode500());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		}
		catch(Exception e)
		{
			response.setMessage(message.getInternalServerError());
			response.setStatus(Boolean.TRUE);
			response.setStatusCode(message.getCode500());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	public ResponseEntity<ApiResponseMessage> deleteById(Long id) {
		log.info("Entered into the Delete by Id method");
		ApiResponseMessage response = new ApiResponseMessage();
		try {
			log.debug("Deleting scheme details by id: {}",id);
			repo.deleteById(id);
			log.debug("Deleted scheme details by id: {}",id);
			response.setMessage(message.getSuccess());
			response.setStatus(Boolean.TRUE);
			response.setStatusCode(message.getCode200());
//			return "Deleted Successfully";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch (Exception e) {
			log.error("The Scheme is not deleted by id: {}",id);
			response.setMessage(message.getInternalServerError());
			response.setStatus(Boolean.FALSE);
			response.setStatusCode(message.getCode500());
//			return "Error Message --> "+e.getMessage();
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ApiResponseMessage> deleteBySchemeName(String schemeName) {
		log.info("Entered into the Delete by Name method");
		ApiResponseMessage response = new ApiResponseMessage();
		try {
			log.debug("Deleting scheme details by name: {}",schemeName);
			Scheme scheme = repo.findBySchemeNameAndIsDeletedFalse(schemeName).get();
			Long id = scheme.getId();
			repo.deleteById(id);
			log.debug("Deleted scheme details by name: {}",schemeName);
			response.setMessage(message.getSuccess());
			response.setStatus(Boolean.TRUE);
			response.setStatusCode(message.getCode200());
//			return "Deleted Successfully";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch (Exception e) {
			log.error("The Scheme is not deleted by name: {}",schemeName);
//			return "Error Message --> "+e.getMessage();
			response.setMessage(message.getInternalServerError());
			response.setStatus(Boolean.FALSE);
			response.setStatusCode(message.getCode500());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
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

}
