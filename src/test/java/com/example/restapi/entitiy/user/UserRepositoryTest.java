package com.example.restapi.entitiy.user;

import com.example.restapi.entity.User.User;
import com.example.restapi.entity.User.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest // H2 데이터베이스를 자동으로 실행
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void createUserTest(){

        String email = "ggomjae@naver.com";

        userRepository.save(User.builder()
            .email(email)
            .password("123")
            .nickname("ggomjae")
            .build());

        Optional<User> user = userRepository.findByEmail("ggomjae@naver.com");

        user.ifPresent(selectUser->{
            System.out.println("user : " + selectUser);
            System.out.println("user : " + selectUser.getEmail());
            assertEquals(email,selectUser.getEmail());
        });
    }
}
