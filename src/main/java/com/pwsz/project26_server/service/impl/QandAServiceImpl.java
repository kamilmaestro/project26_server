package com.pwsz.project26_server.service.impl;

import com.pwsz.project26_server.domain.dto.QuestionDto;
import com.pwsz.project26_server.domain.model.QuestionDAO;
import com.pwsz.project26_server.service.QandAService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Service
public class QandAServiceImpl implements QandAService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QandAService.class);

    @Autowired
    QuestionDAO questionDAO;

    @Override
    public void readQandA(Long questionId, QuestionDto questionDto) {
        String line = "";
        int randNrQuestion = questionDAO.randQuestionNr(questionId);
        LOGGER.info("id{} ", randNrQuestion);

        try{
            BufferedReader reader = new BufferedReader(new FileReader(Long.toString(questionId) + ".txt"));

            while ((line = reader.readLine()) != null){
                if(line.startsWith(Integer.toString(randNrQuestion))){
                    setVariables( questionId, line, questionDto);
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
    public void setVariables(Long questionId, String line, QuestionDto questionDto) {
        String [] parts = line.split(";");

        questionDto.setQuestion(parts[1]);
        questionDto.setAnswer1(parts[2]);
        questionDto.setAnswer2(parts[3]);
        questionDto.setAnswer3(parts[4]);
        questionDto.setAnswer4(parts[5]);
        questionDto.setCorrectAnswer(Integer.parseInt(parts[6]));
    }

    @Override
    public boolean isAnswerCorrect(String correctAnswerNr) {
        for(int i = 2; i < 6; i++){
            if(correctAnswerNr.equals(Integer.toString(i - 1))) {
                return true;
            }
        }
        return false;
    }
}
