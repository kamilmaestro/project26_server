package com.pwsz.project26_server.service.impl;

import com.google.common.base.Strings;
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
        String line;

        try{
            int randNrQuestion = questionDAO.randQuestionNr(questionId);

            BufferedReader reader = new BufferedReader(new FileReader(Long.toString(questionId) + ".txt"));
            try {
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith(Integer.toString(randNrQuestion))) {
                        setVariables(questionId, line, questionDto);
                    }
                }
            }finally {
                reader.close();
            }
        }catch (FileNotFoundException ex){
            LOGGER.error("File not found!");
        }catch (IOException ex){
            LOGGER.error("I can not read this file!");
        } catch (Exception ex) {
            LOGGER.error("File is empty!");
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
    public boolean isEmpty(QuestionDto questionDto){
        if(Strings.isNullOrEmpty(questionDto.getQuestion()))
            return true;
        else if(Strings.isNullOrEmpty(questionDto.getAnswer1()) || Strings.isNullOrEmpty(questionDto.getAnswer2()) || Strings.isNullOrEmpty(questionDto.getAnswer3()) || Strings.isNullOrEmpty(questionDto.getAnswer4()))
            return true;
        else if(questionDto.getCorrectAnswer() <= 0)
            return true;

        return false;
    }

    @Override
    public boolean isIdCorrect(Long id){
        return (id >= 1 && id <= 12);
    }
}
