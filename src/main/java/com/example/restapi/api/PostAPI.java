package com.example.restapi.api;

import com.example.restapi.dto.request.reply.RequestCreateReplyDto;
import com.example.restapi.dto.response.post.ResponseRetrievePostDto;

import com.example.restapi.dto.response.reply.ResponseCreateReplyDto;
import com.example.restapi.dto.response.reply.ResponseDeleteReplyDto;
import com.example.restapi.service.PostService;
import com.example.restapi.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class PostAPI {

    private final PostService postService;
    private final ReplyService replyService;

    ////////////////////////////////////////
    //               Post                 //
    ////////////////////////////////////////

    // 모든 게시물을 갖고 오는 메소드
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

    // 게시물을 갖고오는 메소드
    @GetMapping("/posts/{post_id}")
    public ResponseRetrievePostDto retrievePost(@PathVariable Long post_id) {

        ResponseRetrievePostDto responseRetrievePostDto = postService.retrievePost(post_id);

        // HateOAS
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrievePost(post_id));

        responseRetrievePostDto.add(linkTo.withRel("retrieve-post"));

        return responseRetrievePostDto;
    }

    ////////////////////////////////////////
    //               Reply                //
    ////////////////////////////////////////

    @PostMapping("/posts/{post_id}/replys")
    public ResponseEntity<ResponseCreateReplyDto> createReply(@RequestBody RequestCreateReplyDto requestCreateReplyDto, @PathVariable Long post_id){

        ResponseCreateReplyDto responseCreateReplyDto = replyService.saveReply(post_id,requestCreateReplyDto);

        Map<String,Long> parameterContainer = new HashMap<>();
        parameterContainer.put("post_id",post_id);
        parameterContainer.put("reply_id",responseCreateReplyDto.getRno());

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/posts/{post_id}/replys/{reply_id}")
                .buildAndExpand(parameterContainer)
                .toUri();

        return ResponseEntity.created(location).body(responseCreateReplyDto);
    }

    @DeleteMapping("/posts/{post_id}/replys/{reply_id}")
    public ResponseDeleteReplyDto deleteReply(@PathVariable Long post_id, @PathVariable Long reply_id){

        ResponseDeleteReplyDto responseDeleteReplyDto = replyService.deleteReply(post_id,reply_id);

        // HateOAS
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteReply(post_id,reply_id));

        responseDeleteReplyDto.add(linkTo.withRel("delete-reply"));

        return responseDeleteReplyDto;
    }
}