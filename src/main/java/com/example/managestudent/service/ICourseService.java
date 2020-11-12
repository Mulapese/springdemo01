package com.example.managestudent.service;

import com.example.managestudent.model.Course;
import com.example.managestudent.model.Student;
import com.example.managestudent.repository.ICourseRepository;

import java.util.List;
import java.util.Optional;

public interface ICourseService{
    List<Course> findAll();
    Optional<Course> findById(String id);
    Course insert(Course course);
    List<Student> findStudentByCourseId(String coursesId);
}
