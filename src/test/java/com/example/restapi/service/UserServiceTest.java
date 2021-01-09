package com.example.restapi.service;

import com.example.restapi.dto.exception.UserNotExceptionResponse;
import com.example.restapi.entity.User.User;
import com.example.restapi.entity.User.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/*
    BDD : 애플리케이션이 어떻게 '행동' 하는지
    Given - 주어진 상황
    When  - 뭔가를 하면
    Then  - 결과는 이럼
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Test
    void test_saveUser(){

        // Given
        User user = createUser();
        assertNotNull(user);

        // When
        userRepository.save(user);

        // Then
        verify(userRepository).save(user);
        then(userRepository).should(times(1)).save(user);
        then(userRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void test_exist_retrieveUser(){

        //Given
        User user = createUser();
        assertNotNull(user);
        userRepository.save(user);

        //When
        given(userRepository.findById(1L)).willReturn(Optional.of(user));

        //Then
        assertEquals("ggomjae@naver.com",user.getEmail());
    }

    @Test
    void test_notFound_retrieveUser(){
        //Given
        User user = createUser();
        assertNotNull(user);

        //When
        given(userRepository.findById(1L)).willThrow(new UserNotExceptionResponse("Not found"));

        //Then
        assertEquals("ggomjae@naver.com",user.getEmail());
    }

    private User createUser() {
        User user = User.builder()
                .email("ggomjae@naver.com")
                .nickname("ggomjae")
                .password("1234")
                .build();
        return user;
    }

}