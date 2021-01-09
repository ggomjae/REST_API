package com.example.restapi.api;

import com.example.restapi.dto.request.mail.MailDto;
import com.example.restapi.service.MailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MailController {

    //private final MailService mailService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/v1/api/mail")
    public void execMail(@RequestBody MailDto mailDto) {
        logger.info("mail test");
        //mailService.mailSend(mailDto);
    }
}