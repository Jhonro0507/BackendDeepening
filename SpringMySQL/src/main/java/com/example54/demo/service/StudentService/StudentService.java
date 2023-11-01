package com.example54.demo.service.StudentService;

import com.example54.demo.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findById(Long id);
    Student create(Student student);
    Student update(Long id, Student student);
    boolean deleteStudent(Long id);
}
