package com.example.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentController {
  @Autowired
  private StudentService studentService;

  @PostMapping
  public Student createStudent(@RequestBody Student student) {
    return studentService.savStudent(student);
  }

  @PostMapping("/update-multiple")
  public List<Student> updateMultipleStudents(@RequestBody List<Student> students) {
    return studentService.updateMultipleStudents(students);
  }

  @GetMapping("/{id}")
  public Student getStudent(@PathVariable Long id) {
    return studentService.getStudent(id);
  }

  @GetMapping
  public List<Student> getStudents() {
    return studentService.getStudents();
  }

  @PutMapping("/{id}")
  public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
    return studentService.updateStudent(id, student);
  }

  @PostMapping("/{studentId}/subjects/{subjectId}")
  public ResponseEntity<?> assignSubjectToStudent(@PathVariable Long studentId, @PathVariable Long subjectId) {
    studentService.assignSubjectToStudent(studentId, subjectId);
    return ResponseEntity.ok("Subject assigned successfully.");
  }

}
