package com.example54.demo.repositoriy.StudentRepo;

import com.example54.demo.model.Student;

import java.util.List;

public interface StudentRepository {
    //Traer todos los objetos de la entidad student
    List<Student> findAll();
    //Buscar un Student por un ID
    Student findById(Long id);
    // Crear un Student
    Student create(Student student);
    // Actualizar un Student por Id
    Student update(Long id, Student student);
    // Eliminar un Student por Id
    Boolean deleteStudent(Long id);
}
