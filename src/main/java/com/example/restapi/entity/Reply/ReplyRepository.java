package com.example.restapi.entity.Reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Long> {

    @Query("SELECT r FROM Reply r WHERE r.uno = ?1 ")
    List<Reply> findAllAsc(Long user_id);
}
