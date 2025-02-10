package com.spring.jpatest.exception.custom;

import com.spring.jpatest.exception.exceptionEnum;

public class NoBoardDataException extends RuntimeException{
    private exceptionEnum error;

    public NoBoardDataException(exceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }

    public NoBoardDataException(int boardId){

    }
}
