package com.spring.jpatest.entity;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="t_hibernate_main")
public class hibernateMainEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    @Comment(value = "일련번호")
    private int seq;                    

    @Column(name = "NAME")
    @Comment(value = "이름")
    private String name;

    @Column(name = "AGE")
    @Comment(value = "나이")
    private int age;

    @Column(name = "UNIQUENUM")
    @Comment(value = "고유번호")
    private int uniqueNum;
}
