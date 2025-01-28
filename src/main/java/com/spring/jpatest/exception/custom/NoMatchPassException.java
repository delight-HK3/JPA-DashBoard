package com.spring.jpatest.exception.custom;

import com.spring.jpatest.exception.exceptionEnum;

public class NoMatchPassException extends RuntimeException{
    private exceptionEnum error;

    public NoMatchPassException() {
        super();
    }

    public NoMatchPassException(exceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }
}
