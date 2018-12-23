package com.pwsz.project26_server.domain.dto;


import com.pwsz.project26_server.domain.dto.Answer;

import java.util.Arrays;

public class Question {

    private Long id;
    private String question;
    private Answer[] answers = new Answer[4];

    public Question(){}

    public Question(Long id, String question){
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

    public Answer[] getAnswers() {
        return answers;
    }

    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answers=" + Arrays.toString(answers) +
                '}';
    }
}
