package com.example.restapi.dto.response;

import lombok.Getter;

@Getter
public class ResponseRetrieveDto {

    private final String email;
    private final String nickname;

    public ResponseRetrieveDto(String email, String nickname){
        this.email = email;
        this.nickname = nickname;
    }
}
