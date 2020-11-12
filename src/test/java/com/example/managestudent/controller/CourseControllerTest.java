package com.example.managestudent.controller;

import com.example.managestudent.model.Course;
import com.example.managestudent.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CourseController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CourseControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CourseService service;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getCourses_noParams() throws Exception {
        Course course = new Course("XML201_Id", "XML201_Name", "XML201_Description");
        List<Course> allCourses = Arrays.asList(course);

        given(service.findAll()).willReturn(allCourses);



        mvc.perform(get("/courses")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].courseId", is(course.getCourseId())))
                .andExpect(jsonPath("$[0].name", is(course.getName())))
                .andExpect(jsonPath("$[0].description", is(course.getDescription())));
    }

    @Test
    public void getCourses_hasValidName() throws Exception {
        String name = "ValidName";
        Course course1 = new Course("XML201_Id", name + "01", "XML201_Description");
        Course course2 = new Course("XML201_Id", name + "02", "XML201_Description");
        Course course3 = new Course("XML201_Id", "Course03", "XML201_Description");
        List<Course> allCourses = Arrays.asList(course1, course2);

        given(service.findByNameContaining(Mockito.anyString())).willReturn(allCourses);

        mvc.perform(get("/courses?name=" + name)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(course1.getName())))
                .andExpect(jsonPath("$[1].name", is(course2.getName())));
    }

    @Test
    public void getCourses_hasInvalidName() throws Exception {
        String name = "ValidName";

        List<Course> courses = new ArrayList<>();

        given(service.findByNameContaining(Mockito.anyString())).willReturn(courses);

        mvc.perform(get("/courses?name=" + name)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getCourseById_hasValidId() throws Exception {
        Course course = new Course("XML201_Id", "XML201_Name", "XML201_Description");
        given(service.findById(Mockito.anyString())).willReturn(java.util.Optional.of(course));

        mvc.perform(get("/courses/" + course.getCourseId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseId", is(course.getCourseId())))
                .andExpect(jsonPath("$.name", is(course.getName())))
                .andExpect(jsonPath("$.description", is(course.getDescription())));
    }

    @Test
    public void getCourseById_hasInvalidId() throws Exception {
        String id = "SampleId";
        given(service.findById(Mockito.anyString())).willReturn(Optional.ofNullable(null));

        mvc.perform(get("/courses/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addCourse_hasValidCourse() throws Exception {
        Course course = new Course("XML201_Id", "XML201_Name", "XML201_Description");
        mvc.perform(post("/courses")
        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isCreated());
    }

    @Test
    public void addCourse_hasNullBody() throws Exception {
        mvc.perform(post("/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateCourse_hasValidIdAndValidCourse() throws Exception {
        Course course = new Course("XML201_Id", "XML201_Name", "XML201_Description");
        given(service.findById(Mockito.anyString())).willReturn(Optional.of(course));
        given(service.insert(Mockito.any())).willReturn(course);

        mvc.perform(put("/courses/" + course.getCourseId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseId", is(course.getCourseId())))
                .andExpect(jsonPath("$.name", is(course.getName())))
                .andExpect(jsonPath("$.description", is(course.getDescription())));
    }

    @Test
    public void updateCourse_hasInvalidId() throws Exception {
        Course course = new Course("XML201_Id", "XML201_Name", "XML201_Description");
        given(service.findById(Mockito.anyString())).willReturn(Optional.ofNullable(null));

        mvc.perform(put("/courses/" + course.getCourseId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteCourse_hasValidId() throws Exception {
        Course course = new Course("XML201_Id", "XML201_Name", "XML201_Description");
        mvc.perform(delete("/courses/" + course.getCourseId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteCourse_hasInvalidId() throws Exception {
        doThrow(new Exception()).when(service).deleteById(Mockito.anyString());
        Course course = new Course("XML201_Id", "XML201_Name", "XML201_Description");
        mvc.perform(delete("/courses/" + course.getCourseId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}