package com.spring.jpatest.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum exceptionEnum {
    
    // 유저등록시 값이 없는 경우
    USER_INSERT_DATA_NULL(HttpStatus.NOT_FOUND,"유저 등록에 필요한 정보가 부족합니다.");

    private final HttpStatus status;
    private final String message;

    exceptionEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
