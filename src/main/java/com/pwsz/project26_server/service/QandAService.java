package com.pwsz.project26_server.service;

import com.pwsz.project26_server.domain.dto.QuestionDto;

public interface QandAService {

    public void readQandA(Long questionId, QuestionDto questionDto);

    public void setVariables(Long questionId, String line, QuestionDto questionDto);

    public boolean isAnswerCorrect(String correctAnswerNr);
}
