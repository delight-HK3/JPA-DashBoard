package com.spring.jpatest.dto.Like;

import java.util.UUID;

import lombok.Getter;

@Getter
public class likeRequestDTO {
    
    private final UUID useruuId;
    private final int boardCd;

    public likeRequestDTO(UUID useruuId, int boardCd) {
        this.useruuId = useruuId;
        this.boardCd = boardCd;
    }

}
