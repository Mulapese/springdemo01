package com.example.managestudent.service;

import com.example.managestudent.model.Course;
import com.example.managestudent.model.Student;
import com.example.managestudent.model.StudentCourse;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<Student> findAll();
    Optional<Student> findById(String id);
    Student insert(Student student);
    List<Student> findByNameContaining(String name);
    StudentCourse addCourseToStudent(StudentCourse studentCourse);
}
