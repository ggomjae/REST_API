package com.example.restapi.entity.Post;

import com.example.restapi.utils.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long pno;

    @Column(nullable = false)
    private Long uno;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "Text", length = 2000, nullable = false)
    private String content;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean postStatus;

    @Builder
    public Post(Long pno, Long uno, String title, String content, String description, boolean postStatus){

        this.uno = uno;
        this.title = title;
        this.content = content;
        this.description = description;
        this.postStatus = postStatus;
    }

    public void updateTitle(String title){
        this.title = title;
    }
}
