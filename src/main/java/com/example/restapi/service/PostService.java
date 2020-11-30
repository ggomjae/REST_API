package com.example.restapi.service;

import com.example.restapi.dto.exception.UserNotExceptionResponse;
import com.example.restapi.dto.request.post.RequestCreatePostDto;
import com.example.restapi.dto.response.post.ResponseCreatePostDto;
import com.example.restapi.dto.response.post.ResponseRetrievePostDto;
import com.example.restapi.entity.Post.Post;
import com.example.restapi.entity.Post.PostRepository;
import com.example.restapi.entity.User.User;
import com.example.restapi.entity.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseCreatePostDto savePost(Long id, RequestCreatePostDto requestCreatePostDto){

        User user = verify(id);
        Post post = postRepository.save(requestCreatePostDto.toPost(user.getId()));
        return new ResponseCreatePostDto(post.getPno(),post.getTitle());
    }

    /*

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
     */

    @Transactional
    public List<ResponseRetrievePostDto> retrievePosts(Long id){
        User user = verify(id);
        return postRepository.findAllDesc(user.getId())
                .stream()
                .map( post -> new ResponseRetrievePostDto(post.getId(),post.getPno()))
                .collect(Collectors.toList());
    }

    // user가 존재하는지 확인하는 메소드
    @Transactional
    public User verify(Long id){
        return userRepository.findById(id).orElseThrow(()->
                new UserNotExceptionResponse("Not Found User"));
    }
}
