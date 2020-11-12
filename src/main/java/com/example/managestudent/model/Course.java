package com.example.managestudent.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "course")
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @ApiModelProperty(notes = "The database generated product ID")
    private String courseId;

    @ApiModelProperty(notes = "The database generated product ID")
    private String name;

    @ApiModelProperty(notes = "The database generated product ID")
    private String description;

}
