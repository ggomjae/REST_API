package com.example.restapi.dto.response.post;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class ResponseRetrievePostDto extends RepresentationModel<ResponseRetrievePostDto> {

    private final Long uno;
    private final Long pno;
    private final String content;

    public ResponseRetrievePostDto(Long uno, Long pno,String content){
        this.uno = uno;
        this.pno = pno;
        this.content = content;
    }
}
