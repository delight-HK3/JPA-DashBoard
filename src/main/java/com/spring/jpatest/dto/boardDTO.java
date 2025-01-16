package com.spring.jpatest.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
public class boardDTO {
    
    private String boardTitle;
    private int viewCnt; 
    private int likeCnt;
    private LocalDate instDate;

    @Builder
    public boardDTO(String boardTitle, int viewCnt,
                    int likeCnt, LocalDate instDate){
        this.boardTitle = boardTitle;
        this.viewCnt = viewCnt;
        this.likeCnt = likeCnt;
        this.instDate = instDate;
    } 
}
