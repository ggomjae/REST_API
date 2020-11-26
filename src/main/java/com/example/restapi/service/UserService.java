package com.example.restapi.service;

import com.example.restapi.Entity.User.User;
import com.example.restapi.Entity.User.UserRepository;
import com.example.restapi.dto.request.RequestCreateDto;
import com.example.restapi.dto.response.ResponseCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public ResponseCreateDto save(RequestCreateDto requestCreateDto) {
        User user = userRepository.save(requestCreateDto.toEntiy());
        return new ResponseCreateDto(user.getNickname());
    }
}
