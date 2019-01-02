package com.pwsz.project26_server.rest;

import com.pwsz.project26_server.domain.dto.QuestionDto;
import com.pwsz.project26_server.service.QandAService;
import com.pwsz.project26_server.service.impl.QandAServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MilionApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MilionApiController.class);

    @Autowired
    QandAService qandAService;

    @RequestMapping(value = "/milion/question/{id}", method = RequestMethod.GET)
    public ResponseEntity<QuestionDto> sendQAndA(@PathVariable("id") Long id){
        QuestionDto questionDto = new QuestionDto();
        qandAService.readQandA(id, questionDto);

        LOGGER.info("Question: {}", questionDto.getQuestion());
        LOGGER.info("Correct Answer: {}", questionDto.getCorrectAnswer());

        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }



}
