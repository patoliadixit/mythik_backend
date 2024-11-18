package com.example.demo.subject;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
  Optional<Subject> findByName(String name);

  Set<Subject> findByStudentsId(Long studentId);
}
