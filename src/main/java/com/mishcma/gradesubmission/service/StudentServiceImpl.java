package com.mishcma.gradesubmission.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.mishcma.gradesubmission.entity.Course;
import com.mishcma.gradesubmission.entity.Student;
import com.mishcma.gradesubmission.exception.EntityNotFoundException;
import com.mishcma.gradesubmission.repository.StudentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {


    private StudentRepository studentRepository;

    @Override
    public Student getStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return unwrapStudent(student, id);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {      
        studentRepository.deleteById(id);  
    }

    @Override
    public List<Student> getStudents() {
        return (List<Student>)studentRepository.findAll();
    }

    @Override
    public Set<Course> getEnrolledCourses(Long id) {
        Student student = getStudent(id);
        return student.getCourses();
    }

    static Student unwrapStudent(Optional<Student> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Student.class);
    }

}