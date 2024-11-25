package com.spring.jpatest.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jpatest.entity.Member;

import jakarta.persistence.EntityManager;

@Repository
public class hibernateRepositoryImpl implements hibernateRepository{

    private EntityManager em;

    @Override
    @Transactional
    public void memeberSearch() {
        
        Member member = Member.builder()
                            .age(0)
                            .name("test341")
                            .build();

        em.persist(member);

    }
    
}
