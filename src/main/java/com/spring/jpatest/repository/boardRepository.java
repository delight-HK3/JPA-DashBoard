package com.spring.jpatest.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.jpatest.dto.boardDTO;

@Repository
public interface boardRepository{

    public List<boardDTO> getBoardList(); // 리스트 조회
    
    public void boardSave(); // 등록및 저장

    public void boardDelete(); // 게시글 삭제
} 