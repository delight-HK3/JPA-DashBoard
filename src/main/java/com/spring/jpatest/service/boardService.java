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
        System.out.println("=========isViewed=========");
        System.out.println("isViewed : "+viewCountService.isViewed(boardCd));
        
        //boardRepository.boardCntUp(boardCd);
        boardDetailDTO result = boardRepository.getBoardDetail(boardCd);

        // 1명이 조회를 한다면 상관없지만 2명 이상이 조회를 할 경우 생각
        // 1번 방법 redis 값을 변화 시키는 방법
        // - 로그인한 유저와 로그인 안한 유저 관계없이 게시글에 접근하면 Redis 조회수 증가
        // 스케줄링 시간이 되면 조회수중 가장 큰값으로 업데이트
        // 작업을 마치면 Redis flushall로 캐시 삭제
        if(!viewCountService.isViewed(boardCd)){
            viewCountService.increaseViewCount(boardCd, result.getViewCnt());    
        }
        System.out.println("=========increaseViewCount=========");
        System.out.println("increaseViewCount : "+viewCountService.getViewCount(boardCd));
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
