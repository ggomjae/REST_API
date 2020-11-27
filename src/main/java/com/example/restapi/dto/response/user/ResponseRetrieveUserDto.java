package com.example.restapi.dto.response.user;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class ResponseRetrieveUserDto extends RepresentationModel<ResponseRetrieveUserDto> {

    private final String email;
    private final String nickname;

    public ResponseRetrieveUserDto(String email, String nickname){
        this.email = email;
        this.nickname = nickname;
    }
}
