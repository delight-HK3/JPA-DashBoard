package com.spring.jpatest.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.jpatest.dto.boardDTO;

@Repository
public interface boardRepository{

    public List<boardDTO> getBoardList();
    
} 