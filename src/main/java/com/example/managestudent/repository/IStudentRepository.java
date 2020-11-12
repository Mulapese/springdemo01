package com.example.managestudent.repository;

import com.example.managestudent.model.Course;
import com.example.managestudent.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStudentRepository extends JpaRepository<Student, String> {
    List<Student> findByNameContaining(String name);
    List<Student> findByStudentIdIn(List<String> studentIds);
//    Student insertWithQuery()
}
