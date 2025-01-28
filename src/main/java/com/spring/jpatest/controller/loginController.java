package com.spring.jpatest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.jpatest.dto.login.loginDTO;
import com.spring.jpatest.dto.login.loginResponseDTO;
import com.spring.jpatest.service.loginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class loginController {

    private final loginService loginService;

    public loginController(loginService loginService){
        this.loginService = loginService;
    }

     /**
      * 회원로그인 
      *
      * @param logindto
      * @param request
      */
    @ResponseBody
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String userlogin(loginDTO logindto, HttpServletRequest request) {
        
        HttpSession session = request.getSession(true); 
        loginResponseDTO result = loginService.getUserInfo(logindto);

        session.setAttribute("nickName",result.getNickName());
        
        return "redirect:/";
    }
    

}
