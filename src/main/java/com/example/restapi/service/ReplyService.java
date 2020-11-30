package com.example.restapi.service;

import com.example.restapi.dto.exception.NotMatchExceptionResponse;
import com.example.restapi.dto.exception.PostNotExceptionResponse;
import com.example.restapi.dto.exception.ReplyNotExceptionResponse;
import com.example.restapi.dto.request.reply.RequestCreateReplyDto;
import com.example.restapi.dto.response.reply.ResponseCreateReplyDto;
import com.example.restapi.dto.response.reply.ResponseDeleteReplyDto;
import com.example.restapi.dto.response.reply.ResponseRetrieveReplyDto;
import com.example.restapi.entity.Post.Post;
import com.example.restapi.entity.Post.PostRepository;
import com.example.restapi.entity.Reply.Reply;
import com.example.restapi.entity.Reply.ReplyRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    @Transactional
    public List<ResponseRetrieveReplyDto> retrieveAll(Long user_id){
        return replyRepository.findAllAsc(user_id)
                .stream()
                .map( reply -> new ResponseRetrieveReplyDto(reply.getPno(),reply.getUno(),reply.getContent()))
                .collect(Collectors.toList());
    }

    @Transactional
    public ResponseCreateReplyDto saveReply(Long post_id, RequestCreateReplyDto requestCreateReplyDto){

        Post post = verify(post_id);
        Reply reply = replyRepository.save(requestCreateReplyDto.toReply(post.getUno(),post.getPno()));
        return new ResponseCreateReplyDto(reply.getRno(),reply.getPno(),reply.getUno());
    }

    @Transactional
    public ResponseDeleteReplyDto deleteReply(Long post_id, Long reply_id){
        Post post = verify(post_id);
        Reply reply = replyRepository.findById(reply_id).orElseThrow(()->
                new ReplyNotExceptionResponse("Not Found Reply"));

        // 모두 존재하지만 user가 작성한것과 다를때 예외 처리
        if(reply.getPno() != post.getPno()) throw new NotMatchExceptionResponse("Not Match");

        replyRepository.delete(reply);
        return new ResponseDeleteReplyDto(reply.getRno(),true);
    }

    // post가 존재하는지 확인하는 메소드
    @Transactional
    public Post verify(Long post_id){
        return postRepository.findById(post_id).orElseThrow(()->
                new PostNotExceptionResponse("Not Found Post"));
    }
}
