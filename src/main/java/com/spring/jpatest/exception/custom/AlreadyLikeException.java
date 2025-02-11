package com.spring.jpatest.exception.custom;

import com.spring.jpatest.exception.exceptionEnum;

public class AlreadyLikeException extends RuntimeException{
   private exceptionEnum error;

    public AlreadyLikeException(exceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    } 
}
