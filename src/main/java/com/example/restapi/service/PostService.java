package com.example.restapi.service;

import com.example.restapi.dto.exception.UserNotExceptionResponse;
import com.example.restapi.dto.request.post.RequestCreatePostDto;
import com.example.restapi.dto.response.post.ResponseCreatePostDto;
import com.example.restapi.entity.Post.Post;
import com.example.restapi.entity.Post.PostRepository;
import com.example.restapi.entity.User.User;
import com.example.restapi.entity.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    // user가 존재하는지 확인하는 메소드
    @Transactional
    public User verify(Long id){
        return userRepository.findById(id).orElseThrow(()->
                new UserNotExceptionResponse("Not Found User"));
    }
}
