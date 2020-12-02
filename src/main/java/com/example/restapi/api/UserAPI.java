package com.example.restapi.api;

import com.example.restapi.dto.request.post.RequestCreatePostDto;
import com.example.restapi.dto.request.post.RequestUpdatePostDto;
import com.example.restapi.dto.request.user.RequestCreateUserDto;
import com.example.restapi.dto.request.user.RequestUpdateUserDto;
import com.example.restapi.dto.response.post.ResponseCreatePostDto;
import com.example.restapi.dto.response.post.ResponseDeletePostDto;
import com.example.restapi.dto.response.post.ResponseRetrievePostDto;
import com.example.restapi.dto.response.post.ResponseUpdatePostDto;
import com.example.restapi.dto.response.reply.ResponseRetrieveReplyDto;
import com.example.restapi.dto.response.user.ResponseCreateUserDto;
import com.example.restapi.dto.response.user.ResponseDeleteUserDto;
import com.example.restapi.dto.response.user.ResponseRetrieveUserDto;
import com.example.restapi.dto.response.user.ResponseUpdateUserDto;

import com.example.restapi.service.PostService;
import com.example.restapi.service.ReplyService;
import com.example.restapi.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class UserAPI {

    private final UserService userService;
    private final PostService postService;
    private final ReplyService replyService;

    ////////////////////////////////////////
    //               User                 //
    ////////////////////////////////////////

    // 유저를 저장하는 메소드
    @PostMapping("/users")
    public ResponseEntity<ResponseCreateUserDto> createUser(@Valid @RequestBody RequestCreateUserDto requestCreateDto){

        ResponseCreateUserDto responseCreateDto = userService.saveUser(requestCreateDto);
        /*
            현재 URI를 얻기 위해 ServletUriComponentsBuilder를 쓴다.
            201 Created 응답일 때 어느 페이지로 이동할지를 알려주는 헤더.
         */
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseCreateDto.getId())
                .toUri();

        /*  Login 구현 후 Login Link 달아주면된다.
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(this.getClass()).retrieveUser(responseCreateDto.getId()));
        responseCreateDto.add(linkTo.withRel("retrieve-user")); // 각 DTO에 각자의 상세 URL 반환
        */
        return ResponseEntity.created(location).body(responseCreateDto);
    }

    // 모든 유저 정보를 갖고 오는 메소드
    @GetMapping("/users")
    public List<ResponseRetrieveUserDto> retrieveAllUsers(){

        List<ResponseRetrieveUserDto> users = userService.retrieveAllUser();
        
        // List안에 있는 모든 DTO에 상세 Link 걸어주는 반복문
        for(ResponseRetrieveUserDto responseRetrieveUserDto : users){
            WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(this.getClass()).retrieveUser(responseRetrieveUserDto.getId()));
            responseRetrieveUserDto.add(linkTo.withRel("retrieve-user")); // 각 DTO에 각자의 상세 URL 반환
        }

        return users;
    }

    // 유저 정보를 갖고 오는 메소드
    @GetMapping("/users/{user_id}")
    public ResponseRetrieveUserDto retrieveUser(@PathVariable Long user_id){

        ResponseRetrieveUserDto responseRetrieveDto = userService.retrieveUser(user_id);

        /*
            HateOAS : 유저정보를 갖고오는 메소드 -> Link : Update, Delete
            responseRetrieveDto.add(Link.of(String.valueOf(linkTo))); // #1
         */

        // Update Link
        WebMvcLinkBuilder UpdateLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).updateUserNickname(user_id,new RequestUpdateUserDto("title")));
        responseRetrieveDto.add(UpdateLink.withRel("update-user")); // #2

        // Delete Link
        WebMvcLinkBuilder DeleteLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteUser(user_id));
        responseRetrieveDto.add(UpdateLink.withRel("delete-user"));

        return responseRetrieveDto;
    }

    // 유저의 정보를 업데이트 하는 메소드
    @PatchMapping("/users/{user_id}/nickname")
    public ResponseUpdateUserDto updateUserNickname(@PathVariable Long user_id, @Valid @RequestBody RequestUpdateUserDto requestUpdateUserDto){

        ResponseUpdateUserDto responseUpdateUserDto = userService.updateUserNickname(user_id,requestUpdateUserDto);

        // HateOAS : Update 한 후에 사용자 정보 갖고 오는 Link
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveUser(user_id));

        responseUpdateUserDto.add(linkTo.withRel("retrieve-user"));

        return responseUpdateUserDto;
    }

    // 유저를 삭제하는 메소드
    @DeleteMapping("/users/{user_id}")
    public ResponseDeleteUserDto deleteUser(@PathVariable Long user_id){

        ResponseDeleteUserDto responseDeleteUserDto = userService.deleteUser(user_id);

        // HateOAS : -> 추후에 Login 구현 후 Link 다시 구현
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

        responseDeleteUserDto.add(linkTo.withRel("retrieve-all-user"));

        return responseDeleteUserDto;
    }

    ////////////////////////////////////////
    //               Post                 //
    ////////////////////////////////////////

    // 유저의 게시물을 만드는 메소드
    @PostMapping("/users/{user_id}/posts")
    public ResponseEntity<ResponseCreatePostDto> createPost(@RequestBody RequestCreatePostDto requestCreatePostDto, @PathVariable Long user_id){

        ResponseCreatePostDto responseCreatePostDto = postService.savePost(user_id,requestCreatePostDto);

            Map<String,Long> parameterContainer = new HashMap<>();
            parameterContainer.put("user_id",user_id);
            parameterContainer.put("post_id",responseCreatePostDto.getPno());

            // User Post와 다르게 fromCurrentcontextPath를 썼음. path 때문에
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/users/{user_id}/posts/{post_id}")
                .buildAndExpand(parameterContainer)
                .toUri();

            /*
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setLocation(uriOfNewResource);
                return new ResponseEntity<>(customer, httpHeaders, HttpStatus.CREATED);
             */

        return ResponseEntity.created(location).body(responseCreatePostDto);
    }

    // 유저의 모든 게시물을 갖고오는 메소드
    @GetMapping("/users/{user_id}/posts")
    public List<ResponseRetrievePostDto> retrievePostsOfUser(@PathVariable Long user_id){

        List<ResponseRetrievePostDto> posts = postService.retrievePosts(user_id);

        // HateOAS : 게시물을 다 갖고온 후 갖고온 게시물의 각 조회하는 Link
        for(ResponseRetrievePostDto responseRetrievePostDto : posts){
            WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(PostAPI.class).retrievePost(responseRetrievePostDto.getPno()));
            responseRetrievePostDto.add(linkTo.withRel("retrieve-user-post"));
        }
        return posts;
    }

    // 유저의 게시물을 변경하는 메소드
    @PatchMapping("/users/{user_id}/posts/{post_id}/title")
    public ResponseUpdatePostDto updatePostTitle(@RequestBody RequestUpdatePostDto requestUpdatePostDto, @PathVariable long user_id, @PathVariable long post_id){

        ResponseUpdatePostDto responseUpdatePostDto = postService.updatePostTitle(requestUpdatePostDto,user_id,post_id);

        // HateOAS : Update 한 후에 게시물 정보 갖고 오는 Link
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(PostAPI.class).retrievePost(post_id));

        responseUpdatePostDto.add(linkTo.withRel("retrieve-post"));
        return responseUpdatePostDto;
    }

    // 유저의 게시물을 삭제하는 메소드
    @DeleteMapping("/users/{user_id}/posts/{post_id}")
    public ResponseDeletePostDto deletePost(@PathVariable Long user_id, @PathVariable Long post_id){

        ResponseDeletePostDto responseDeletePostDto = postService.deletePost(user_id,post_id);

        // HateOAS
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(PostAPI.class).retrieveAllPost());

        responseDeletePostDto.add(linkTo.withRel("retrieve-all-post"));

        return responseDeletePostDto;
    }

    ////////////////////////////////////////
    //               Reply                //
    ////////////////////////////////////////

    // 유저의 댓글을 모두 갖고 오는 메소드
    @GetMapping("/users/{user_id}/replys")
    public List<ResponseRetrieveReplyDto> retrieveReplysOfUser(@PathVariable Long user_id){

        List<ResponseRetrieveReplyDto> replys = replyService.retrieveAll(user_id);

        // 보통 모든 댓글을 다보고 그 다음 상태는 User의 상태니
        for(ResponseRetrieveReplyDto responseRetrieveReplyDto : replys){
            WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(this.getClass()).retrieveUser(user_id));
            responseRetrieveReplyDto.add(linkTo.withRel("retrieve-user"));
        }

        return replys;
    }
}
