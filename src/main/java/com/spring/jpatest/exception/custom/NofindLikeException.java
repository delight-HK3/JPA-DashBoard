package com.spring.jpatest.exception.custom;

import com.spring.jpatest.exception.exceptionEnum;

public class NofindLikeException extends RuntimeException{
    private exceptionEnum error;

    public NofindLikeException(exceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    } 
}
