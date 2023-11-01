package com.example54.demo.service.StudentService;


import com.example54.demo.model.Student;
import com.example54.demo.repositoriy.StudentRepo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.InvocationTargetException;


import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student create(Student student){
        return studentRepository.create(student);
    }

    @Override
    public Student update(Long id, Student student) {
        return studentRepository.update(id, student);
    }

    @Override
    public boolean deleteStudent(Long id) {
        return studentRepository.deleteStudent(id);
    }
}

