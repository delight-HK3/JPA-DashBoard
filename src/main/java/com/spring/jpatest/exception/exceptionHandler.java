package com.spring.jpatest.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.jpatest.exception.custom.NoMatchInfoException;

import jakarta.persistence.NoResultException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class exceptionHandler {
    
    // 유저등록시 값이 없는 경우
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> MethodArgumentNotValidExceptionHandler(final NullPointerException e){
        
        String message = getExceptionMessage(e.getMessage());
        StackTraceElement[] stackTraceElement = e.getStackTrace();
        log.error(message, stackTraceElement[0]);
        
        return ResponseEntity
                .status(exceptionEnum.USER_INSERT_DATA_NULL.getStatus())
                .body(exceptionEnum.USER_INSERT_DATA_NULL.getMessage());
    } 

    // 아이디 혹은 비밀번호가 일치하지 않는 경우
    @ExceptionHandler({NoMatchInfoException.class})
    public ResponseEntity<?> NoMatchInfoExceptionHandler(final RuntimeException e) {

        String message = getExceptionMessage(e.getMessage());
        StackTraceElement[] stackTraceElement = e.getStackTrace();
        log.error(message, stackTraceElement[0]);
        
        return ResponseEntity
                .status(exceptionEnum.NOT_MATCH_INFO.getStatus())
                .body(exceptionEnum.NOT_MATCH_INFO.getMessage());
    }

    // 게시글이 존재하지 않는 경우
    // NullPointerException : (Exception 조건 : 해당 게시글이 존재하지 않는 경우)
    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<String> NoDataExceptionHandler(final RuntimeException e) {

        String message = getExceptionMessage(e.getMessage());
        StackTraceElement[] stackTraceElement = e.getStackTrace();
        log.error(message, stackTraceElement[0]);
        
        String script = "<script>alert('해당 게시글이 존재하지 않습니다.'); location.href='/'</script>";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html; charset=UTF-8");

        return new ResponseEntity<>(script, headers, HttpStatus.OK);
    }

    private String getExceptionMessage(String message){
        if(StringUtils.hasText(message)){
            return message + "\n \t {}";
        }
        return "\n \t {}";
    }
}
