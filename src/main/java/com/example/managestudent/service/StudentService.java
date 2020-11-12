package com.example.managestudent.service;

import com.example.managestudent.model.Course;
import com.example.managestudent.model.Student;
import com.example.managestudent.model.StudentCourse;
import com.example.managestudent.repository.ICourseRepository;
import com.example.managestudent.repository.IStudentCourseRepository;
import com.example.managestudent.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService{
    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private IStudentCourseRepository studentCourseRepository;

    @Autowired
    private ICourseRepository courseRepository;

    public List<Course> findCourseByStudentId(String studentId){
        List<String> courseIds = studentCourseRepository.findCourseIdByStudentId(studentId);
        List<Course> courses = courseRepository.findByCourseIdIn(courseIds);
        return courses;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(String id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student insert(Student student) {
        return null;
    }

    @Override
    public List<Student> findByNameContaining(String name) {
        return studentRepository.findByNameContaining(name);
    }

    @Override
    public StudentCourse addCourseToStudent(StudentCourse studentCourse) {
        return studentCourseRepository.save(studentCourse);
    }
}
