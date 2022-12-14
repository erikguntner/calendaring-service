package com.hfla.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hfla.demo.models.Student;
import com.hfla.demo.services.StudentService;


@RestController
@RequestMapping(path = "/students")
public class StudentController {

  private final StudentService studentService;
  
  @Autowired
  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
	public List<Student> getStudents() {
		return studentService.getStudents();
	}

  @PostMapping
  public Student addStudent(@RequestBody Student student) {
    return studentService.addStudent(student);
  }

  @DeleteMapping(path = "/{id}")
  public void deleteStudent(@PathVariable("id") Long id) {
    studentService.deleteStudent(id);
  }

  @PutMapping(path = "/{id}")
  public Student updateStudent(@PathVariable("id") Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String email) {
    return studentService.updateStudent(id, name, email);
  }
  
}
