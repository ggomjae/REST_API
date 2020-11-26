package com.example.restapi.dto.response;

import lombok.Getter;

@Getter
public class ResponseCreateDto {

    private final String nickname;

    public ResponseCreateDto(String nickname){
        this.nickname = nickname;
    }
}
