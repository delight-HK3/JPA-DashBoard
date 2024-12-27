package com.spring.jpatest.entity;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    @Comment(value = "일련번호")
    private int seq;                    
    
    @Column(name = "room_name")
    @Comment(value = "이름")
    private String roomname;

    @Comment(value = "멤버번호")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberid")
    private Member member;

}
