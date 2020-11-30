package com.example.restapi.dto.request.post;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@ApiModel(description = "게시물 속성 업데이트 정보")
public class RequestUpdatePostDto {

    private String title;

    @Builder
    public RequestUpdatePostDto(String title){
        this.title = title;
    }
}
