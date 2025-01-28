package com.spring.jpatest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.jpatest.dto.board.boardDTO;
import com.spring.jpatest.repository.boardRepository;

@Service
public class boardService {
    
    private final boardRepository boardRepository;

    public boardService(boardRepository boardRepository){
        this.boardRepository = boardRepository;
    }
    
    /**
     * 게시판 게시글 모두로딩
     * 
     * @return resultList
     */
    public List<boardDTO> getBoardList(){
        List<boardDTO> resultList = boardRepository.getBoardList();
        return resultList;
    }

}
