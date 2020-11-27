package com.example.restapi.dto.response.user;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class ResponseRetrieveUserDto extends RepresentationModel<ResponseRetrieveUserDto> {

    private final Long id;
    private final String email;
    private final String nickname;

    public ResponseRetrieveUserDto(Long id, String email, String nickname){
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }
}
