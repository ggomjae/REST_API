package com.example.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    @GetMapping("/post-reply")
    public void retrieveAllReply(){

    }
}
