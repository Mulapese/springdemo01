package com.example.managestudent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    private String studentId;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    public Student(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }
}
