package com.spring.veacy.repos;

import java.util.Optional;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.veacy.entity.Scheme;

public interface SchemeRepo extends JpaRepository<Scheme, Long>{

//	@Query(nativeQuery = true, value="SELECT * FROM scheme_table WHERE scheme_name=? && is_deleted=false")
	Optional<Scheme> findBySchemeNameAndIsDeletedFalse(String schemeName);
	
	
	
}
