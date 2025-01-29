package com.spring.jpatest.exception;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.jpatest.exception.custom.NoMatchInfoException;

import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class exceptionHandler {
    
    // 유저등록시 값이 없는 경우
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> NullPointerException(final NullPointerException e){
        
        String message = getExceptionMessage(e.getMessage());
        StackTraceElement[] stackTraceElement = e.getStackTrace();
        log.error(message, stackTraceElement[0]);
        
        return ResponseEntity
                .status(exceptionEnum.USER_INSERT_DATA_NULL.getStatus())
                .body(exceptionEnum.USER_INSERT_DATA_NULL.getMessage());
    } 

    // 아이디 혹은 비밀번호가 일치하지 않는 경우
    @ExceptionHandler({NoMatchInfoException.class})
    public ResponseEntity<?> NoMatchInfoException(final RuntimeException e) {

        String message = getExceptionMessage(e.getMessage());
        StackTraceElement[] stackTraceElement = e.getStackTrace();
        log.error(message, stackTraceElement[0]);
        
        return ResponseEntity
                .status(exceptionEnum.NOT_MATCH_INFO.getStatus())
                .body(exceptionEnum.NOT_MATCH_INFO.getMessage());

    }


    private String getExceptionMessage(String message){
        if(StringUtils.hasText(message)){
            return message + "\n \t {}";
        }
        return "\n \t {}";
    }
}
