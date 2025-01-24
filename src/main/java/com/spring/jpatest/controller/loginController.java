package com.spring.jpatest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class loginController {
    
    /**
     * 회원로그인
     * 
     * @param param
     */
    @ResponseBody
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public void requestMethodName(@RequestParam String param) {
        
    }
    

}
