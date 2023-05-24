package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    public List<Student> getAll() {
        return studentRepository.findAll();
    }


    public void createStudent(Student student) {

        //Kendi mesajımızı vermek için custom exception ürettik
        if(studentRepository.existsByEmail(student.getEmail())){
            throw new ConflictException("Email is exist already!! ");
        }
        studentRepository.save(student);
    }


    public Student findStudent(Long id) {
        //NullPointerException almamak için Java'ya Optional yapılar eklendi.
        //Student varsa göndercem yoksa içi boş bir obje göndercem diyor yani Optional yapı gönderiyor
        //Öğrenci varsa gönderiyor yoksa orElseThrow()
         return studentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Student not found with id: " + id));
    }


    public void deleteStudent(Long id) {
        Student student = findStudent(id); //İlgili id'li öğrenci var mı kontrol ediyor.
        studentRepository.delete(student); //Yukardan gelen student'ı buraya yazıyorum.

    }
}
