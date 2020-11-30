package com.example.restapi.service;

import com.example.restapi.dto.response.reply.ResponseRetrieveReplyDto;
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

    @Transactional
    public List<ResponseRetrieveReplyDto> retrieveAll(Long user_id){
        return replyRepository.findAllAsc(user_id)
                .stream()
                .map( reply -> new ResponseRetrieveReplyDto(reply.getPno(),reply.getUno(),reply.getContent()))
                .collect(Collectors.toList());
    }
}
