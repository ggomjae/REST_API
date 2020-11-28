package com.example.restapi.controller;

import com.example.restapi.dto.request.user.RequestCreateUserDto;
import com.example.restapi.dto.response.user.ResponseCreateUserDto;
import com.example.restapi.dto.response.user.ResponseRetrieveUserDto;

import com.example.restapi.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class UserController {

    private final UserService userService;

    ////////////////////////////////////////
    //               User                 //
    ////////////////////////////////////////

    // 유저를 저장하는 메소드
    @PostMapping("/users")
    public ResponseEntity<ResponseCreateUserDto> createUser(@Valid @RequestBody RequestCreateUserDto requestCreateDto){

        ResponseCreateUserDto responseCreateDto = userService.save(requestCreateDto);
        /*
            현재 URI를 얻기 위해 ServletUriComponentsBuilder를 쓴다.
            201 Created 응답일 때 어느 페이지로 이동할지를 알려주는 헤더.
         */
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseCreateDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(responseCreateDto);
    }

    // 모든 유저 정보를 갖고 오는 메소드
    @GetMapping("/users")
    public List<ResponseRetrieveUserDto> retrieveAllUsers(){

        List<ResponseRetrieveUserDto> alluser = userService.retrieveAllUser();

        for(ResponseRetrieveUserDto responseRetrieveUserDto : alluser){
            WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(this.getClass()).retrieveUser(responseRetrieveUserDto.getId()));
            responseRetrieveUserDto.add(linkTo.withRel("retrieve-user"));
        }

        return alluser;
    }

    // 유저 정보를 갖고 오는 메소드
    @GetMapping("/users/{id}")
    public ResponseRetrieveUserDto retrieveUser(@PathVariable Long id){

        /*
            WebMvcLinkBuilder selfLinkBuilder = linkTo(EventController.class).slash(newEvent.getId());
            URI createdUri = selfLinkBuilder.toUri();
            EventResource eventResource = new EventResource(event);
            eventResource.add(linkTo(EventController.class).withRel("query-events"));
            eventResource.add(selfLinkBuilder.withRel("update-event"));
            eventResource.add(new Link("/docs/index.html#resources-events-create").withRel("profile"));
            return ResponseEntity.created(createdUri).body(eventResource);
         */

        // HateOAS
        ResponseRetrieveUserDto responseRetrieveDto = userService.retrieve(id);
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveUser(id));

        // responseRetrieveDto.add(Link.of(String.valueOf(linkTo))); // #1
        responseRetrieveDto.add(linkTo.withRel("retrieve-user")); // #2

        return responseRetrieveDto;
    }

    // 유저의 정보를 업데이트 하는 메소드
    @PatchMapping("/users/{id}/nickname")
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
    @PatchMapping("/users/{id}/posts/{post_id}/title")
    public String updatePostTitle(@PathVariable int id, @PathVariable int post_id){
        return "updatePostTitle";
    }

    // 유저의 게시물을 삭제하는 메소드
    @DeleteMapping("/users/{id}/posts/{post_id}")
    public String deletePost(@PathVariable int id, @PathVariable int post_id){
        return "deletePost";
    }

    ////////////////////////////////////////
    //               Reply                //
    ////////////////////////////////////////

    // 유저의 댓글을 모두 갖고 오는 메소드
    @GetMapping("/users/{id}/replys")
    public String retrieveReplysOfUser(@PathVariable Long id){
        return "retrieveReplysOfUser";
    }
}
