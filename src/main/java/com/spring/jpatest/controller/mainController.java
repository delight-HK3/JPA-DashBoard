package com.spring.jpatest.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.jpatest.dto.paramDTO;
import com.spring.jpatest.dto.resultDTO;
import com.spring.jpatest.service.springjpaService;

@Controller
public class mainController {

    private final springjpaService springjpaservice;

    mainController(springjpaService springjpaservice){
        this.springjpaservice = springjpaservice;
    }

    @ResponseBody
    @RequestMapping(value="/get", method=RequestMethod.GET)
    public List<resultDTO> requestMethodName(paramDTO paramdto) {
        
        return springjpaservice.searchList();
    }
    

}
