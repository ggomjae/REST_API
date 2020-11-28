package com.example.restapi.dto.response.user;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class ResponseUpdateUserDto extends RepresentationModel<ResponseUpdateUserDto> {

    private final Long id;
    private final String nickname;

    public ResponseUpdateUserDto(Long id, String nickname){
        this.id = id;
        this.nickname = nickname;
    }
}
