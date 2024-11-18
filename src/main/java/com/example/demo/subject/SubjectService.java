package com.example.demo.subject;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;

@Service
public class SubjectService {

  @Autowired
  private SubjectRepository subjectRepository;
  @Autowired
  private StudentRepository studentRepository;

  public Subject saveSubject(Subject subject) {
    Optional<Subject> alreadyExists = subjectRepository.findByName(subject.getName());
    if (alreadyExists.isPresent()) {
      throw new RuntimeException("Subject with name " + subject.getName() + " already exists.");
    }
    return subjectRepository.save(subject);
  }

  public void deleteSubject(Long id) {
    Set<Student> students = studentRepository.findBySubjectsId(id);
    System.out.println(students);
    if (students.size() != 0) {
      throw new RuntimeException(
          "Can not delete the subject as there are " + students.size() + " students present in the subject");
    }
    subjectRepository.deleteById(id);
  }

  public Subject updateSubject(Long id, Subject inputSubject) {
    Subject subject = subjectRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No subject found with ID:" + id));

    subject.setName(inputSubject.getName());
    return subjectRepository.save(subject);
  }

  public List<Subject> getSubjects() {
    return subjectRepository.findAll();
  }
}
