package com.example.restapi.Entity.User;

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

    private String nickname;

    @Builder
    public User(String password, String email, String nickname){
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }
}
