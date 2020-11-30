package com.example.restapi.dto.response.post;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class ResponseDeletePostDto extends RepresentationModel<ResponseDeletePostDto> {

    private final Long pno;
    private final boolean status;

    public ResponseDeletePostDto(Long pno, Boolean status){
        this.pno = pno;
        this.status = status;
    }
}
