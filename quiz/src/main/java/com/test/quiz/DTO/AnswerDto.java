package com.test.quiz.DTO;


import com.test.quiz.enums.Option;
import jakarta.persistence.Column;

public class AnswerDto {
    @Column(nullable = false)
    private int questionId;

    @Column(nullable = false)
    private Option answer;

    public int getQuestionId() {
        return questionId;
    }

    public AnswerDto setQuestionId(int questionId) {
        this.questionId = questionId;
        return this;
    }

    public Option getAnswer() {
        return answer;
    }

    public AnswerDto setAnswer(String answer) {
        this.answer = Option.valueOf(answer);
        return this;
    }

    public AnswerDto(int questionId, String answer) {
        this.questionId = questionId;
        this.answer = Option.valueOf(answer);
    }

    public AnswerDto(){}
}
