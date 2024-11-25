package com.spring.jpatest.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.jpatest.dto.paramDTO;
import com.spring.jpatest.dto.resultDTO;
import com.spring.jpatest.entity.springjpaMainEntity;
import com.spring.jpatest.service.hibernateService;
import com.spring.jpatest.service.springjpaService;

@Controller
public class mainController {

    private final springjpaService springjpaservice;
    private final hibernateService hibernateservice;

    mainController(springjpaService springjpaservice, hibernateService hibernateservice){
        this.springjpaservice = springjpaservice;
        this.hibernateservice = hibernateservice;
    }

    @ResponseBody
    @RequestMapping(value="/get", method=RequestMethod.GET)
    public List<resultDTO> requestMethodName(paramDTO paramdto) {
        
        //springjpaMainEntity test = springjpaservice.searchId();

        //System.out.println(test.getAge());

        return springjpaservice.searchList();
    }
    
    @ResponseBody
    @RequestMapping(value="/getone", method=RequestMethod.GET)
    public springjpaMainEntity getone() {
        // 원래 이렇게 하면 안된다.
        paramDTO paramdto = new paramDTO();

        paramdto.setSearchNum(123523);

        return springjpaservice.searchId(paramdto);
    }

    @ResponseBody
    @RequestMapping(value="/testone", method=RequestMethod.GET)
    public void saveOne() {
        springjpaservice.save();
    }
    
    @ResponseBody
    @RequestMapping(value="/testall", method=RequestMethod.GET)
    public void saveAll() {
        springjpaservice.saveAll();
    }

    @ResponseBody
    @RequestMapping(value="/hibersave", method=RequestMethod.GET)
    public void hibernateSave() {
        hibernateservice.save();
    }

    @ResponseBody
    @RequestMapping(value="/hiberupdt", method=RequestMethod.GET)
    public void hibernateUpdt(@RequestParam("id") int id) {
        hibernateservice.update(id);
    }
}
