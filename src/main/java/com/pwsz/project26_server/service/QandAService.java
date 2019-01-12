package com.pwsz.project26_server.service;

import com.pwsz.project26_server.domain.dto.QuestionDto;

public interface QandAService {

    void readQandA(Long questionId, QuestionDto questionDto);

    void setVariables(Long questionId, String line, QuestionDto questionDto);

    boolean isEmpty(QuestionDto questionDto);
}
