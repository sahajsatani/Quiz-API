package com.test.quiz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "tblquiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int quizId;

    @Column(nullable = false)
    private LocalDate date;

    private int totalAttemptQuestion=0;

    private int totalCorrect=0;

    private int totalIncorrect=0;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

    @OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL)
    private List<QuestionDetails> questionDetailsList;

    public int getQuizId() {
        return quizId;
    }

    public Quiz setQuizId(int quizId) {
        this.quizId = quizId;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public Quiz setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public int getTotalAttemptQuestion() {
        return totalAttemptQuestion;
    }

    public Quiz setTotalAttemptQuestion(int totalAttemptQuestion) {
        this.totalAttemptQuestion = totalAttemptQuestion;
        return this;
    }

    public int getTotalCorrect() {
        return totalCorrect;
    }

    public Quiz setTotalCorrect(int totalCorrect) {
        this.totalCorrect = totalCorrect;
        return this;
    }

    public int getTotalIncorrect() {
        return totalIncorrect;
    }

    public Quiz setTotalIncorrect(int totalIncorrect) {
        this.totalIncorrect = totalIncorrect;
        return this;
    }

    public Student getStudent() {
        return student;
    }

    public Quiz setStudent(Student student) {
        this.student = student;
        return this;
    }

    public List<QuestionDetails> getQuestionDetailsList() {
        return questionDetailsList;
    }

    public Quiz setQuestionDetailsList(List<QuestionDetails> questionDetailsList) {
        this.questionDetailsList = questionDetailsList;
        return this;
    }

    public Quiz(int quizId, LocalDate date, int totalAttemptQuestion, int totalCorrect, int totalIncorrect, Student student, List<QuestionDetails> questionDetailsList) {
        this.quizId = quizId;
        this.date = date;
        this.totalAttemptQuestion = totalAttemptQuestion;
        this.totalCorrect = totalCorrect;
        this.totalIncorrect = totalIncorrect;
        this.student = student;
        this.questionDetailsList = questionDetailsList;
    }

    public Quiz(){}
}
