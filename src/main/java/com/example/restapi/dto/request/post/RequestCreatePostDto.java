package com.example.restapi.dto.request.post;

import com.example.restapi.entity.Post.Post;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@ApiModel(description = "게시물을 등록하는 정보")
public class RequestCreatePostDto {

    @ApiModelProperty(notes = "게시물의 제목을 입력하세요.")
    private String title;

    @ApiModelProperty(notes = "게시물의 내용을 입력하세요.")
    private String content;

    private String description;

    private boolean postStatus;

    @Builder
    public RequestCreatePostDto(String title, String content, String description, boolean postStatus){
        this.title = title;
        this.content = content;
        this.description = description;
        this.postStatus = postStatus;
    }

    public Post toPost(Long id){
        return Post.builder()
                .title(this.title)
                .id(id)
                .content(this.content)
                .description(this.description)
                .postStatus(this.postStatus)
                .build();
    }
}
