package com.example54.demo.repositoriy.CourseRepo;

import com.example54.demo.model.Course;

import java.util.List;

public interface CourseRepository {
    //Traer todos los objetos de la entidad student
    List<Course> findAll();
    //Buscar un Student por un ID
    Course findById(Long id);
    // Crear un Student
    Course create(Course student);
    // Actualizar un Student por Id
    Course update(Long id, Course student);
    // Eliminar un Student por Id
    Boolean delete(Long id);
}
