package com.example.managestudent.repository;

import com.example.managestudent.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseRepository extends JpaRepository<Course, String> {
    List<Course> findByNameContaining(String name);
    List<Course> findByCourseIdIn(List<String> courseIds);

}
