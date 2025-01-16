package com.spring.jpatest.dto;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class boardDTO {
    
    private String boardTitle;
    private int viewCnt; 
    private int likeCnt;
    private LocalDate instDate;

}
