package com.example.restapi.dto.response.post;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class ResponseUpdatePostDto extends RepresentationModel<ResponseUpdatePostDto> {

    private final Long pno;
    private final Long id;
    private final String title;

    public ResponseUpdatePostDto(Long pno,Long id, String title){
        this.pno = pno;
        this.id = id;
        this.title =title;
    }
}
