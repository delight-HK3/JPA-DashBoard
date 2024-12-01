package com.spring.jpatest.entity;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@NamedQuery(name = "findMember", query="select m from Member m")
@NamedQuery(name = "Member.findMemberBySeq", query="select m from Member m where m.seq = :seq")
@NamedQuery(name = "Member.findMemberByAge", query="select m from Member m where m.age = :age")

@Table(name="member")
public class Member {
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

    @Builder
    public Member(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void updtMember(int seq, String name, int age){
        this.seq = seq;
        this.name = name;
        this.age = age;
    }

}
