package com.pwsz.project26_server.domain.dto;

public class EmptyAnswer extends NullPointerException{
    private static final String ERROR_MESSAGE = "The answer is empty!";

    public EmptyAnswer(){
        super(ERROR_MESSAGE);
    }
}
