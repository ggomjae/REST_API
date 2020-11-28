package com.example.restapi.dto.response.user;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class ResponseCreateUserDto extends RepresentationModel<ResponseCreateUserDto> {

    private final Long id;
    private final String nickname;

    public ResponseCreateUserDto(Long id, String nickname){
        this.id = id;
        this.nickname = nickname;
    }
}
