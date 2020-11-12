package com.example.managestudent.controller;

import com.example.managestudent.exception.RecordNotFoundException;
import com.example.managestudent.model.Course;
import com.example.managestudent.model.Student;
import com.example.managestudent.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
@Api(value = "CourseController", description = "REST APIs related to Course Entity.")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "Get list of Courses")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No content") })
    @GetMapping
    public ResponseEntity<List<Course>> getCourses(@RequestParam(required = false) String name){
        List<Course> courses = new ArrayList<>();

        if (name == null)
            courses.addAll(courseService.findAll());
        else {
            courses.addAll(courseService.findByNameContaining(name));
        }

        if (courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @ApiOperation(value = "Get Courses by courseId")
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") String id) {
        Optional<Course> courseData = courseService.findById(id);

        if (courseData.isPresent()) {
            return new ResponseEntity<>(courseData.get(), HttpStatus.OK);
        } else {
            throw new RecordNotFoundException("The courseId " + id + " is not exist");
        }
    }


    @ApiOperation(value = "Add a course")
    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Optional<Course> courseData = courseService.findById(course.getCourseId());
        if (courseData.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        Course _course = courseService.insert(course);
        return new ResponseEntity<>(_course, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a course")
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") String id, @RequestBody Course course) {
        Optional<Course> courseData = courseService.findById(id);

        if (courseData.isPresent()) {
            Course _course = courseData.get();
            _course.setName(course.getName());
            _course.setDescription(course.getDescription());
            return new ResponseEntity<>(courseService.insert(_course), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Delete a course")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") String id) {
        Optional<Course> courseData = courseService.findById(id);
        if(courseData.isPresent()) {
            courseService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Get list of student filter by courseId")
    @GetMapping("/{id}/students")
    public ResponseEntity<List<Student>> getStudentsByCourse(@PathVariable String id){
        return new ResponseEntity<>(courseService.findStudentByCourseId(id), HttpStatus.OK);
    }
}
