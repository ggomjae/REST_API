package com.example.restapi.entity.Reply;

import com.example.restapi.entity.Post.Post;
import com.example.restapi.utils.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long rno;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false)
    private Long uno;

    @Column(columnDefinition = "Text", length = 1000)
    private String content;

    @Builder
    private Reply(Post post, Long uno, String content){
        this.post = post;
        this.uno = uno;
        this.content = content;
    }
}
