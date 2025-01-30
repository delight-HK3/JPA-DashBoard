package com.spring.jpatest.domain;

import java.util.UUID;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "useruuid")
    //@Column(name = "useruuid", columnDefinition = "BINARY(16)",unique = true, nullable = false)
    @Comment(value = "유저 일련번호")
    private UUID useruuid;

    @Column(name = "nickName", length = 50)
    @Comment(value = "닉네임")
    private String nickName;

    @Column(name = "userId", length = 50)
    @Comment(value = "아이디")
    private String userId;

    @Column(name = "password")
    @Comment(value = "비밀번호")
    private String password;

    @Builder
    public User(String nickName, String userId, String password){
        this.nickName = nickName;
        this.userId = userId;
        this.password = password;
    }
}
