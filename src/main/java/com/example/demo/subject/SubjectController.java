package com.example.demo.subject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/subjects")
@CrossOrigin
public class SubjectController {
  @Autowired
  private SubjectService subjectService;

  @PostMapping
  public Subject createSubject(@RequestBody Subject subject) {
    return subjectService.saveSubject(subject);
  }

  @GetMapping
  public List<Subject> getSubjects() {
    return subjectService.getSubjects();
  }

  @PutMapping("/{id}")
  public Subject updateSubject(@PathVariable Long id, @RequestBody Subject subject) {
    return subjectService.updateSubject(id, subject);
  }

  @DeleteMapping("/{id}")
  public void deleteSubject(@PathVariable Long id) {
    subjectService.deleteSubject(id);
  }
}
