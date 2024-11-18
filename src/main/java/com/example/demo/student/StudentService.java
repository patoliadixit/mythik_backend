package com.example.demo.student;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.subject.Subject;
import com.example.demo.subject.SubjectRepository;

@Service
public class StudentService {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private SubjectRepository subjectRepository;

  public Student savStudent(Student student) {
    return studentRepository.save(student);
  }

  public List<Student> getStudents() {
    return studentRepository.findAll();
  }

  public Student getStudent(Long id) {
    return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
  }

  public Student updateStudent(Long id, Student inputStudent) {
    Student student = studentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No student found with the id " + id));
    student.setFirstName(inputStudent.getFirstName());
    student.setLastName(inputStudent.getLastName());
    student.setAge(inputStudent.getAge());
    return studentRepository.save(student);
  }

  public Student assignSubjectToStudent(Long studentId, Long subjectId) {
    Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new RuntimeException("Student not found"));
    Subject subject = subjectRepository.findById(subjectId)
        .orElseThrow(() -> new RuntimeException("Subject not found"));
    if (student.subjects.contains(subject)) {
      throw new RuntimeException("Subject already assigned to the student.");
    }
    student.setSubject(subject);
    return studentRepository.save(student);
  }

  @Async
  private CompletableFuture<Student> updateStudentAsync(Student student) {
    Student existingStudent = studentRepository.findById(student.getId())
        .orElseThrow(() -> new RuntimeException("No student found with the id " + student.getId()));
    existingStudent.setFirstName(student.getFirstName());
    existingStudent.setLastName(student.getLastName());
    existingStudent.setAge(student.getAge());
    Student updatedStudent = studentRepository.save(existingStudent);
    return CompletableFuture.completedFuture(updatedStudent);
  }

  public List<Student> updateMultipleStudents(List<Student> students) {
    List<CompletableFuture<Student>> futures = students.stream()
        .map(this::updateStudentAsync)
        .collect(Collectors.toList());

    CompletableFuture<Void> allOf = CompletableFuture.allOf(
        futures.toArray(new CompletableFuture[0]));

    try {
      allOf.join();
      return futures.stream()
          .map(CompletableFuture::join)
          .collect(Collectors.toList());
    } catch (Exception e) {
      throw new RuntimeException("Error updating students: " + e.getMessage());
    }
  }
}
