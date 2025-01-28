package com.spring.jpatest.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.jpatest.dto.login.loginDTO;
import com.spring.jpatest.dto.login.loginResponseDTO;
import com.spring.jpatest.exception.exceptionEnum;
import com.spring.jpatest.exception.custom.NoInfoException;
import com.spring.jpatest.exception.custom.NoMatchPassException;
import com.spring.jpatest.repository.loginRepository;

@Service
public class loginService {
    
    private final loginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

    public loginService(loginRepository loginRepository, PasswordEncoder passwordEncoder){
        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 입력조건에 일하는 유저 찾기
    public loginResponseDTO getUserInfo(loginDTO logindto){

        loginResponseDTO result = loginRepository.getUserInfo(logindto);
        // 유저 정보가 존재하지 않는 경우
        if(result == null){ throw new NoInfoException(exceptionEnum.NO_USER_INFO); }

        boolean passcheck = passwordEncoder.matches(logindto.getPassword(), result.getPassword());
        // 비밀번호가 일치하지 않은 경우
        if(passcheck == false){ throw new NoMatchPassException(exceptionEnum.NOT_MATCH_PASS); }

        return result;
    }

}
