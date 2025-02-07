package com.spring.jpatest.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.jpatest.dto.board.boardDetailDTO;
import com.spring.jpatest.dto.board.boardListDTO;
import com.spring.jpatest.dto.board.boardSaveDTO;

import com.spring.jpatest.repository.boardRepository;

@Service
public class boardService {
    
    private final boardRepository boardRepository;

    public boardService(boardRepository boardRepository){
        this.boardRepository = boardRepository;
    }
    
    /**
     * 게시판 - 게시글 모두로딩
     * 
     * @return resultList
     */
    public Page<boardListDTO> getBoardList(Pageable pageable){
        return boardRepository.getBoardList(pageable);
    }

    /**
     * 게시판 - 게시글 상세보기, 조회수 1 증가
     * 
     * @param boardCd
     * @return result
     */
    public boardDetailDTO getBoardDetail(int boardCd){

        boardRepository.boardCntUp(boardCd);
        boardDetailDTO result = boardRepository.getBoardDetail(boardCd);

        return result;
    }

    /**
     * 게시판 - 게시글 입력
     * 
     * @param boardAdddto
     */
    public void boardSave(boardSaveDTO boardSavedto){
        boardRepository.boardSave(boardSavedto);
    }
    
    /**
     * 게시판 - 게시글 삭제
     * 
     * @param userid
     * @param boardCd
     */
    public void boardDel(UUID userid, int boardCd){
        boardRepository.boardDelete(userid, boardCd);
    }
}
