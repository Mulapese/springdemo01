package com.example.managestudent.service;

import com.example.managestudent.controller.CourseController;
import com.example.managestudent.model.Course;
import com.example.managestudent.repository.ICourseRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
//@WebMvcTest(CourseService.class)
//@AutoConfigureMockMvc(addFilters = false)
class CourseServiceTest {

    @Mock
    private ICourseRepository courseRepository;

    @InjectMocks
//    @Mock
//    @Autowired
    private CourseService courseService;

    @Test
    void findAll() {
        Course course = new Course();
        course.setName("Test name");
        Mockito.doReturn(Optional.of(course)).when(courseRepository).findByNameContaining("Test name");
        List<Course> courses = Arrays.asList(course);
        Mockito.doReturn(courses).when(courseRepository).findAll();

        List<Course> actualCourses = courseService.findAll();
        assert (actualCourses.size() == 1);
    }

    @Test
    void findById() {
    }

    @Test
    void insert() {
    }

    @Test
    void findStudentByCourseId() {
    }

    @Test
    void findByNameContaining() {
    }

    @Test
    void deleteById() {
    }
}