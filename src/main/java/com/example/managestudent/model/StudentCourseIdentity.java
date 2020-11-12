package com.example.managestudent.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

@Data
public class StudentCourseIdentity implements Serializable {
    @Id
    private String studentId;
    @Id
    private String courseId;
}
