package com.example.restapi.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    // 모든 유저 정보를 갖고 오는 메소드
    @GetMapping("/users")
    public String retrieveAllUsers(){
        return "retrieveAllUsers";
    }

    // 유저를 저장하는 메소드
    @PostMapping("/users")
    public String createUser(){
        return "createUser";
    }

    // 유저 정보를 갖고 오는 메소드
    @GetMapping("/users/{id}")
    public String retrieveUser(@PathVariable int id){
        return "retrieveUser";
    }

    // 유저를 삭제하는 메소드
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id){
        return "deleteUser";
    }

    // 유저의 모든 게시물을 갖고오는 메소드
    @GetMapping("/users/{id}/posts")
    public String retrievePostsOfUser(@PathVariable int id){
        return "retrievePostsOfUser";
    }

    // 유저의 게시물을 만드는 메소드
    @PostMapping("/users/{id}/posts")
    public String createPost(@PathVariable int id){
        return "createPost";
    }

    // 유저의 게시물을 삭제하는 메소드
    @PostMapping("/users/{id}/posts/{post_id}")
    public String deletePost(@PathVariable int id, @PathVariable int post_id){
        return "deletePost";
    }
}
