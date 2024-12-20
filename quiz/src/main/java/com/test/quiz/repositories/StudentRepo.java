package com.test.quiz.repositories;

import com.test.quiz.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    Student findByEmail(String email);
}
