package com.pwsz.project26_server.domain.dto;


import javax.validation.constraints.NotNull;

public class AnswerDto {

    private String answer;
    private boolean isCorrect;

    public AnswerDto(){}

    public AnswerDto(@NotNull String answer, boolean isCorrect) throws EmptyAnswerDto {
        if(answer.isEmpty())
            throw new EmptyAnswerDto();
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer='" + answer + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
