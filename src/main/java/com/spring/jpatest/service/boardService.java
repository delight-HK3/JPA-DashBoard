package com.spring.jpatest.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.jpatest.dto.board.boardDetailDTO;
import com.spring.jpatest.dto.board.boardListDTO;
import com.spring.jpatest.dto.board.boardSaveDTO;
import com.spring.jpatest.repository.board.boardRepositoryCustom;
import com.spring.jpatest.repository.user.userRepository;

@Service
public class boardService {
    
    private final boardRepositoryCustom boardRepository;
    private final userRepository userRepository;
    private final viewCountService viewCountService;

    public boardService(boardRepositoryCustom boardRepository, userRepository userRepository, viewCountService viewCountService){
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.viewCountService = viewCountService;
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
        
        System.out.println(viewCountService.isViewed(boardCd));

        //boardRepository.boardCntUp(boardCd);
        boardDetailDTO result = boardRepository.getBoardDetail(boardCd);

        if(!viewCountService.isViewed(boardCd)){
            viewCountService.increaseViewCount(boardCd, result.getViewCnt());    
        }

        return result;
    }

    /**
     * 게시판 - 게시글 수정정보 조회
     * 
     * @param boardCd
     * @return
     */
    public boardDetailDTO getBoardEdit(int boardCd){
        return boardRepository.getBoardDetail(boardCd);
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
