package com.test.quiz.entity;

import com.test.quiz.enums.Option;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tblquestiondetails")
public class QuestionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int questionDetailsId;

    @Column(nullable = false)
    private Option studentAnswer;

    @ManyToOne
    @JoinColumn(name = "quizId")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question question;

    public int getQuestionDetailsId() {
        return questionDetailsId;
    }

    public QuestionDetails setQuestionDetailsId(int questionDetailsId) {
        this.questionDetailsId = questionDetailsId;
        return this;
    }

    public Option getStudentAnswer() {
        return studentAnswer;
    }

    public QuestionDetails setStudentAnswer(Option studentAnswer) {
        this.studentAnswer = studentAnswer;
        return this;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public QuestionDetails setQuiz(Quiz quiz) {
        this.quiz = quiz;
        return this;
    }

    public Question getQuestion() {
        return question;
    }

    public QuestionDetails setQuestion(Question question) {
        this.question = question;
        return this;
    }

    public QuestionDetails(int questionDetailsId, Option studentAnswer, Quiz quiz, Question question) {
        this.questionDetailsId = questionDetailsId;
        this.studentAnswer = studentAnswer;
        this.quiz = quiz;
        this.question = question;
    }

    public QuestionDetails(){}
}
