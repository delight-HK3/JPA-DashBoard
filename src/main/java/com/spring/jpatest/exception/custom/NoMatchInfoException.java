package com.spring.jpatest.exception.custom;

import com.spring.jpatest.exception.exceptionEnum;

public class NoMatchInfoException extends RuntimeException{
    private exceptionEnum error;

    public NoMatchInfoException(exceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }
}
