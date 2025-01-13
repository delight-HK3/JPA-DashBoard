package com.spring.jpatest.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
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
import lombok.Builder;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name="t_springdatajpa_main")
public class springjpaMainEntity {

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

    @Builder
    public springjpaMainEntity(String name, int age, int uniqueNum){
        this.age = age;
        this.name = name;
        this.uniqueNum = uniqueNum;
    }
}
