package com.spring.jpatest.dto.board;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class boardDetailDTO {

    private final String nickName;        // 작성자 닉네임임
    private final LocalDate instDate;     // 게시글 등록일
    private final String boardTitle;      // 게시글 제목
    private final String boardSubject;    // 게시글 내용
    private final int viewCnt;            // 게시글 조회수
    private final int likeCnt;            // 게시글 좋아요 개수

    public boardDetailDTO(String nickName, String boardTitle, String boardSubject,
                        int viewCnt, int likeCnt, LocalDate instDate){
        this.nickName = nickName;
        this.boardTitle = boardTitle;
        this.boardSubject = boardSubject;
        this.viewCnt = viewCnt;
        this.likeCnt = likeCnt;
        this.instDate = instDate;
    }

}
