package com.pwsz.project26_server.domain.dto;

public class EmptyAnswerDto extends NullPointerException{
    private static final String ERROR_MESSAGE = "The answer is empty!";

    public EmptyAnswerDto(){
        super(ERROR_MESSAGE);
    }
}
