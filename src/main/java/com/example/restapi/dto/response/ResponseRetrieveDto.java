package com.example.restapi.dto.response;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class ResponseRetrieveDto extends RepresentationModel<ResponseRetrieveDto> {

    private final String email;
    private final String nickname;

    public ResponseRetrieveDto(String email, String nickname){
        this.email = email;
        this.nickname = nickname;
    }
}
