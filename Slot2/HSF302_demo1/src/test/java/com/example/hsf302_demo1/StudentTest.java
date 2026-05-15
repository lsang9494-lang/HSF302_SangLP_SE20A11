package com.example.hsf302_demo1;

import com.example.hsf302_demo1.Student;
import com.example.hsf302_demo1.service.StudentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class StudentTest {

    @Autowired
    private StudentService studentService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testCreateAndRetrieveStudentFromDatabase() {
        studentService.createStudent("Test Student", "test@fpt.edu.vn", 25);
        entityManager.flush();
        entityManager.clear();

        Student created = entityManager
                .createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class)
                .setParameter("email", "test@fpt.edu.vn")
                .getSingleResult();

        assertNotNull(created, "Student should exist in database");
        assertEquals("Test Student", created.getFullName());
        assertEquals("test@fpt.edu.vn", created.getEmail());
        assertEquals(25, created.getAge());
    }
}
