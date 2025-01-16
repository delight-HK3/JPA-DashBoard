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
    
    /**
     * 게시판 게시글 모두로딩
     * 
     * @return resultList
     */
    public List<boardDTO> getBoardList(){
        List<Board> boardList = boardRepository.findAll();
        List<boardDTO> resultList = new ArrayList<>();

        for(int i = 0; i < boardList.size(); i++){
            boardDTO boardto = boardDTO.builder()
                                .boardTitle(boardList.get(i).getBoardTitle())
                                .viewCnt(boardList.get(i).getViewCnt())
                                .likeCnt(boardList.get(i).getLikeCnt())
                                .instDate(boardList.get(i).getInstDate())
                                .build();

            resultList.add(boardto);
        }

        return resultList;
    }

}
