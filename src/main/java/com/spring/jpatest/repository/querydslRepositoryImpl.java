package com.spring.jpatest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.jpatest.dto.memresultDTO;

import com.spring.jpatest.entity.Member;
import com.spring.jpatest.entity.Room;
import com.spring.jpatest.entity.Roomservice;

import jakarta.persistence.EntityManager;

import static com.spring.jpatest.entity.QMember.member;

import java.util.ArrayList;
import java.util.List;

@Repository
public class querydslRepositoryImpl implements querydslRepository{
    
    @Autowired
    private JPAQueryFactory queryFactory;
    
    @Autowired
    private EntityManager em;

    @Override
    @Transactional
    public List<memresultDTO> searchAll() {
        List<memresultDTO> result = queryFactory
                                .select(Projections.constructor(memresultDTO.class,
                                    member.name,
                                    member.age
                                ))
                                .from(member)
                                .fetch();
        
        return result;
    }

    @Override
    @Transactional
    public Member searchOne(int seq) {
        return queryFactory.selectFrom(member)
                            .fetchFirst();
    }

    @Override
    @Transactional
	public long insert() { // execute 리턴값 타입이 long이다.
		return queryFactory.insert(member)
                            .columns(member.age,member.name)
                            .values(12,"tester11")
                            .execute();
	}

    @Override
    @Transactional
    public long update() {
        return queryFactory.update(member)
                           .set(member.age, 22)
                           .where(member.age.eq(12))
                           .execute(); 
    }

    @Override
    @Transactional
    public long delete() {
        return queryFactory.delete(member)
                            .where(member.age.eq(12))
                            .execute();
    }

    @Override
    @Transactional
    public void insertRoom() {
        List<Member> memberList = new ArrayList<>();

        for(int i = 0; i < 3; i++){ // 고객등록
            String people = "people_"+i;
            Member member = new Member(people,20);

            memberList.add(member);
            em.persist(member);
        }

        Room room = new Room("normalRoom",memberList);
        em.persist(room);

        Roomservice rooomservice = new Roomservice("toast", "coffee",  room, memberList.get(0)); 
                                            
        em.persist(rooomservice);
    }

    
    
    
    
}
