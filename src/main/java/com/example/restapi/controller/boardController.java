package com.example.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class boardController {

    @GetMapping("/temp")
    public String tempMethod(){
        return "Temp";
    }
}
