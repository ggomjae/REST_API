package com.example.restapi.service;

import com.example.restapi.dto.request.mail.MailDto;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {

    //private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "개발자 주소";

    public void mailSend(MailDto mailDto) {

//        SimpleMailMessage message = new SimpleMailMessage();
//        /*
//            setTo() : 받는 사람 주소
//            setFrom() : 보내는 사람 주소
//            setSubject() : 제목
//            setText() : 메시지 내용
//         */
//        message.setTo(mailDto.getAddress());
//        message.setFrom(MailService.FROM_ADDRESS);
//        message.setSubject(mailDto.getTitle());
//        message.setText(mailDto.getContent());
//
//        /* 실제로 메일을 보내는 */
//        mailSender.send(message);
    }
}