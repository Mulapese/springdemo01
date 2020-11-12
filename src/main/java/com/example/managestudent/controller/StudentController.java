package com.example.managestudent.controller;

import com.example.managestudent.model.Course;
import com.example.managestudent.model.Student;
import com.example.managestudent.model.StudentCourse;
import com.example.managestudent.repository.IStudentCourseRepository;
import com.example.managestudent.repository.IStudentRepository;
import com.example.managestudent.service.StudentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@Api(value = "CourseController", description = "REST APIs related to Student Entity.")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private IStudentCourseRepository studentCourseRepository;

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(@RequestParam(required = false) String name){
        try {
            List<Student> students = new ArrayList<>();

            if (name == null)
                studentService.findAll().forEach(students::add);
            else {
                studentService.findByNameContaining(name).forEach(students::add);
            }

            if (students.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<List<Course>> getCoursesByStudent(@PathVariable String id){
        return new ResponseEntity<>(studentService.findCourseByStudentId(id), HttpStatus.OK);
    }

    @PostMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<StudentCourse> addCourseToStudent(@PathVariable String studentId, @RequestBody StudentCourse studentCourse){
        return new ResponseEntity<>(studentService.addCourseToStudent(studentCourse), HttpStatus.CREATED);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<StudentCourse>> getStudentCourse(){
        return new ResponseEntity<>(studentCourseRepository.findAll(), HttpStatus.OK);
    }

}
