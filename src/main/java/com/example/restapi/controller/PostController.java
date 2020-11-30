package com.example.restapi.controller;

import com.example.restapi.dto.response.post.ResponseRetrievePostDto;
import com.example.restapi.dto.response.user.ResponseRetrieveUserDto;
import com.example.restapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class PostController {

    private final PostService postService;

    // 모든 게시물을 갖고 오는 메소드 [ 로그인안하고 보기만 할 때 ]
    @GetMapping("/posts")
    public List<ResponseRetrievePostDto> retrieveAllPost(){

        List<ResponseRetrievePostDto> posts = postService.retrieveAllPost();

        for(ResponseRetrievePostDto responseRetrievePostDto : posts){
            WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(this.getClass()).retrieveAllPost());
            responseRetrievePostDto.add(linkTo.withRel("Non-Login-retrieve-all-post"));
        }

        return posts;
    }

    // 게시물을 갖고오는 메소드 [ 로그인안하고 클릭시 볼 때 ]
    @GetMapping("/posts/{post_id}")
    public String retrievePost() { return "retrievePost"; }
}
