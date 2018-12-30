package com.pwsz.project26_server.service.impl;

import com.pwsz.project26_server.domain.dto.AnswerDto;
import com.pwsz.project26_server.domain.dto.QuestionDto;
import com.pwsz.project26_server.domain.model.QuestionDAO;
import com.pwsz.project26_server.service.QandAService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class QandAServiceImpl implements QandAService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QandAService.class);

    @Autowired
    QuestionDAO questionDAO;

    @Override
    public void readQandA(Long questionId, QuestionDto questionDto) {
        String line = "";
        int randNrQuestion = questionDAO.randQuestionNr(questionId);

        try{
            BufferedReader reader = new BufferedReader(new FileReader(Long.toString(questionId) + ".txt"));
            line = reader.readLine();
            while (line != null){
                if(line.startsWith(Integer.toString(randNrQuestion))){
                    setVariables(line, questionDto);
                }
            }
            reader.close();
        }catch (FileNotFoundException ex){
            LOGGER.error("File not found!");
        }catch (IOException ex){
            LOGGER.error("I can not read this file!");
        }
    }

    @Override
    public void setVariables(String line, QuestionDto questionDto) {
        String [] parts = line.split(";");
        AnswerDto[] answersDto = new AnswerDto[4];

        questionDto.setQuestion(parts[1]);
        for(int i = 2; i < 6; i++){
            if(parts[6].equals(Integer.toString(i - 1))) {
                answersDto[i] = new AnswerDto(line, true);
            }else {
                answersDto[i] = new AnswerDto(line, false);
            }
            //questionDto.setAnswers(answersDto);
        }
    }
}
