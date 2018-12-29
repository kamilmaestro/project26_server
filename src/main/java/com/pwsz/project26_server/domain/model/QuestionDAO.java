package com.pwsz.project26_server.domain.model;

import com.pwsz.project26_server.domain.dto.AnswerDto;
import com.pwsz.project26_server.domain.dto.QuestionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

@Repository
public class QuestionDAO implements Question{

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionDAO.class);

    @Override
    public int howManyLines(Long questionId){
        String line;
        int nrOfLines = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(Long.toString(questionId) + ".txt"));
            line = reader.readLine();
            while(line != null){
                nrOfLines++;
            }
            reader.close();
        }catch (FileNotFoundException ex){
            LOGGER.error("File not found!");
        }catch (IOException ex){
            LOGGER.error("I can not read this file!");
        }

        return nrOfLines;
    }

    @Override
    public int randQuestionNr(Long questionId) {
        int maxLineNr = howManyLines(questionId);
        Random rand = new Random();

        return rand.nextInt(maxLineNr) + 1;
    }

    @Override
    public String readQuestion(Long questionId, QuestionDto questionDto) {
        String line = "";

        try{
            BufferedReader reader = new BufferedReader(new FileReader(Long.toString(questionId) + ".txt"));
            line = reader.readLine();
            while (line != null){
                if(line.startsWith(questionId.toString())){
                   setVariables(line, questionDto);
                }
            }
            reader.close();
        }catch (FileNotFoundException ex){
            LOGGER.error("File not found!");
        }catch (IOException ex){
            LOGGER.error("I can not read this file!");
        }

        return "";
    }

    @Override
    public void setVariables(String line, QuestionDto questionDto){
        String [] parts = line.split(";");
        questionDto.setQuestion(parts[1]);
        AnswerDto[] answersDto = new AnswerDto[4];

        for(int i = 2; i < 6; i++){
            if(parts[6] == Integer.toString(i - 1)) {
                answersDto[i] = new AnswerDto(line, true);
            }else {
                answersDto[i] = new AnswerDto(line, false);
            }

            //questionDto.setAnswers(answersDto);
        }
        questionDto.setQuestion(parts[1]);
    }

}
