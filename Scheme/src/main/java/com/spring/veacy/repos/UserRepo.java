package com.spring.veacy.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.veacy.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

	Optional<User> findByNameAndIsDeletedFalse(String name);
}
