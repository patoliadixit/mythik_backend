package com.example.demo.student;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
  Set<Student> findBySubjectsId(Long subjectId);
}