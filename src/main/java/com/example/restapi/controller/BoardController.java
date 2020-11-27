package com.example.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class BoardController {

    // 모든 게시물을 갖고 오는 메소드 [ 로그인안하고 보기만 할 때 ]
    @GetMapping("/posts")
    public String retrieveAllPost(){
        return "retrieveAllPost";
    }
}
