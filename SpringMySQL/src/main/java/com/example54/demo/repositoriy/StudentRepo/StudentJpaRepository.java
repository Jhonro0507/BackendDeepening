package com.example54.demo.repositoriy.StudentRepo;

import com.example54.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentJpaRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s JOIN s.courses c WHERE c.name = :nombreCurso")
    List<Student> buscarEstudiantesPorNombreCurso(@Param("nombreCurso") String nombreCurso);
}
