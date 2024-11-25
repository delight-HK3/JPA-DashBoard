package com.spring.jpatest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jpatest.entity.Member;

import jakarta.persistence.EntityManager;

@Repository
public class hibernateRepositoryImpl implements hibernateRepository{
    
    @Autowired
    private EntityManager em;

    @Override
    @Transactional
    public void memberSave() {
        
        Member member = Member.builder()
                                .name("tester")
                                .age(10)
                                .build();

        em.persist(member);
    }

    @Override
    @Transactional
    public void memberUpdate(int id) {
        Member member = em.find(Member.class, id);
        if (member != null) {
            member.updtMember(member.getSeq(), "tester1", 111);        

            em.merge(member);
        }
    }
    
}
