package com.spring.jpatest.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jpatest.entity.Member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

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

    @Override
    @Transactional
    public void memeberDelete(int id) {
        Member member = em.find(Member.class, id);
        if (member != null) {
            em.remove(member);
        }
    }

    @Override
    @Transactional
    public void memberGetReference(int id) {
        Member member = em.find(Member.class, id); // member db 조회시점
        System.out.println("Member class: " + member.getClass());
        System.out.println("Member name: " + member.getName());

        //em.clear();

        Member memberProxy = em.getReference(Member.class, member.getSeq());
        System.out.println("Member proxy class: " + memberProxy.getClass());
        System.out.println("Member proxy name: " + memberProxy.getName()); // memberProxy db 조회시점
    }

    @Override
    @Transactional
    public void createjpql() { // createQuery 방식
        String findQuery1 = "select m from Member as m"; // 방식 1
        String findQuery2 = "select m.seq, m.name, m.age from Member as m"; // 방식 2
        
        // 일반적인 조회방법
        List<Member> resultList = em.createQuery(findQuery1, Member.class).getResultList();

        //반환 타입이 Member 로 명확한 경우 TypedQuery 사용
        TypedQuery<Member> findsql = em.createQuery(findQuery2, Member.class);
        List<Member> typesqlList = findsql.getResultList();

        for(Member member : resultList){
            System.out.println(member.toString());
        }

        System.out.println("===================================");

        for(Member member : typesqlList){
            System.out.println(member.toString());
        }
    }

    @Override
    @Transactional
    public void createsql() {
        String sqlQuery1 = "select * from Member"; // 방식 1
        String sqlQuery2 = "select seq, name, age from Member"; // 방식 2

        List<Member> resultList = em.createNativeQuery(sqlQuery1, Member.class).getResultList();
        Query findsql = em.createNativeQuery(sqlQuery2, Member.class);
        List<Member> typesqlList = findsql.getResultList();

        for(Member member : resultList){
            System.out.println(member.toString());
        }

        System.out.println("===================================");

        for(Member member : typesqlList){
            System.out.println(member.toString());
        }
    }

    @Override
    @Transactional
    public void createnamedsql() {
        List<Member> findMember = em.createNamedQuery("findMember", Member.class)
              .getResultList();
        
        List<Member> findMemberBySeq = em.createNamedQuery("Member.findMemberBySeq", Member.class)
              .setParameter("seq", 1)
              .getResultList();

        List<Member> findMemberByAge = em.createNamedQuery("Member.findMemberByAge", Member.class)
              .setParameter("age", 111)
              .getResultList();      
        
        for(Member member : findMember){
            System.out.println("findMember : "+member.toString());
        }

        for(Member member : findMemberBySeq){
            System.out.println("findMemberBySeq : "+member.toString());
        }

        for(Member member : findMemberByAge){
            System.out.println("findMemberByAge : "+member.toString());
        }
    }
    
}
