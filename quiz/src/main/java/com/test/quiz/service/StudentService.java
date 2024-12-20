package com.test.quiz.service;

import com.test.quiz.entity.Student;
import com.test.quiz.repositories.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepo studentRepo;
    public ResponseEntity<?> registerStudent(Student student) {
        try{
            if(studentRepo.findByEmail(student.getEmail())==null){
                studentRepo.save(student);
                return new ResponseEntity<>("Student saved.",HttpStatus.OK);
            }else
                return new ResponseEntity<>("Already exist.",HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
