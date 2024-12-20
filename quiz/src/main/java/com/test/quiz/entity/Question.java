package com.test.quiz.entity;

import com.test.quiz.enums.Option;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "tblquestion")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int questionId;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String a;

    @Column(nullable = false)
    private String b;

    @Column(nullable = false)
    private String c;

    @Column(nullable = false)
    private String d;

    @Column(nullable = false)
    private Option answer;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    private List<QuestionDetails> questionDetailsList;


    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", question='" + question + '\'' +
                ", A='" + a + '\'' +
                ", B='" + b + '\'' +
                ", C='" + c + '\'' +
                ", D='" + d + '\'' +
                ", answer=" + answer +
                '}';
    }

    public int getQuestionId() {
        return questionId;
    }

    public Question setQuestionId(int questionId) {
        this.questionId = questionId;
        return this;
    }

    public String getQuestion() {
        return question;
    }

    public Question setQuestion(String question) {
        this.question = question;
        return this;
    }

    public String getA() {
        return a;
    }

    public Question setA(String a) {
        this.a = a;
        return this;
    }

    public String getB() {
        return b;
    }

    public Question setB(String b) {
        this.b = b;
        return this;
    }

    public String getC() {
        return c;
    }

    public Question setC(String c) {
        this.c = c;
        return this;
    }

    public String getD() {
        return d;
    }

    public Question setD(String d) {
        this.d = d;
        return this;
    }

    public Option getAnswer() {
        return answer;
    }

    public Question setAnswer(String answer) {
        this.answer = Option.valueOf(answer);
        return this;
    }

    public List<QuestionDetails> getQuestionDetailsList() {
        return questionDetailsList;
    }

    public Question setQuestionDetailsList(List<QuestionDetails> questionDetailsList) {
        this.questionDetailsList = questionDetailsList;
        return this;
    }

    public Question(int questionId, String question, String a, String b, String c, String d, Option answer, List<QuestionDetails> questionDetailsList) {
        this.questionId = questionId;
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answer = answer;
        this.questionDetailsList = questionDetailsList;
    }

    public Question(){}
}
