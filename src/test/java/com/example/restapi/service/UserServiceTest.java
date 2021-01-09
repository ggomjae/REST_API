package com.example.restapi.service;

import com.example.restapi.entity.User.User;
import com.example.restapi.entity.User.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/*
    given	  객체를 생성 할 때 사용 - 설정
    when	  객체를 얻어 올 때 사용 - 조건 - 무조건 이 값을 리턴해야한다.
    then	  객체를 비교 할 때 사용
    verify	  확인 작업을 할 때 사용

    - 내용 참고
    http://wonwoo.ml/index.php/post/1453

    when vs given

    From my point of view these are just different styles.
    The first is the normal Mockito syntax and the second just tries to fit nicer into BDD style tests
    - I really like the second version because it reads so nicely in BDD tests.
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

        // 무조건 이값을 리턴
        when(userRepository.save(user)).thenReturn(user);

        User return_user = userRepository.save(user);

        // Then
        verify(userRepository).save(user);
        assertEquals("ggomjae@naver.com", return_user.getEmail());
        // assertEquals("ggomjae@zum.com", return_user.getEmail()); -- fail

        then(userRepository).should(times(1)).save(user);
        then(userRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void test_retrieveUser(){

        //Given
        User user = createUser();
        assertNotNull(user);

        //When
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> return_user = userRepository.findById(1L);

        //Then
        assertEquals(return_user, Optional.of(user));
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