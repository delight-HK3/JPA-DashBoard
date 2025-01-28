package com.spring.jpatest.dto.login;

import lombok.Getter;

@Getter
public class loginResponseDTO {
    
    private final String userId;        // 아이디
    private final String nickName;      // 닉네임
    private final String password;      // 비밀번호

    public loginResponseDTO(String userId, String nickName, String password){
        this.userId = userId;
        this.nickName = nickName;
        this.password = password;
    }
    
}
