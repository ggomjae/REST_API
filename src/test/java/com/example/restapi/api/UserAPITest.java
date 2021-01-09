package com.example.restapi.api;

import com.example.restapi.dto.request.user.RequestCreateUserDto;
import com.example.restapi.dto.response.reply.ResponseCreateReplyDto;
import com.example.restapi.dto.response.user.ResponseCreateUserDto;
import com.example.restapi.entity.User.User;
import com.example.restapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

/*
    given	  객체를 생성 할 때 사용
    when	  객체를 얻어 올 때 사용
    then	  객체를 비교 할 때 사용
    verify	  확인 작업을 할 때 사용
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserAPITest {

    @Mock
    UserService userService;

    @Test
    void test_creatUser(){
        // Given
        RequestCreateUserDto requestCreateUserDto = createUserDto();


        // Vertify

    }

    private RequestCreateUserDto createUserDto() {
        RequestCreateUserDto requestCreateUserDto = RequestCreateUserDto.builder()
                .email("ggomjae@naver.com")
                .nickname("ggomjae")
                .password("1234")
                .build();
        return requestCreateUserDto;
    }
}
