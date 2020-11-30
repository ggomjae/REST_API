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
    public ResponseCreatePostDto retrievePost(Long id, RequestCreatePostDto requestCreatePostDto){

        /*
            Post를 등록하기 전에 해당하는 아이디가 있는지 확인한 후에 게시물 저장.
         */
        User user = userRepository.findById(id).orElseThrow(()->
                new UserNotExceptionResponse("Not Found User"));

        Post post = postRepository.save(requestCreatePostDto.toPost(user.getId()));
        return new ResponseCreatePostDto(post.getPno(),post.getTitle());
    }
}
