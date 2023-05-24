package com.spring.veacy.repos;

import java.util.Optional;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.veacy.entity.Scheme;

public interface SchemeRepo extends JpaRepository<Scheme, Long>{

	Optional<Scheme> findBySchemeName(String schemeName);
	
//	@Query("UPDATE Scheme SET isDeleted = true WHERE schemeName=?")
//	@SQLDelete(sql = "UPDATE scheme_table SET is_deleted = true WHERE scheme_name = ?")
	String deleteBySchemeName(String schemeName);
}
