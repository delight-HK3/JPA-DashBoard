package com.spring.jpatest.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.spring.jpatest.dto.userDTO;
import com.spring.jpatest.service.userService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
public class mainController {

    private final userService userService;

    public mainController(userService userService){
        this.userService = userService;
    }

    /**
     * 게시판 - 유저추가
     * 
     * @param nickName
     * @return
     */
    @RequestMapping(value="/inst", method=RequestMethod.POST)
    public String userInst(@Valid userDTO userdto) {

        return new String();
    }
    
}
