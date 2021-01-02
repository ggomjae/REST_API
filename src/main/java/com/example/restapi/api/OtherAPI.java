package com.example.restapi.api;

import com.example.restapi.service.OtherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class OtherAPI {

    private final OtherService otherService;

    @GetMapping("/account/googlesigncallback")
    public void GoogleSignCallback(@RequestParam String code) throws Exception {
        System.out.println("------------code-------------");
        System.out.println(code);

        otherService.googleSignCallback(code);

        return ;
    }
}
