package com.example.managestudent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "student_course")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(StudentCourseIdentity.class)
public class StudentCourse implements Serializable {

    @Id
    private String studentId;
    @Id
    private String courseId;

    private Date startDate;

    private Date endDate;
}
