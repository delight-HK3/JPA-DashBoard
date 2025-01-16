package com.spring.jpatest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class boardController {

    /**
     * 게시판 로딩
     * 
     * @param mav
     * @return
     */
    @RequestMapping(value = "/", method=RequestMethod.GET)
    public ModelAndView requestMethodName(ModelAndView mav) {

        


        mav.setViewName("content/index");

        return mav;
    }



}
