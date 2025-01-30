package com.spring.jpatest.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
    public List<boardListDTO> getBoardList(){
        List<boardListDTO> resultList = boardRepository.getBoardList();
        return resultList;
    }

    /**
     * 게시판 - 게시글 입력
     * 
     * @param boardAdddto
     */
    public void boardSave(boardSaveDTO boardSavedto){
        boardRepository.boardSave(boardSavedto);
    }
}
