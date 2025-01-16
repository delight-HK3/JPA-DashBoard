package com.spring.jpatest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.jpatest.domain.Board;
import com.spring.jpatest.dto.boardDTO;
import com.spring.jpatest.repository.boardRepository;

@Service
public class boardService {
    
    private final boardRepository boardRepository;

    public boardService(boardRepository boardRepository){
        this.boardRepository = boardRepository;
    }
    
    public List<boardDTO> getBoardList(){
        List<Board> boardList = boardRepository.findAll();
        List<boardDTO> resultList = new ArrayList<>();

        for(Board list : boardList){
            
            

        }

    }

}
