package com.example.restapi.entity.User;

import com.example.restapi.utils.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Builder
    public User(String password, String email, String nickname){
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

    // user nickname을 변경하는 행동
    public void updateNickName(String nickname){
        this.nickname = nickname;
    }
}
