package com.example.restapi.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.uno = ?1 ORDER BY p.pno DESC")
    List<Post> findAllConditionDesc(Long user_id);

    @Query("SELECT p FROM Post p ORDER BY p.pno DESC")
    List<Post> findAllDesc();
}