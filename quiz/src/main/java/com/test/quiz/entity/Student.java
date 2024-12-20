package com.test.quiz.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Entity(name = "tblstudent")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int studentId;

    @Column(nullable = false)
    private String userName;

    @Email
    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Quiz> quizList;

    public int getStudentId() {
        return studentId;
    }

    public Student setStudentId(int studentId) {
        this.studentId = studentId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public Student setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Student setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Student setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public Student setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
        return this;
    }

    public Student(int studentId, String userName, String email, String password, List<Quiz> quizList) {
        this.studentId = studentId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.quizList = quizList;
    }

    public Student(){}
}
