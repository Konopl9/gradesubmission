package com.mishcma.gradesubmission.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mishcma.gradesubmission.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByUsername(String username);
}