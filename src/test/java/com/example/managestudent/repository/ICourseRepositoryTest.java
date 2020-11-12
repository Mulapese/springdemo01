package com.example.managestudent.repository;

import com.example.managestudent.model.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ICourseRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ICourseRepository courseRepository;

    @Test
    public void whenFindByNameContaining_thenReturnCourse() {
        // given
        Course alex = new Course("XML201", "XML201", "XML201");
        entityManager.persist(alex);
        entityManager.flush();

        // when
        List<Course> found = courseRepository.findByNameContaining(alex.getName());

        // then
        assert (found.get(0).getName().equalsIgnoreCase(alex.getName()));
    }
}