package com.spring.jpatest.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.jpatest.dto.memresultDTO;
import com.spring.jpatest.entity.Member;

@Repository
public interface querydslRepository {

    public List<memresultDTO> searchAll();

    public Member searchOne(int seq);

    public long insert();

    public long update();

    public long delete();
} 