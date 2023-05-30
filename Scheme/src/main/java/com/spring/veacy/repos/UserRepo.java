/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.spring.veacy.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.veacy.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

	Optional<User> findByNameAndIsDeletedFalse(String name);
}
