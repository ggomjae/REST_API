package com.example.restapi.controller;

import com.example.restapi.dto.request.RequestCreateDto;
import com.example.restapi.dto.response.ResponseCreateDto;
import com.example.restapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    ////////////////////////////////////////
    //               User                 //
    ////////////////////////////////////////

    // 유저를 저장하는 메소드
    @PostMapping("/users")
    public ResponseCreateDto createUser(@RequestBody RequestCreateDto requestCreateDto){

        return userService.save(requestCreateDto);
    }

    // 모든 유저 정보를 갖고 오는 메소드
    @GetMapping("/users")
    public String retrieveAllUsers(){
        return "retrieveAllUsers";
    }

    // 유저 정보를 갖고 오는 메소드
    @GetMapping("/users/{id}")
    public String retrieveUser(@PathVariable int id){
        return "retrieveUser";
    }

    // 유저의 정보를 업데이트 하는 메소드
    @PutMapping("/users/{id}/nickname")
    public String updateUserNickname(@PathVariable int id){
        return "updateUserNickname";
    }

    // 유저를 삭제하는 메소드
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id){
        return "deleteUser";
    }

    ////////////////////////////////////////
    //               Post                 //
    ////////////////////////////////////////

    // 유저의 게시물을 만드는 메소드
    @PostMapping("/users/{id}/posts")
    public String createPost(@PathVariable int id){
        return "createPost";
    }

    // 유저의 모든 게시물을 갖고오는 메소드
    @GetMapping("/users/{id}/posts")
    public String retrievePostsOfUser(@PathVariable int id){
        return "retrievePostsOfUser";
    }

    // 유저의 게시물을 변경하는 메소드
    @PutMapping("/users/{id}/posts/{post_id}/title")
    public String updatePostTitle(@PathVariable int id, @PathVariable int post_id){
        return "updatePostTitle";
    }

    // 유저의 게시물을 삭제하는 메소드
    @DeleteMapping("/users/{id}/posts/{post_id}")
    public String deletePost(@PathVariable int id, @PathVariable int post_id){
        return "deletePost";
    }
}
