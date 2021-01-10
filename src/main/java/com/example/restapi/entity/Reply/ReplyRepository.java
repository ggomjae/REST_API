package com.example.restapi.entity.Reply;

import com.example.restapi.entity.Post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Long> {

    List<Reply> findAllAsc(Long user_id);

    List<Reply> findReplyByPost(Post post);
}
