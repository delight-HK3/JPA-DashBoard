package com.spring.jpatest.repository;


import com.spring.jpatest.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface memberRepository extends JpaRepository<Member, Integer>{
    
}
