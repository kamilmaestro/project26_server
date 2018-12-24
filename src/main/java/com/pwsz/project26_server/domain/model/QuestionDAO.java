package com.pwsz.project26_server.domain.model;

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
    public String readQuestion(Long questionId) {
        String line = "";

        try{
            BufferedReader reader = new BufferedReader(new FileReader(Long.toString(questionId) + ".txt"));
            line = reader.readLine();
            while (line != null){
                line.replaceFirst(Long.toString(questionId) + ";", "");


            }

            reader.close();
        }catch (FileNotFoundException ex){
            LOGGER.error("File not found!");
        }catch (IOException ex){
            LOGGER.error("I can not read this file!");
        }



        return "";
    }
}
