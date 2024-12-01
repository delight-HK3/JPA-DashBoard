package com.spring.jpatest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.querydsl.core.Tuple;
import com.spring.jpatest.dto.memresultDTO;
import com.spring.jpatest.dto.resultDTO;
import com.spring.jpatest.entity.Member;
import com.spring.jpatest.repository.querydslRepository;

@Service
public class querydslService {
    
    private final querydslRepository querydslrepository;

    querydslService(querydslRepository querydslrepository){
        this.querydslrepository = querydslrepository;
    }

    public List<memresultDTO> searchAll(){
        return querydslrepository.searchAll();
    }

    public Member searchOne(int seq){
        return querydslrepository.searchOne(seq);
    }

    public long insert(){
        return querydslrepository.insert();
    }

    public long update(){
        return querydslrepository.update();
    }

    public long delete(){
        return querydslrepository.delete();
    }
}
