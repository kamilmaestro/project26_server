package com.pwsz.project26_server.domain.dto;


import com.pwsz.project26_server.domain.dto.AnswerDto;

import java.util.Arrays;

public class QuestionDto {

    private Long id;
    private String question;
    private AnswerDto[] answers = new AnswerDto[4];

    public QuestionDto(){}

    public QuestionDto(Long id, String question){
        this.id = id;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public AnswerDto[] getAnswers() {
        return answers;
    }

    public void setAnswers(AnswerDto[] answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "QuestionDto{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answers=" + Arrays.toString(answers) +
                '}';
    }
}
