package com.spring.veacy.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.veacy.entity.AuditingLogger;
import com.spring.veacy.entity.Scheme;
import com.spring.veacy.model.SchemeModel;
import com.spring.veacy.repos.SchemeRepo;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class SchemeServiceImpl implements SchemeService {

	@Autowired
	SchemeRepo repo;
	
	private SchemeModel mapPersistenceModelToRestModel(Scheme scheme){
		SchemeModel schemeModel = new SchemeModel();
		schemeModel.setSchemeName(scheme.getSchemeName());
		schemeModel.setSchemeDescription(scheme.getSchemeDescription());
//		schemeModel.setIsDeleted(scheme.getIsDeleted());
        return schemeModel;
    }
	
	private void mapRestModelToPersistenceModel(SchemeModel schemeModel, Scheme scheme){
		scheme.setSchemeName(schemeModel.getSchemeName());
		scheme.setSchemeDescription(schemeModel.getSchemeDescription());
//		scheme.setIsDeleted(schemeModel.getIsDeleted());
	}
	
	@Override
	public List<Scheme> getAll() {
		log.info("Entered into the GetAll method");
		log.debug("Fetching all the scheme details");
		return repo.findAll();
	}

	@Override
	public String getById(Long id) {
		log.info("Entered into the GetById method");
		try {
			Optional<Scheme> optional = repo.findById(id);
			Scheme scheme =optional.get();
			AuditingLogger loggerId = scheme.getAuditingLogger();
			String result = '\n'+"Id: "+scheme.getId()+'\n'+
					"Name: "+scheme.getSchemeName()+'\n'+
					"Description: "+scheme.getSchemeDescription()+'\n'+
					"Deleted: "+scheme.getIsDeleted()+'\n'+
					"Auditing: "+'\n'+'\t'+
						"Id: "+loggerId.getId()+'\n'+'\t'+
						"Created By: "+loggerId.getCreatedBy()+'\n'+'\t'+
						"Created At: "+loggerId.getCreatedAt()+'\n'+'\t'+
						"Modified By: "+loggerId.getModifiedBy()+'\n'+'\t'+
						"Modified At: "+loggerId.getModifiedAt();
			log.debug("Fetching scheme with id: {}",result);
			return result;
		}
		catch(Exception e)
		{
			log.warn("The Id: {} is not found",id);
			return "Error --> "+e.getMessage();
		}
	}

	@Override
	public String findBySchemeName(String schemeName) {
		log.info("Entered into the GetByName method");
		try {
			log.debug("Fetching scheme with name: {}",schemeName);
			Optional<Scheme> optional = repo.findBySchemeName(schemeName);
			Scheme scheme =optional.get();
			AuditingLogger loggerId = scheme.getAuditingLogger();
			String result ='\n'+ "Id: "+scheme.getId()+'\n'+
					"Name: "+scheme.getSchemeName()+'\n'+
					"Description: "+scheme.getSchemeDescription()+'\n'+
					"Deleted: "+scheme.getIsDeleted()+'\n'+
					"Auditing: "+'\n'+'\t'+
						"Id: "+loggerId.getId()+'\n'+'\t'+
						"Created By: "+loggerId.getCreatedBy()+'\n'+'\t'+
						"Created At: "+loggerId.getCreatedAt()+'\n'+'\t'+
						"Modified By: "+loggerId.getModifiedBy()+'\n'+'\t'+
						"Modified At: "+loggerId.getModifiedAt();
			log.debug("Fetched scheme with name {}",result);
			return result;
		}
		catch(Exception e)
		{
			log.warn("The Scheme Name: {} is not found",schemeName);
			return "Error --> "+e.getMessage();
		}
	}

	@Override
	public String save(SchemeModel schemeModel) {
		log.info("Entered into the Create method");
		try {
			log.debug("Storing scheme details");
			Scheme scheme = new Scheme();
			AuditingLogger loggerId = new AuditingLogger();
			loggerId.setId(schemeModel.getAuditingId());
			scheme.setSchemeName(schemeModel.getSchemeName());
			scheme.setSchemeDescription(schemeModel.getSchemeDescription());
			scheme.setAuditingLogger(loggerId);
			repo.save(scheme);
			String status = "The scheme is created successfully";
			String result = status+'\n'+
					"Id: "+scheme.getId()+'\n'+
					"Name: "+scheme.getSchemeName()+'\n'+
					"Description: "+scheme.getSchemeDescription()+'\n'+
					"Deleted: "+scheme.getIsDeleted()+'\n'+
					"Auditing: "+'\n'+'\t'+
						"Id: "+loggerId.getId()+'\n'+'\t'+
						"Created By: "+loggerId.getCreatedBy()+'\n'+'\t'+
						"Created At: "+loggerId.getCreatedAt()+'\n'+'\t'+
						"Modified By: "+loggerId.getModifiedBy()+'\n'+'\t'+
						"Modified At: "+loggerId.getModifiedAt();
			log.debug("Stored scheme details: {}",result);
			return result;
		}
		catch(Exception e) {
			log.warn("The Scheme is not created");
			return "Error --> "+e.getMessage();
		}
	}

	@Override
	public String update(SchemeModel schemeModel,String name) {
		log.info("Entered into the Update method");
		Optional<Scheme> optional = repo.findBySchemeName(name);
		if(optional.isPresent())
		{
			log.debug("Updating scheme details");
			Scheme scheme = optional.get();
//			AuditingLogger loggerId = new AuditingLogger();
//			loggerId.setId(schemeModel.getAuditingId());
			scheme.setSchemeName(schemeModel.getSchemeName());
			scheme.setSchemeDescription(schemeModel.getSchemeDescription());
//			scheme.setAuditingLogger(loggerId);
			repo.saveAndFlush(scheme);
			log.debug("Updated scheme details");
			return "The name= "+name+" is updated successfully";
		}
		else
		{
			log.warn("The Scheme is not updated");
			return "The name= "+name+" is not found in the db.";
		}
	}

	@Override
	public String updated(Map<String, Object> updates, String name) {
		log.info("Entered into the Update method");
		Optional<Scheme> optional = repo.findBySchemeName(name);
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
//							case "isDeleted":
//								schemeModel.setIsDeleted((Boolean) value);
//								break;
							default:
								throw new IllegalArgumentException("Invalid field: "+field);
						}
					}
			);
			mapRestModelToPersistenceModel(schemeModel, scheme);
			repo.save(scheme);
			log.debug("Updated scheme details: {}",updates);
		return "Scheme Updated Successfully";
		}
		else {
			log.warn("The Scheme is not updated");
			return "The schemeName = "+name+"is not found.";
		}
	}

	@Override
	public String deleteById(Long id) {
		log.info("Entered into the Delete by Id method");
		try {
			log.debug("Deleting scheme details by id: {}",id);
			repo.deleteById(id);
			log.debug("Deleted scheme details by id: {}",id);
			return "Deleted Successfully";
		}
		catch (Exception e) {
			log.warn("The Scheme is not deleted by id: {}",id);
			return "Error Message --> "+e.getMessage();
		}
	}

	@Override
	public String deleteBySchemeName(String schemeName) {
		log.info("Entered into the Delete by Name method");
		try {
			log.debug("Deleting scheme details by name: {}",schemeName);
			Scheme scheme = repo.findBySchemeName(schemeName).get();
			Long id = scheme.getId();
			repo.deleteById(id);
			log.debug("Deleted scheme details by name: {}",schemeName);
//			repo.deleteBySchemeName(schemeName);
			return "Deleted Successfully";
		}
		catch (Exception e) {
			log.warn("The Scheme is not deleted by name: {}",schemeName);
			return "Error Message --> "+e.getMessage();
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

}
