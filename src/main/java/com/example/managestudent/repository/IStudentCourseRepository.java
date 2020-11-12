package com.example.managestudent.repository;

import com.example.managestudent.model.StudentCourse;
import com.example.managestudent.model.StudentCourseIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IStudentCourseRepository  extends JpaRepository<StudentCourse, StudentCourseIdentity> {

    @Query(value = "SELECT course_id FROM student_course sc WHERE student_id = ?1", nativeQuery = true)
    List<String> findCourseIdByStudentId(String studentId);
    @Query(value = "SELECT student_id FROM student_course sc WHERE course_id = ?1", nativeQuery = true)
    List<String> findStudentIdByCourseId(String courseId);
    List<StudentCourse> findByStudentId(String studentId);

}
