package com.spring.jpatest.dto.Like;

import java.util.UUID;

import lombok.Getter;

@Getter
public class likeRequestDTO {
    
    private final UUID userId;
    private final int boardId;

    public likeRequestDTO(UUID userId, int boardId) {
        this.userId = userId;
        this.boardId = boardId;
    }

}
