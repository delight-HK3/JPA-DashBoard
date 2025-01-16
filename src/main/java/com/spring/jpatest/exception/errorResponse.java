package com.spring.jpatest.exception;

import lombok.Getter;

@Getter
public class errorResponse {
	private String errorMessage;

    public errorResponse(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
