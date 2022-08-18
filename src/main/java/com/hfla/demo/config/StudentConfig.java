package com.hfla.demo.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hfla.demo.models.Student;
import com.hfla.demo.repositories.StudentRepository;

@Configuration
public class StudentConfig {
  
  @Bean
  CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
      return args -> {
        Student erik = new Student("Erik", "erikgutner@gmail.com", LocalDate.of(1990, 1, 1));
        Student mariam = new Student("Mariam", "Mariam@gmail.com", LocalDate.of(1991, 1, 1));

        studentRepository.saveAll(List.of(erik, mariam));
      };
  }
};
