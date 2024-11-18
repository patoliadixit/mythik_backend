package com.example.demo.student;

import java.util.List;
import java.util.Set;

import com.example.demo.subject.Subject;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToMany
  Set<Subject> subjects;

  private String firstName;
  private String lastName;
  private int age;

  // Required no-args constructor for JPA
  public Student() {
  }

  public Student(Long id, String firstName, String lastName, int age) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Long getId() {
    return this.id;
  }

  public Set<Subject> getSubjects() {
    return this.subjects;
  }

  public Subject setSubject(Subject subject) {
    this.subjects.add(subject);
    return subject;
  }
}