package com.example.restapi.service;

import com.example.restapi.dto.exception.UserNotExceptionResponse;
import com.example.restapi.dto.request.user.RequestUpdateUserDto;
import com.example.restapi.dto.response.user.ResponseDeleteUserDto;
import com.example.restapi.dto.response.user.ResponseRetrieveUserDto;
import com.example.restapi.dto.response.user.ResponseUpdateUserDto;
import com.example.restapi.entity.User.User;
import com.example.restapi.entity.User.UserRepository;
import com.example.restapi.dto.request.user.RequestCreateUserDto;
import com.example.restapi.dto.response.user.ResponseCreateUserDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public ResponseCreateUserDto saveUser(RequestCreateUserDto requestCreateDto) {

        User user = userRepository.save(requestCreateDto.toEntiy());
        return new ResponseCreateUserDto(user.getUno(), user.getNickname());
    }

    @Transactional
    public ResponseRetrieveUserDto retrieveUser(Long user_id){

        // 만약 존재하지 않으면 Error
        User user = userRepository.findById(user_id).orElseThrow(()->
                new UserNotExceptionResponse("Not Found User"));

        return new ResponseRetrieveUserDto(user.getUno(),user.getEmail(), user.getNickname());
    }

    /*
        0개를 보낼 수 도있기 때문에 예외처리가 따로 없다.
     */
    @Transactional
    public List<ResponseRetrieveUserDto> retrieveAllUser(){

        return userRepository.findAll()
                .stream()
                .map( user -> new ResponseRetrieveUserDto(user.getUno(), user.getEmail(),user.getNickname()))
                .collect(Collectors.toList());
    }

    @Transactional
    public ResponseUpdateUserDto updateUserNickname(Long user_id, RequestUpdateUserDto requestUpdateUserDto){

        User user = userRepository.findById(user_id).orElseThrow(()->
                new UserNotExceptionResponse("Not Found User"));

        // 이미 예외처리를 통해 user를 걸러냈기 때문에 오류가 따로 없다.
        user.updateNickName(requestUpdateUserDto.getNickname());

        return new ResponseUpdateUserDto(user_id,user.getNickname());
    }

    @Transactional
    public ResponseDeleteUserDto deleteUser(Long user_id){

        User user = userRepository.findById(user_id).orElseThrow(()->
                new UserNotExceptionResponse("Not Found User"));
        userRepository.delete(user);

        // status : true를 넣어줌으로 삭제됨을 클라이언트에게 보냄
        return new ResponseDeleteUserDto(user_id,true);
    }
}
