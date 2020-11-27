package com.example.restapi.dto.request;

import com.example.restapi.entity.User.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@ApiModel(description = "회원 가입을 위한 정보")
public class RequestCreateDto {

     private String password;

     @Size(min = 3, message = "3글자 이상 입력하세요.")
     @ApiModelProperty(notes = "사용자 이메일을 입력하세요.")
     private String email;

     private String nickname;

     @Builder
     public RequestCreateDto(String password, String email, String nickname){
         this.password = password;
         this.email = email;
         this.nickname = nickname;
     }

    public User toEntiy(){
        return User.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }
}
