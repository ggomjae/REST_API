package com.example.restapi.dto.response.reply;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class ResponseCreateReplyDto extends RepresentationModel<ResponseCreateReplyDto> {

    private final Long rno;
    private final Long pno;
    private final Long uno;

    public ResponseCreateReplyDto(Long rno,Long pno, Long uno){
        this.rno = rno;
        this.pno = pno;
        this.uno = uno;
    }
}
