package com.example.restapi.dto.request.user;

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
public class RequestCreateUserDto {

     private String password;

     @Size(min = 3, message = "3글자 이상 입력하세요.")
     @ApiModelProperty(notes = "사용자 이메일을 입력하세요.")
     private String email;

     @ApiModelProperty(notes = "사용자 닉네임을 입력하세요.")
     private String nickname;

     @Builder
     public RequestCreateUserDto(String password, String email, String nickname){
         this.password = password;
         this.email = email;
         this.nickname = nickname;
     }

    public User toEntiy(String encodePassword){
        return User.builder()
                .email(this.email)
                .password(encodePassword)
                .nickname(this.nickname)
                .build();
    }
}
