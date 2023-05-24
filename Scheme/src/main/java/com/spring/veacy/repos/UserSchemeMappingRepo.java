package com.spring.veacy.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.veacy.entity.Scheme;
import com.spring.veacy.entity.UserSchemeMapping;

public interface UserSchemeMappingRepo extends JpaRepository<UserSchemeMapping, Long> {

	UserSchemeMapping findByScheme(Scheme scheme);
}
