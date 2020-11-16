package com.example.managestudent.repository;

import com.example.managestudent.model.Course;
import com.example.managestudent.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentRepository extends JpaRepository<Student, String> {
    List<Student> findByNameContaining(String name);
    List<Student> findByStudentIdIn(List<String> studentIds);
//    Student insertWithQuery()
}
