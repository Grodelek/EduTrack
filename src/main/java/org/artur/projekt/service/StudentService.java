package org.artur.projekt.service;

import org.artur.projekt.models.Student;
import org.artur.projekt.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    public void removeStudentById(Long id){
        studentRepository.deleteById(id);
    }
}
