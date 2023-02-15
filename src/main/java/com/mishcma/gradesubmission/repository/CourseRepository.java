package com.mishcma.gradesubmission.repository;

import org.springframework.data.repository.CrudRepository;

import com.mishcma.gradesubmission.entity.Course;


public interface CourseRepository extends CrudRepository<Course, Long> {

}