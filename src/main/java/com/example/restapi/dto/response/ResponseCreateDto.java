package com.example.restapi.dto.response;

import lombok.Getter;

@Getter
public class ResponseCreateDto {

    private final Long id;
    private final String nickname;

    public ResponseCreateDto(Long id, String nickname){
        this.id = id;
        this.nickname = nickname;
    }
}
