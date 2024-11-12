package com.spring.jpatest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.jpatest.dto.resultDTO;
import com.spring.jpatest.entity.Member;
import com.spring.jpatest.entity.springjpaMainEntity;
import com.spring.jpatest.repository.memberRepository;
import com.spring.jpatest.repository.springjpaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
public class springjpaService {
    
    private final springjpaRepository springjparepository;
    private final memberRepository memberrepository;

    springjpaService(springjpaRepository springjparepository, memberRepository memberrepository){
        this.springjparepository = springjparepository;
        this.memberrepository = memberrepository;
    }
    
    public springjpaMainEntity searchId(){
        return springjparepository.findById(1).get();
    }

    public List<springjpaMainEntity> searchAll(){
        return springjparepository.findAll();
    }

    @Transactional
    public void save(){
        //long time = System.currentTimeMillis();

        Member member = new Member("tester00", 10);
        System.out.println("1========================1");
        memberrepository.save(member);
        System.out.println("1========================1");

        member.setName("tester11");

        System.out.println("2========================2");
        memberrepository.save(member);
        System.out.println("2========================2");

        member.setName("tester22");

        member.setName("tester44");
        member.setName("tester333");
        /*for(int i=1; i<=10; i++){
            Member member = Member.builder()
                                .name("tester")
                                .age(10 + i)
                                .build();
            
            memberrepository.saveAndFlush(member);
        }*/

        //System.out.println("clear time : "  + (System.currentTimeMillis() - time) + "ms.");
    }

    @Transactional
    public void saveAll(){
        long time = System.currentTimeMillis();

        List<Member> members = new ArrayList<>();
        for(int i=1; i<=10; i++){
            Member member = Member.builder()
                                .name("tester")
                                .age(10 + i)
                                .build();
            
            members.add(member);
        }

        memberrepository.saveAllAndFlush(members);

        System.out.println("clear time : "  + (System.currentTimeMillis() - time) + "ms.");
    }



    public void delete(springjpaMainEntity springjpaMainEntity){
        springjparepository.delete(springjpaMainEntity);
    }

    public void deleteAll(){
        springjparepository.deleteAll();
    }

    public void deleteById(int id){
        springjparepository.deleteById(id);
    }

    public void deleteAllById(List<Integer> id){
        springjparepository.deleteAllById(id);
    }


    public List<resultDTO> searchList(){
        List<resultDTO> dtoList = new ArrayList<>();
        List<springjpaMainEntity> resultList = springjparepository.searchListJpql();

        for(int i = 0; i < resultList.size(); i++){
            resultDTO dto = resultDTO.entitytoDto(resultList.get(i));
            dtoList.add(dto);
        }

        return dtoList;
    } 
}
