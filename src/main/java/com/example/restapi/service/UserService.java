package com.example.restapi.service;

import com.example.restapi.dto.exception.UserNotExceptionResponse;
import com.example.restapi.dto.response.user.ResponseRetrieveUserDto;
import com.example.restapi.entity.User.User;
import com.example.restapi.entity.User.UserRepository;
import com.example.restapi.dto.request.user.RequestCreateUserDto;
import com.example.restapi.dto.response.user.ResponseCreateUserDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public ResponseCreateUserDto save(RequestCreateUserDto requestCreateDto) {

        User user = userRepository.save(requestCreateDto.toEntiy());
        return new ResponseCreateUserDto(user.getId(), user.getNickname());
    }

    @Transactional
    public ResponseRetrieveUserDto retrieve(Long id){

        User user = userRepository.findById(id).orElseThrow(()->
                new UserNotExceptionResponse("Not Found User"));

        return new ResponseRetrieveUserDto(user.getEmail(), user.getNickname());
    }
}
