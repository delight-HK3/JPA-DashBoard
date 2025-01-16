package com.spring.jpatest.service;

import org.springframework.stereotype.Service;

import com.spring.jpatest.domain.User;
import com.spring.jpatest.dto.userDTO;
import com.spring.jpatest.repository.userRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class userService {
    
    private final userRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public userService(userRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 사용자 등록
    public void useradd(userDTO userdto){
        User user = User.builder()
                        .nickName(userdto.getNickName())
                        .userId(userdto.getUserId())
                        .password(passwordEncoder.encode(userdto.getPassword()))
                        .build();
        
        userRepository.save(user);
    }
}
