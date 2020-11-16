package com.example.managestudent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "student1")
@AllArgsConstructor
@NoArgsConstructor
public class Student1 {
    @Id
    private String studentId;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    private Date createdTime;
}
