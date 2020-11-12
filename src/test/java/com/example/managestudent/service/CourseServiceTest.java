package com.example.managestudent.service;

import com.example.managestudent.model.Course;
import com.example.managestudent.repository.ICourseRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class CourseServiceTest {

    @Mock
    ICourseRepository courseRepository;

    @InjectMocks
    CourseService courseService;

    @Test
    void findAll() {
        Course course = new Course();
        course.setName("Test name");
        Mockito.doReturn(Optional.of(course)).when(courseRepository).findByNameContaining("Test name");
        List<Course> courses = Arrays.asList(course);
        Mockito.doReturn(courses).when(courseRepository).findAll();

        List<Course> actualCourses = (List<Course>) courseService.findAll();
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