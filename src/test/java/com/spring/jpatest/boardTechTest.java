package com.spring.jpatest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.jpatest.dto.board.boardDetailDTO;
import com.spring.jpatest.service.boardService;

@SpringBootTest
@DisplayName("게시판 기능 테스트 클래스")
public class boardTechTest {
    
    @Autowired
	boardService boardService;

    @Test
	@DisplayName("특정 게시글 정보 가져오기 (실패)")
	public void getBoardDetailFailTest(){
		
		boardDetailDTO result = boardService.getBoardDetail(2);

		System.out.println(result);

	}

    @Test
	@DisplayName("특정 게시글 정보 가져오기 (성공)")
	public void getBoardDetailSuccessTest(){
		
		boardDetailDTO result = boardService.getBoardDetail(1);

		System.out.println(result);

	}

}
