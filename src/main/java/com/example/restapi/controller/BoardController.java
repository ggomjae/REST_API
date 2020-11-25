package com.example.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

    @GetMapping("/temp")
    public String tempMethod(){
        return "Temp";
    }
}
