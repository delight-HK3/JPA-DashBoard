package com.spring.jpatest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class mainController {
    
    @RequestMapping(value="/index",  method = RequestMethod.GET)
    public ModelAndView index (ModelAndView mav){
        
        mav.setViewName("content/index");
        
        return mav;
    }

    
    

}
