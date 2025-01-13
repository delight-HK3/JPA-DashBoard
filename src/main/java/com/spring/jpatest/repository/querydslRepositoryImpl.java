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
        // Room에 등록할 용도로 Member Type의 ArrayList생성
        List<Member> memberList = new ArrayList<>();

        for(int i = 0; i < 3; i++){ // 고객등록
            String people = "people_"+i;
            Member member = new Member(people,20); // Member Entity객체 생성 
            memberList.add(member); // 리스트에 Member객체 추가

            em.persist(member); // Member 테이블에 insert 쿼리추가
        }

        // Room 테이블에 normalRoom 등록쿼리 및 member 테이블에 등록된 사용자의 Room_seq 수정 쿼리추가 
        Room room = new Room("normalRoom",memberList); 
        em.persist(room);

        // 등록된 Room, 등록된 Member, 음식, 드링크 추가하는 쿼리추가
        Roomservice rooomservice = new Roomservice("toast", "coffee",  room, memberList.get(0));                                     
        em.persist(rooomservice);
    }

    
    
    
    
}
