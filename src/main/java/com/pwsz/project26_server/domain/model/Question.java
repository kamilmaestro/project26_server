package com.pwsz.project26_server.domain.model;

public interface Question {

    int howManyLines(Long questionId);

    int randQuestionNr(Long questionId);
}
