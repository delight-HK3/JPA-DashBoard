package com.spring.jpatest.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.spring.jpatest.dto.userDTO;
import com.spring.jpatest.service.userService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class userController {

    private final userService userService;

    public userController(userService userService){
        this.userService = userService;
    }

    /**
     * 게시판 - 유저추가
     * 
     * @param nickName
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/user/inst", method=RequestMethod.POST)
    public void userInst(@Valid userDTO userdto) {
        userService.useradd(userdto); // 유저추가
    }
    
}
