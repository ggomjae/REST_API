package com.example.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

    // 모든 게시물을 갖고 오는 메소드 [ 로그인안하고 보기만 할 때 ]
    @GetMapping("/posts")
    public String retrievePosts(){
        return "retrievePosts";
    }
}
