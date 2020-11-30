package com.example.restapi.dto.response.post;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class ResponseRetrievePostDto extends RepresentationModel<ResponseRetrievePostDto> {

    private final Long id;
    private final Long pno;

    public ResponseRetrievePostDto(Long id, Long pno){
        this.id = id;
        this.pno = pno;
    }
}
