package com.spring.jpatest.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.jpatest.dto.board.boardDetailDTO;
import com.spring.jpatest.dto.board.boardListDTO;
import com.spring.jpatest.dto.board.boardSaveDTO;

@Repository
public interface boardRepository{

    public List<boardListDTO> getBoardList(); // 리스트 조회
    
    public boardDetailDTO getBoardDetail(int boardCd); // 게시글 상세정보 호출

    public void boardCntUp(int boardCd); // 게시글 조회수 1 증가

    public void boardSave(boardSaveDTO boardSavedto); // 등록및 저장

    public void boardDelete(); // 게시글 삭제
} 