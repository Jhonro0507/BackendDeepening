package com.example54.demo.repositoriy.CourseRepo;

import com.example54.demo.model.Course;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CourseRepositoryImpl implements CourseRepository{

    private CourseJpaRepository courseJpaRepository;

    @Autowired
    public CourseRepositoryImpl(CourseJpaRepository courseJpaRepository){
        this.courseJpaRepository = courseJpaRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseJpaRepository.findAll().size()>0? courseJpaRepository.findAll() :null;
    }

    @Override
    public Course findById(Long id) {
        return courseJpaRepository.findById(id).isPresent() ? courseJpaRepository.findById(id).get():null;
    }

    @Override
    public Course create(Course course) {
        return courseJpaRepository.save(course);
    }

    @Override
    public Course update(Long id, Course course) {
        Course courseFound = findById(id);
        if (courseFound != null){
            courseFound.setName(course.getName());
            courseFound.setInstructor(course.getInstructor());
            courseFound.setStudents(course.getStudents());
            return courseJpaRepository.save(courseFound);
        } else{
            return null;
        }
    }

    @Override
    public Boolean delete(Long id) {
        Course courseFound = findById(id);
        if (courseFound != null){
            courseJpaRepository.delete(courseFound);
            return true;
        }else {
            return false;
        }
    }
}
