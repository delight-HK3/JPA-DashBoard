package com.spring.jpatest.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum exceptionEnum {
    
    // 유저등록시 값이 없는 경우
    USER_INSERT_DATA_NULL(HttpStatus.NOT_FOUND,"유저 등록에 필요한 정보가 부족합니다."),
    
    // 유저정보가 없는 경우
    NO_USER_INFO(HttpStatus.NOT_FOUND, "유저정보가 없습니다."),

    // 유저 정보는 있으나 비밀번호가 일치하지 않는 경우
    NOT_MATCH_PASS(HttpStatus.NOT_FOUND,"비밀번호가 올바르지 않습니다.");

    private final HttpStatus status;
    private final String message;

    exceptionEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
