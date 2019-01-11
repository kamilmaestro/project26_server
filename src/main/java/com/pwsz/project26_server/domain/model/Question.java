package com.pwsz.project26_server.domain.model;

import com.pwsz.project26_server.domain.dto.QuestionDto;

public interface Question {

    int howManyLines(Long questionId);

    int randQuestionNr(Long questionId) throws Exception;

    boolean isEmpty(QuestionDto questionDto);
}
