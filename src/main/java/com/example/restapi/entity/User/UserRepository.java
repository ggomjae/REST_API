package com.example.restapi.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    /*
        update , delete Query시 @Modifying 어노테이션을 추가
        @Query 어노테이션의 쿼리를 JPQL이 아닌 SQL로 바꾸고 싶다면 nativeQuery=true로 변경
     */

    /*
        @Modifying
        @Query(value="UPDATE User u SET u.nickname = :nickname WHERE u.id = :id", nativeQuery=false)
        Integer update(@Param("nickname") String nickname, @Param("id") Long id);
     */
}