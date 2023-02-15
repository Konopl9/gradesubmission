package com.mishcma.gradesubmission.repository;

import org.springframework.data.repository.CrudRepository;

import com.mishcma.gradesubmission.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

}