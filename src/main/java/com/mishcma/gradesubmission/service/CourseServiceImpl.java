package com.mishcma.gradesubmission.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.mishcma.gradesubmission.entity.Course;
import com.mishcma.gradesubmission.entity.Student;
import com.mishcma.gradesubmission.exception.EntityNotFoundException;
import com.mishcma.gradesubmission.repository.CourseRepository;
import com.mishcma.gradesubmission.repository.StudentRepository;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    
    @Override
    public Course getCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return unwrapCourse(course, id);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {  
        courseRepository.deleteById(id);      
    }

    @Override
    public List<Course> getCourses() {
        return (List<Course>)courseRepository.findAll();
    }

    @Override
    public Course addStudentToCourse(Long studentId, Long courseId) {
        Course course = getCourse(courseId);
        Optional<Student> student = studentRepository.findById(studentId);
        Student unwrappedStudent = StudentServiceImpl.unwrapStudent(student, studentId);
        course.getStudents().add(unwrappedStudent);
        return courseRepository.save(course);
    }

    @Override
    public Set<Student> getEnrolledStudents(Long id) {
        Course course = getCourse(id);
        return course.getStudents();
    }

    static Course unwrapCourse(Optional<Course> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Course.class);
    }


}
