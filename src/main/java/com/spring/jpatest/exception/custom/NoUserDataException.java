package com.spring.jpatest.exception.custom;

import com.spring.jpatest.exception.exceptionEnum;

public class NoUserDataException extends RuntimeException{
     private exceptionEnum error;

    public NoUserDataException(exceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }
}
