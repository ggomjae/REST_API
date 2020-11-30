package com.example.restapi.dto.response.reply;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class ResponseDeleteReplyDto extends RepresentationModel<ResponseDeleteReplyDto> {

    private final Long rno;
    private final Boolean status;

    public ResponseDeleteReplyDto(Long rno, Boolean status){
        this.rno = rno;
        this.status = status;
    }

}
