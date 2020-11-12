package com.example.managestudent.service;

import com.example.managestudent.model.Course;
import com.example.managestudent.model.Student;
import com.example.managestudent.repository.ICourseRepository;
import com.example.managestudent.repository.IStudentCourseRepository;
import com.example.managestudent.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService{

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private IStudentCourseRepository studentCourseRepository;

    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> findById(String courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public Course insert(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Student> findStudentByCourseId(String coursesId) {
        List<String> studentIds = studentCourseRepository.findStudentIdByCourseId(coursesId);
        List<Student> students = studentRepository.findByStudentIdIn(studentIds);
        return students;
    }

    @Override
    public List<Course> findByNameContaining(String name){
        return courseRepository.findByNameContaining(name);
    }

    @Override
    public void deleteById(String id){
        courseRepository.deleteById(id);
    }

}
