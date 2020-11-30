package com.example.restapi.dto.request.reply;

import com.example.restapi.entity.Post.Post;
import com.example.restapi.entity.Reply.Reply;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@ApiModel(description = "댓글을 위한 정보")
public class RequestCreateReplyDto {

    // rno, pno , uno, content
    @ApiModelProperty(notes = "댓글의 내용을 입력하세요.")
    private String content;

    @Builder
    public RequestCreateReplyDto(String content){
        this.content = content;
    }

    public Reply toReply(Long user_id, Long post_id){
        return Reply.builder()
                .uno(user_id)
                .pno(post_id)
                .content(this.content)
                .build();
    }
}
