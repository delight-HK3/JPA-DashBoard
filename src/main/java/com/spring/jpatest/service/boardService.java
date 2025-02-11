package com.spring.jpatest.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.jpatest.domain.User;
import com.spring.jpatest.dto.board.boardDetailDTO;
import com.spring.jpatest.dto.board.boardListDTO;
import com.spring.jpatest.dto.board.boardSaveDTO;
import com.spring.jpatest.repository.board.boardRepositoryCustom;
import com.spring.jpatest.repository.user.userRepository;

@Service
public class boardService {
    
    private final boardRepositoryCustom boardRepository;
    private final userRepository userRepository;

    public boardService(boardRepositoryCustom boardRepository, userRepository userRepository){
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
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
    public boardDetailDTO getBoardDetail(int boardCd, String tag){

        if(tag.equals("detail")){
            boardRepository.boardCntUp(boardCd);
        }
        boardDetailDTO result = boardRepository.getBoardDetail(boardCd);

        return result;
    }

    /**
     * 게시판 - 게시글 저장 및 수정
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
