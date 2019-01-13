package com.pwsz.project26_server.rest;

import com.pwsz.project26_server.domain.dto.ErrorDto;
import com.pwsz.project26_server.domain.dto.QuestionDto;
import com.pwsz.project26_server.domain.dto.ResourceNotFound;
import com.pwsz.project26_server.service.QandAService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Field;
import java.nio.charset.Charset;


@RestController
@RequestMapping("/api")
public class MilionApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MilionApiController.class);

    @Autowired
    QandAService qandAService;

    public void setUTF(){
        System.setProperty("file.encoding","UTF-8");
        Field charset;
        try {
            charset = Charset.class.getDeclaredField("defaultCharset");
            charset.setAccessible(true);
            charset.set(null,null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/milion/question/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> sendQAndA(@PathVariable("id") Long id) throws ResourceNotFound {

        if(!(qandAService.isIdCorrect(id))) throw new ResourceNotFound();

        setUTF();

        QuestionDto questionDto = new QuestionDto();
        qandAService.readQandA(id, questionDto);

        LOGGER.info("Question id: {}", id);
        LOGGER.info("Question: {}", questionDto.getQuestion());
        LOGGER.info("Nr of correct answer: {}", questionDto.getCorrectAnswer());

        return qandAService.isEmpty(questionDto) ?
                new ResponseEntity<>(new ErrorDto("File doesn't contain correct/any content!"), HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(questionDto, HttpStatus.OK);
    }
}
