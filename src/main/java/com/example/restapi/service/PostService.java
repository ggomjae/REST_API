package com.example.restapi.service;

import com.example.restapi.dto.exception.PostNotExceptionResponse;
import com.example.restapi.dto.exception.UserNotExceptionResponse;
import com.example.restapi.dto.request.post.RequestCreatePostDto;
import com.example.restapi.dto.request.post.RequestUpdatePostDto;
import com.example.restapi.dto.response.post.ResponseCreatePostDto;
import com.example.restapi.dto.response.post.ResponseDeletePostDto;
import com.example.restapi.dto.response.post.ResponseRetrievePostDto;
import com.example.restapi.dto.response.post.ResponseUpdatePostDto;
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

    @Transactional
    public List<ResponseRetrievePostDto> retrievePosts(Long id){
        User user = verify(id);
        return postRepository.findAllDesc(user.getId())
                .stream()
                .map( post -> new ResponseRetrievePostDto(post.getId(),post.getPno()))
                .collect(Collectors.toList());
    }

    @Transactional
    public ResponseUpdatePostDto updatePostTitle(RequestUpdatePostDto requestUpdatePostDto, Long id, Long post_id){
        // User가 있는지 확인
        User user = verify(id);

        // Post가 있는지 확인
        Post post = postRepository.findById(post_id).orElseThrow(()->
            new PostNotExceptionResponse("Not Found Post"));

        post.updateTitle(requestUpdatePostDto.getTitle());

        return new ResponseUpdatePostDto(post.getPno(),user.getId(),post.getTitle());
    }

    @Transactional
    public ResponseDeletePostDto deletePost(Long id, Long post_id){
        User user = verify(id);
        Post post = postRepository.findById(post_id).orElseThrow(()->
                    new PostNotExceptionResponse("Nout Found Post"));
        postRepository.delete(post);
        return new ResponseDeletePostDto(post.getPno(),user.getId(),true);
    }

    // user가 존재하는지 확인하는 메소드
    @Transactional
    public User verify(Long id){
        return userRepository.findById(id).orElseThrow(()->
                new UserNotExceptionResponse("Not Found User"));
    }
}
