package com.pwsz.project26_server.domain.dto;

public class Answer {

    private String answer;
    private boolean isCorrect;

    public Answer(String answer, boolean isCorrect) throws EmptyAnswer {
        if(answer.isEmpty())
            throw new EmptyAnswer();
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
