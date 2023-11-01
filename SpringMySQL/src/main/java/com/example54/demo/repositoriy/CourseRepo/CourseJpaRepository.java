package com.example54.demo.repositoriy.CourseRepo;


import com.example54.demo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseJpaRepository extends JpaRepository<Course, Long> {
}
