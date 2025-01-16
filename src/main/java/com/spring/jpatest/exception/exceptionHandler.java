package com.spring.jpatest.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class exceptionHandler {
    
    // 유저등록시 값이 없는 경우
    /*
    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<?> NullPointerException(final NullPointerException e){
        String message = getExceptionMessage(e.getMessage());
        StackTraceElement[] stackTraceElement = e.getStackTrace();
        log.error(message, stackTraceElement[0]);
        
        return ResponseEntity
                .status(exceptionEnum.USER_INSERT_DATA_NULL.getStatus())
                .body(exceptionEnum.USER_INSERT_DATA_NULL.getMessage());
    } */

    private String getExceptionMessage(String message){
        if(StringUtils.hasText(message)){
            return message + "\n \t {}";
        }
        return "\n \t {}";
    }
}
