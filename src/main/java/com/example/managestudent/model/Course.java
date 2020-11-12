package com.example.managestudent.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table(name = "course")
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    private String courseId;

    private String name;

    private String description;

}
