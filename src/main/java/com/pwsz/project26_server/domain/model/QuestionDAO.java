package com.pwsz.project26_server.domain.model;

import com.google.common.base.Strings;
import com.pwsz.project26_server.domain.dto.FileIsEmpty;
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
        int nrOfLines = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(Long.toString(questionId) + ".txt"));
            try {
                while (reader.readLine() != null) {
                    nrOfLines++;
                }
            }finally {
                reader.close();
            }
        }catch (FileNotFoundException ex){
            LOGGER.error("File not found!");
        }catch (IOException ex){
            LOGGER.error("I can not read this file!");
        }

        return nrOfLines;
    }

    @Override
    public int randQuestionNr(Long questionId) throws FileIsEmpty {
        int maxLineNr = howManyLines(questionId);
        Random rand = new Random();

        //if(maxLineNr <= 0) throw new FileIsEmpty("That file doesn't contain any question!");

        return rand.nextInt(maxLineNr - 1) + 1;
    }

    @Override
    public boolean isEmpty(QuestionDto questionDto){
        if(Strings.isNullOrEmpty(questionDto.getQuestion()))
            return true;
        else if(Strings.isNullOrEmpty(questionDto.getAnswer1()) || Strings.isNullOrEmpty(questionDto.getAnswer2()) || Strings.isNullOrEmpty(questionDto.getAnswer3()) || Strings.isNullOrEmpty(questionDto.getAnswer4()))
            return true;

        return false;
    }
}
