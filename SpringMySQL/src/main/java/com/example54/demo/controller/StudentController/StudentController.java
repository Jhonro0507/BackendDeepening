package com.example54.demo.controller.StudentController;

import com.example54.demo.model.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentController {
    List<Student> getAllStudents();
    ResponseEntity<Student> getStudentById(Long id);
    ResponseEntity<Student> createStudent(Student student);
    ResponseEntity<Student> updateStudent(Long id, Student student);
    ResponseEntity<Void> deleteStudent(Long id);
}

