package com.example.restapi.dto.response.post;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class ResponseCreatePostDto extends RepresentationModel<ResponseCreatePostDto> {

    private final Long pno;
    private final String title;

    public ResponseCreatePostDto(Long pno, String title){
        this.pno = pno;
        this.title = title;
    }
}
