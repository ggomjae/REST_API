package com.example.restapi.entity.Reply;

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
    private Long rno;

    @Column(nullable = false)
    private Long pno;

    @Column(nullable = false)
    private String writer;

    @Column(columnDefinition = "Text", length = 1000)
    private String content;

    @Builder
    private Reply(Long pno, String writer, String content){
        this.pno = pno;
        this.writer = writer;
        this.content = content;
    }
}
