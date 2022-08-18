package com.hfla.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hfla.demo.models.Student;
import com.hfla.demo.repositories.StudentRepository;

@Component
public class StudentService {

  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> getStudents() {
		return studentRepository.findAll();
	}

  public Student addStudent(Student student) {
    Optional<Student> optionalStudent = studentRepository.findStudentByEmail(student.getEmail());
    
    if(optionalStudent.isPresent()) {
      throw new IllegalStateException("Student with email " + student.getEmail() + " already exists");
    } 

    return studentRepository.save(student);
  }

  public void deleteStudent(Long id) {
    boolean studentExists = studentRepository.existsById(id);

    if(!studentExists) {
      throw new IllegalStateException("Student with id " + id + " does not exist");
    }
    
    studentRepository.deleteById(id);
  }

  @Transactional
  public Student updateStudent(Long id, String name, String email) {
    Optional<Student> existingStudent = studentRepository.findById(id);
    
    if(!existingStudent.isPresent()) {
      throw new IllegalStateException("Student with id " + id + " does not exist");
    }

    Student student = existingStudent.get();
    if(name != null && name.length() > 0 && !name.equals(student.getName())) {
      student.setName(name);
    }

    if(email != null && email.length() > 0 && !email.equals(student.getEmail())) {
      Optional<Student> existingStudentWithEmail = studentRepository.findStudentByEmail(email);
      if(existingStudentWithEmail.isPresent()) {
        throw new IllegalStateException("Student with email " + email + " already exists");
      }
      student.setEmail(email);
    }

    return student;
  }
}