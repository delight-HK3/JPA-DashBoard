package com.spring.jpatest.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.jpatest.dto.board.boardListDTO;
import com.spring.jpatest.dto.board.boardSaveDTO;

@Repository
public interface boardRepository{

    public List<boardListDTO> getBoardList(); // 리스트 조회
    
    public void boardSave(boardSaveDTO boardSavedto); // 등록및 저장

    public void boardDelete(); // 게시글 삭제
} 