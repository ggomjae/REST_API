package com.example.restapi.service;

import com.example.restapi.dto.exception.NotMatchExceptionResponse;
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


    ////////////////////////////////////////
    //          UserAPI                   //
    ////////////////////////////////////////

    @Transactional
    public ResponseCreatePostDto savePost(Long user_id, RequestCreatePostDto requestCreatePostDto){

        User user = verify(user_id);
        Post post = postRepository.save(requestCreatePostDto.toPost(user.getUno()));
        return new ResponseCreatePostDto(post.getPno(),post.getTitle());
    }

    @Transactional
    public List<ResponseRetrievePostDto> retrievePosts(Long user_id){
        User user = verify(user_id);
        return postRepository.findAllConditionDesc(user.getUno())
                .stream()
                .map( post -> new ResponseRetrievePostDto(post.getUno(),post.getPno(),post.getContent()))
                .collect(Collectors.toList());
    }

    @Transactional
    public ResponseUpdatePostDto updatePostTitle(RequestUpdatePostDto requestUpdatePostDto, Long user_id, Long post_id){
        // User가 있는지 확인
        User user = verify(user_id);

        // Post가 있는지 확인
        Post post = postRepository.findById(post_id).orElseThrow(()->
            new PostNotExceptionResponse("Not Found Post"));

        // 모두 존재하지만 user가 작성한것과 다를때 예외 처리
        if(post.getUno() != user.getUno()) throw new NotMatchExceptionResponse("Not Match");

        post.updateTitle(requestUpdatePostDto.getTitle());

        return new ResponseUpdatePostDto(post.getPno(),user.getUno(),post.getTitle());
    }

    @Transactional
    public ResponseDeletePostDto deletePost(Long user_id, Long post_id){
        User user = verify(user_id);
        Post post = postRepository.findById(post_id).orElseThrow(()->
                    new PostNotExceptionResponse("Nout Found Post"));

        // 모두 존재하지만 user가 작성한것과 다를때 예외 처리
        if(post.getUno() != user.getUno()) throw new NotMatchExceptionResponse("Not Match");

        postRepository.delete(post);
        return new ResponseDeletePostDto(post.getPno(),true);
    }

    // user가 존재하는지 확인하는 메소드
    @Transactional
    public User verify(Long user_id){
        return userRepository.findById(user_id).orElseThrow(()->
                new UserNotExceptionResponse("Not Found User"));
    }

    ////////////////////////////////////////
    //              PostAPI               //
    ////////////////////////////////////////

    @Transactional
    public List<ResponseRetrievePostDto> retrieveAllPost(){
        return postRepository.findAllDesc()
                .stream()
                .map( post -> new ResponseRetrievePostDto(post.getUno(),post.getPno(),post.getContent()))
                .collect(Collectors.toList());
    }

    @Transactional
    public ResponseRetrievePostDto retrievePost(Long post_id){
        Post post = postRepository.findById(post_id).orElseThrow(()->
                new PostNotExceptionResponse("Not Found Post"));

        return new ResponseRetrievePostDto(post.getPno(),post.getUno(),post.getContent());
    }
}
