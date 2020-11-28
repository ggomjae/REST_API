package com.example.restapi.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@ApiModel(description = "사용자 속성 업데이트 정보")
public class RequestUpdateUserDto {

    @Size(min = 3, message = "3글자 이상 입력하세요.")
    @ApiModelProperty(notes = "사용자 닉네임을 입력하세요.")
    private String nickname;

    @Builder
    public RequestUpdateUserDto(String nickname){
        this.nickname = nickname;
    }
}
