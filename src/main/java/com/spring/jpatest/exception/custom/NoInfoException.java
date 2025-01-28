package com.spring.jpatest.exception.custom;

import com.spring.jpatest.exception.exceptionEnum;

public class NoInfoException extends RuntimeException{
    private exceptionEnum error;

    public NoInfoException() {
        super();
    }

    public NoInfoException(exceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }
}
