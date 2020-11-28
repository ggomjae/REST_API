package com.example.restapi.dto.response.user;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class ResponseDeleteUserDto extends RepresentationModel<ResponseDeleteUserDto> {

    private final Long id;
    private final boolean status;

    public ResponseDeleteUserDto(Long id, boolean status){
        this.id = id;
        this.status = status;
    }
}
