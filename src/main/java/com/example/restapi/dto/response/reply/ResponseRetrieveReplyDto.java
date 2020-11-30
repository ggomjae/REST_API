package com.example.restapi.dto.response.reply;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class ResponseRetrieveReplyDto extends RepresentationModel<ResponseRetrieveReplyDto> {

    private final Long pno;
    private final Long uno;
    private final String content;

    public ResponseRetrieveReplyDto(Long pno, Long uno, String content){
        this.pno = pno;
        this.uno = uno;
        this.content = content;
    }
}
