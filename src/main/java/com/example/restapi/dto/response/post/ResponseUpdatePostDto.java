package com.example.restapi.dto.response.post;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class ResponseUpdatePostDto extends RepresentationModel<ResponseUpdatePostDto> {

    private final Long pno;
    private final Long uno;
    private final String title;

    public ResponseUpdatePostDto(Long pno,Long uno, String title){
        this.pno = pno;
        this.uno = uno;
        this.title =title;
    }
}
