package com.example.restapi.dto.request;

import com.example.restapi.entity.User.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestCreateDto {

     private String password;
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
