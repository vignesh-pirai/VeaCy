/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.spring.veacy.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.veacy.entity.User;
import com.spring.veacy.entity.UserSchemeMapping;

public interface UserSchemeMappingRepo extends JpaRepository<UserSchemeMapping, Long> {

	@Query(nativeQuery = true,value= "select * from user_scheme_mapping_table where is_deleted =false && scheme_id=?")
	List<UserSchemeMapping> findByScheme(Long id);
	
	@Query(nativeQuery = true,value= "select * from user_scheme_mapping_table where is_deleted =false && user_id=?")
	List<UserSchemeMapping> findByUser(Long id);
	
	@Query(nativeQuery = true,value= "select * from user_scheme_mapping_table where is_deleted =false && user_id=? && scheme_id=?")
	Optional<UserSchemeMapping> findBySchemeAndUser( Long uid,Long sid);
}
